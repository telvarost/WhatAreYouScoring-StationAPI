package com.github.telvarost.whatareyouscoring.mixin;

import com.github.telvarost.whatareyouscoring.Config;
import com.github.telvarost.whatareyouscoring.ModHelper;
import com.github.telvarost.whatareyouscoring.achievement.Ways404Achievements;
import net.minecraft.block.BlockBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import net.minecraft.sortme.Explosion;
import net.minecraft.util.maths.TilePos;
import net.modificationstation.stationapi.api.entity.player.PlayerHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Set;

@Mixin(Explosion.class)
public class ExplosionMixin {

    @Shadow
    private Level level;
    @Shadow
    public Set damagedTiles;

    @Inject(method = "kaboomPhase2", at = @At("HEAD"), cancellable = true)
    public void miscTweaks_cancelAllExplosionTileDamage(boolean renderParticles, CallbackInfo ci) {
        if (Config.config.CHALLENGE_404_SCORING_ENABLED) {
            if (0xF != ModHelper.ModHelperFields.EXPLOSION_STATUS_BITFIELD) {
                if (0 < ModHelper.ModHelperFields.DETECT_CREEPER_EXPLOSION) {
                    ModHelper.ModHelperFields.DETECT_CREEPER_EXPLOSION--;
                    ArrayList tilesToCheck = new ArrayList<TilePos>();
                    tilesToCheck.addAll(this.damagedTiles);

                    for (int tileIndex = tilesToCheck.size() - 1; tileIndex >= 0; --tileIndex) {
                        TilePos tilePos = (TilePos) tilesToCheck.get(tileIndex);
                        int blockId = this.level.getTileId(tilePos.x, tilePos.y, tilePos.z);

                        if (BlockBase.IRON_BLOCK.id == blockId) {
                            ModHelper.ModHelperFields.EXPLOSION_STATUS_BITFIELD |= 0x1;
                            PlayerBase player = PlayerHelper.getPlayerFromGame();
                            if (null != player) {
                                player.incrementStat(Ways404Achievements.CREEPER_IRON_BLOCK);
                            }
                        } else if (BlockBase.GOLD_BLOCK.id == blockId) {
                            ModHelper.ModHelperFields.EXPLOSION_STATUS_BITFIELD |= 0x2;
                            PlayerBase player = PlayerHelper.getPlayerFromGame();
                            if (null != player) {
                                player.incrementStat(Ways404Achievements.CREEPER_GOLD_BLOCK);
                            }
                        } else if (BlockBase.LAPIS_LAZULI_BLOCK.id == blockId) {
                            ModHelper.ModHelperFields.EXPLOSION_STATUS_BITFIELD |= 0x4;
                            PlayerBase player = PlayerHelper.getPlayerFromGame();
                            if (null != player) {
                                player.incrementStat(Ways404Achievements.CREEPER_LAPIS_LAZULI_BLOCK);
                            }
                        } else if (BlockBase.DIAMOND_BLOCK.id == blockId) {
                            ModHelper.ModHelperFields.EXPLOSION_STATUS_BITFIELD |= 0x8;
                            PlayerBase player = PlayerHelper.getPlayerFromGame();
                            if (null != player) {
                                player.incrementStat(Ways404Achievements.CREEPER_DIAMOND_BLOCK);
                            }
                        }
                    }
                }
            }
        }
    }

}