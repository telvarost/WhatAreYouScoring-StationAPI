package com.github.telvarost.whatareyouscoring.mixin;

import com.github.telvarost.whatareyouscoring.Config;
import com.github.telvarost.whatareyouscoring.achievement.Ways404Achievements;
import com.github.telvarost.whatareyouscoring.achievement.WaysBasicAchievements;
import com.github.telvarost.whatareyouscoring.achievement.WaysDaysAchievements;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(GameMenuScreen.class)
public class PauseMixin extends Screen {

    @Inject(
            method = "buttonClicked",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/Minecraft;setScreen(Lnet/minecraft/client/gui/screen/Screen;)V",
                    ordinal = 3
            )
    )
    protected void buttonClicked(ButtonWidget par1, CallbackInfo ci) {
        if (Config.config.BASIC_SCORE_CONFIG.BASIC_SCORING_ENABLED) {
            WaysBasicAchievements.updateAchievementCounts();
        }

        if (Config.config.DAYS_SCORE_CONFIG.DAYS_SCORING_ENABLED) {
            WaysDaysAchievements.updateAchievementCounts();
        }

        if (Config.config.CHALLENGE_404_CONFIG.CHALLENGE_404_SCORING_ENABLED) {
            Ways404Achievements.updateAchievementCounts();
        }
    }
}
