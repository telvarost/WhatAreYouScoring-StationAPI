package com.github.telvarost.whatareyouscoring.mixin;

import com.github.telvarost.whatareyouscoring.Config;
import com.github.telvarost.whatareyouscoring.ModHelper;
import com.github.telvarost.whatareyouscoring.achievement.Ways404Achievements;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
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
    private World world;
    @Shadow
    public Set damagedBlocks;

    @Inject(method = "playExplosionSound", at = @At("HEAD"), cancellable = true)
    public void whatAreYouScoring_cancelAllExplosionTileDamage(boolean renderParticles, CallbackInfo ci) {
        if (Config.config.CHALLENGE_404_CONFIG.CHALLENGE_404_SCORING_ENABLED) {
            if (0xF != ModHelper.ModHelperFields.EXPLOSION_STATUS_BITFIELD) {
                if (0 < ModHelper.ModHelperFields.DETECT_CREEPER_EXPLOSION) {
                    ModHelper.ModHelperFields.DETECT_CREEPER_EXPLOSION--;
                    ArrayList tilesToCheck = new ArrayList<BlockPos>();
                    tilesToCheck.addAll(this.damagedBlocks);

                    for (int tileIndex = tilesToCheck.size() - 1; tileIndex >= 0; --tileIndex) {
                        BlockPos tilePos = (BlockPos) tilesToCheck.get(tileIndex);
                        int blockId = this.world.getBlockId(tilePos.x, tilePos.y, tilePos.z);

                        if (Block.IRON_BLOCK.id == blockId) {
                            ModHelper.ModHelperFields.EXPLOSION_STATUS_BITFIELD |= 0x1;
                            ModHelper.ModHelperFields.Current404Score = ModHelper.calculate404ChallengeScore(world);
                            PlayerEntity player = PlayerHelper.getPlayerFromGame();
                            if (null != player) {
                                player.incrementStat(Ways404Achievements.CREEPER_IRON_BLOCK);
                            }
                        } else if (Block.GOLD_BLOCK.id == blockId) {
                            ModHelper.ModHelperFields.EXPLOSION_STATUS_BITFIELD |= 0x2;
                            ModHelper.ModHelperFields.Current404Score = ModHelper.calculate404ChallengeScore(world);
                            PlayerEntity player = PlayerHelper.getPlayerFromGame();
                            if (null != player) {
                                player.incrementStat(Ways404Achievements.CREEPER_GOLD_BLOCK);
                            }
                        } else if (Block.LAPIS_BLOCK.id == blockId) {
                            ModHelper.ModHelperFields.EXPLOSION_STATUS_BITFIELD |= 0x4;
                            ModHelper.ModHelperFields.Current404Score = ModHelper.calculate404ChallengeScore(world);
                            PlayerEntity player = PlayerHelper.getPlayerFromGame();
                            if (null != player) {
                                player.incrementStat(Ways404Achievements.CREEPER_LAPIS_LAZULI_BLOCK);
                            }
                        } else if (Block.DIAMOND_BLOCK.id == blockId) {
                            ModHelper.ModHelperFields.EXPLOSION_STATUS_BITFIELD |= 0x8;
                            ModHelper.ModHelperFields.Current404Score = ModHelper.calculate404ChallengeScore(world);
                            PlayerEntity player = PlayerHelper.getPlayerFromGame();
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