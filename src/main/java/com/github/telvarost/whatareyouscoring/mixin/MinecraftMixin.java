package com.github.telvarost.whatareyouscoring.mixin;

import com.github.telvarost.whatareyouscoring.Config;
import com.github.telvarost.whatareyouscoring.ModHelper;
import com.github.telvarost.whatareyouscoring.achievement.Ways404Achievements;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Shadow public ClientPlayerEntity player;

    @Inject(
            method = "startGame",
            at = @At("HEAD")
    )
    public void createOrLoadWorld(String string, String string2, long l, CallbackInfo ci) {
        ModHelper.resetFieldsOnDeath(null, true);
    }

    @Inject(
            method = "changeDimension",
            at = @At("HEAD"),
            cancellable = true
    )
    public void quickAdditions_changeDimension(CallbackInfo ci) {
        if (Config.config.CHALLENGE_404_CONFIG.CHALLENGE_404_SCORING_ENABLED) {
            if (ModHelper.ModHelperFields.HAS_PLAYER_EXITED_THE_NETHER != (ModHelper.ModHelperFields.HAS_PLAYER_EXITED_THE_NETHER & ModHelper.ModHelperFields.OTHER_BITFIELD)) {
                if (this.player.dimensionId == -1) {
                    ModHelper.ModHelperFields.OTHER_BITFIELD |= ModHelper.ModHelperFields.HAS_PLAYER_EXITED_THE_NETHER;
                    this.player.incrementStat(Ways404Achievements.EXIT_THE_NETHER);
                }
            }
        }
    }
}
