package com.github.telvarost.whatareyouscoring.mixin;

import com.github.telvarost.whatareyouscoring.ModHelper;
import com.github.telvarost.whatareyouscoring.achievement.Ways404Achievements;
import net.minecraft.block.CropBlock;
import net.minecraft.block.PlantBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.modificationstation.stationapi.api.entity.player.PlayerHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(CropBlock.class)
public class CropsMixin extends PlantBlock {
    public CropsMixin(int i, int j) {
        super(i, j);
    }

    @Inject(
            method = "getDroppedItemId",
            at = @At("HEAD")
    )
    public void getDropId(int i, Random random, CallbackInfoReturnable<Integer> cir) {
        if (i == 7) {
            if (15 > ModHelper.ModHelperFields.WHEAT_BROKEN) {
                ModHelper.ModHelperFields.WHEAT_BROKEN++;
                if (15 == ModHelper.ModHelperFields.WHEAT_BROKEN) {
                    PlayerEntity player = PlayerHelper.getPlayerFromGame();
                    if (null != player) {
                        player.incrementStat(Ways404Achievements.BREAK_15_WHEAT);
                    }
                }
            }
        }
    }
}
