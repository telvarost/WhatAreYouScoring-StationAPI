package com.github.telvarost.whatareyouscoring.mixin;

import com.github.telvarost.whatareyouscoring.Config;
import com.github.telvarost.whatareyouscoring.achievement.WaysBasicAchievements;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.BlockBase;

@Mixin(BlockBase.class)
public class BlockBaseMixin {

    @Inject(method = "afterBreak", at = @At("HEAD"), cancellable = true)
    public void clientsideEssentials_afterBreakBlock(Level arg, PlayerBase arg2, int i, int j, int k, int l, CallbackInfo ci) {
        if (Config.config.SCORE_CONFIG.ADD_SCORE_ON_BLOCK_REMOVED) {
            if (null != arg2) {
                arg2.score++;
            }
        }
    }

    @Inject(method = "afterPlaced", at = @At("HEAD"), cancellable = true)
    public void clientsideEssentials_afterPlaced(Level arg, int i, int j, int k, Living arg2, CallbackInfo ci) {
        if (Config.config.SCORE_CONFIG.ADD_SCORE_ON_BLOCK_PLACED) {
            if (null != arg2) {
                if (arg2 instanceof PlayerBase) {
                    ((PlayerBase) arg2).score++;
                    ((PlayerBase) arg2).incrementStat(WaysBasicAchievements.START_BASIC);
                    //((PlayerBase) arg2).incrementStat(WaysDaysAchievements.START_DAYS);
                    //((PlayerBase) arg2).incrementStat(Ways404Achievements.START_404);
                }
            }
        }
    }
}
