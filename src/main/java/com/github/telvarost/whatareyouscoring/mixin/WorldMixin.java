package com.github.telvarost.whatareyouscoring.mixin;

import com.github.telvarost.whatareyouscoring.Config;
import com.github.telvarost.whatareyouscoring.achievement.Ways404Achievements;
import com.github.telvarost.whatareyouscoring.achievement.WaysBasicAchievements;
import com.github.telvarost.whatareyouscoring.achievement.WaysDaysAchievements;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(World.class)
public abstract class WorldMixin implements BlockView {

    @Environment(EnvType.CLIENT)
    @Inject(
            method = "addPlayer",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z"
            )
    )
    public void whatAreYouScoring_addPlayer(PlayerEntity player, CallbackInfo ci) {
        if (Config.config.BASIC_SCORE_CONFIG.BASIC_SCORING_ENABLED) {
            player.incrementStat(WaysBasicAchievements.START_BASIC);
        }

        if (Config.config.DAYS_SCORE_CONFIG.DAYS_SCORING_ENABLED) {
            player.incrementStat(WaysDaysAchievements.START_DAYS);
        }

        if (Config.config.CHALLENGE_404_CONFIG.CHALLENGE_404_SCORING_ENABLED) {
            player.incrementStat(Ways404Achievements.START_404);
        }
    }
}
