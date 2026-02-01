package com.github.telvarost.whatareyouscoring.mixin.client;

import com.github.telvarost.whatareyouscoring.Config;
import com.github.telvarost.whatareyouscoring.ModHelper;
import com.github.telvarost.whatareyouscoring.achievement.Ways404Achievements;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.ClientPlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Shadow public ClientPlayerEntity player;

    @Shadow
    public World world;

    @Inject(
            method = "startGame",
            at = @At("HEAD")
    )
    public void whatAreYouScoring_createOrLoadWorld(String string, String string2, long l, CallbackInfo ci) {
        ModHelper.resetFieldsOnDeath(null, true);
    }

    @Inject(
            method = "changeDimension",
            at = @At("HEAD"),
            cancellable = true
    )
    public void whatAreYouScoring_changeDimension(CallbackInfo ci) {
        if (Config.config.CHALLENGE_404_CONFIG.CHALLENGE_404_SCORING_ENABLED) {
            if (ModHelper.ModHelperFields.HAS_PLAYER_EXITED_THE_NETHER != (ModHelper.ModHelperFields.HAS_PLAYER_EXITED_THE_NETHER & ModHelper.ModHelperFields.OTHER_BITFIELD)) {
                if (this.player.dimensionId == -1) {
                    ModHelper.ModHelperFields.OTHER_BITFIELD |= ModHelper.ModHelperFields.HAS_PLAYER_EXITED_THE_NETHER;
                    ModHelper.ModHelperFields.Current404Score = ModHelper.calculate404ChallengeScore(world);
                    this.player.incrementStat(Ways404Achievements.EXIT_THE_NETHER);
                }
            }
        }
    }

    @Inject(
            method = "respawnPlayer",
            at = @At("RETURN"),
            cancellable = true
    )
    public void whatAreYouScoring_respawnPlayer(boolean worldSpawn, int dimension, CallbackInfo ci) {
        ModHelper.ModHelperFields.CurrentBasicScore = ModHelper.calculateBasicScore();
        ModHelper.ModHelperFields.CurrentDaysScore = ModHelper.calculateDaysScore();
        ModHelper.ModHelperFields.Current404Score = ModHelper.calculate404ChallengeScore(world);
    }
}
