package com.github.telvarost.whatareyouscoring.mixin;

import com.github.telvarost.whatareyouscoring.Config;
import com.github.telvarost.whatareyouscoring.ModHelper;
import com.github.telvarost.whatareyouscoring.achievement.Ways404Achievements;
import com.github.telvarost.whatareyouscoring.achievement.WaysBasicAchievements;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.entity.player.PlayerHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.BlockBase;

@Mixin(BlockBase.class)
public class BlockBaseMixin {

    @Shadow @Final public int id;

    @Inject(method = "afterBreak", at = @At("HEAD"), cancellable = true)
    public void clientsideEssentials_afterBreakBlock(Level arg, PlayerBase arg2, int i, int j, int k, int l, CallbackInfo ci) {
        if (null != arg2) {
            if (Config.config.BASIC_SCORE_CONFIG.ADD_SCORE_ON_BLOCK_REMOVED) {
                if (0 == ModHelper.ModHelperFields.BLOCKS_REMOVED) {
                    arg2.incrementStat(WaysBasicAchievements.BLOCKS_REMOVED);
                }

                ModHelper.ModHelperFields.BLOCKS_REMOVED++;
                arg2.score++;
            }

            if (Config.config.CHALLENGE_404_CONFIG.CHALLENGE_404_SCORING_ENABLED) {

            }
        }
    }

    @Inject(method = "afterPlaced", at = @At("HEAD"), cancellable = true)
    public void clientsideEssentials_afterPlaced(Level arg, int i, int j, int k, Living arg2, CallbackInfo ci) {
        if (null != arg2) {
            if (arg2 instanceof PlayerBase) {
                if (Config.config.BASIC_SCORE_CONFIG.ADD_SCORE_ON_BLOCK_PLACED) {
                    if (0 == ModHelper.ModHelperFields.BLOCKS_PLACED) {
                        ((PlayerBase) arg2).incrementStat(WaysBasicAchievements.BLOCKS_PLACED);
                    }

                    ModHelper.ModHelperFields.BLOCKS_PLACED++;
                    ((PlayerBase) arg2).score++;
                }

                if (Config.config.CHALLENGE_404_CONFIG.CHALLENGE_404_SCORING_ENABLED) {
                    if (32 > ModHelper.ModHelperFields.GLASS_PLACED) {
                        if (this.id == BlockBase.GLASS.id) {
                            ModHelper.ModHelperFields.GLASS_PLACED++;

                            if (32 == ModHelper.ModHelperFields.GLASS_PLACED) {
                                PlayerBase player = PlayerHelper.getPlayerFromGame();
                                if (null != player) {
                                    player.incrementStat(Ways404Achievements.PLACE_32_GLASS);
                                }
                            }
                            return;
                        }
                    }

                    if (20 > ModHelper.ModHelperFields.BRICKS_PLACED) {
                        if (this.id == BlockBase.BRICKS.id) {
                            ModHelper.ModHelperFields.BRICKS_PLACED++;

                            if (20 == ModHelper.ModHelperFields.BRICKS_PLACED) {
                                PlayerBase player = PlayerHelper.getPlayerFromGame();
                                if (null != player) {
                                    player.incrementStat(Ways404Achievements.PLACE_20_BRICKS);
                                }
                            }
                            return;
                        }
                    }

                    if (false == ModHelper.ModHelperFields.HAS_CRASH_SLAB_BEEN_PLACED) {
                        if (arg.getTileId(i, j - 1, k) == BlockBase.DOUBLE_STONE_SLAB.id) {
                            if (3 < arg.getTileMeta(i, j - 1, k)) {
                                ModHelper.ModHelperFields.HAS_CRASH_SLAB_BEEN_PLACED = true;
                                PlayerBase player = PlayerHelper.getPlayerFromGame();
                                if (null != player) {
                                    player.incrementStat(Ways404Achievements.PLACE_A_CRASH_SLAB);
                                }
                            }
                            return;
                        }
                    }
                }
            }
        }
    }
}
