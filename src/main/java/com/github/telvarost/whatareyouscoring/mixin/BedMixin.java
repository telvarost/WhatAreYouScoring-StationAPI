package com.github.telvarost.whatareyouscoring.mixin;

import com.github.telvarost.whatareyouscoring.Config;
import com.github.telvarost.whatareyouscoring.ModHelper;
import com.github.telvarost.whatareyouscoring.achievement.Ways404Achievements;
import net.minecraft.block.BedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BedBlock.class)
public class BedMixin extends Block {

    public BedMixin(int i) {
        super(i, 134, Material.WOOL);
    }

    @Inject(
        method = "onUse",
        at = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/block/BedBlock;method_1657(Lnet/minecraft/world/World;IIIZ)V",
                ordinal = 1
        )
    )
    public void canUse(World arg, int i, int j, int k, PlayerEntity arg2, CallbackInfoReturnable<Boolean> cir) {
        if (Config.config.CHALLENGE_404_CONFIG.CHALLENGE_404_SCORING_ENABLED) {
            if (null != arg2) {
                ModHelper.ModHelperFields.OTHER_BITFIELD |= ModHelper.ModHelperFields.HAS_PLAYER_SLEPT;
                arg2.incrementStat(Ways404Achievements.NEVER_SLEEP);
            }
        }
    }
}
