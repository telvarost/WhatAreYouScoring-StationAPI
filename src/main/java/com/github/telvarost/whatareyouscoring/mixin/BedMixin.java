package com.github.telvarost.whatareyouscoring.mixin;

import com.github.telvarost.whatareyouscoring.Config;
import com.github.telvarost.whatareyouscoring.ModHelper;
import com.github.telvarost.whatareyouscoring.achievement.Ways404Achievements;
import net.minecraft.block.Bed;
import net.minecraft.block.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Bed.class)
public class BedMixin extends BlockBase {

    public BedMixin(int i) {
        super(i, 134, Material.WOOL);
    }

    @Inject(
        method = "canUse",
        at = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/block/Bed;setOccupied(Lnet/minecraft/level/Level;IIIZ)V",
                ordinal = 1
        )
    )
    public void canUse(Level arg, int i, int j, int k, PlayerBase arg2, CallbackInfoReturnable<Boolean> cir) {
        if (Config.config.CHALLENGE_404_CONFIG.CHALLENGE_404_SCORING_ENABLED) {
            if (null != arg2) {
                ModHelper.ModHelperFields.HAS_PLAYER_SLEPT = true;
                arg2.incrementStat(Ways404Achievements.NEVER_SLEEP);
            }
        }
    }
}
