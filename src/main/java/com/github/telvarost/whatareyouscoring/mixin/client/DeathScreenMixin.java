package com.github.telvarost.whatareyouscoring.mixin.client;

import com.github.telvarost.whatareyouscoring.Config;
import com.github.telvarost.whatareyouscoring.ModHelper;
import com.github.telvarost.whatareyouscoring.ScoreDisplayEnum;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;

@Environment(EnvType.CLIENT)
@Mixin(DeathScreen.class)
public class DeathScreenMixin extends Screen {

    public DeathScreenMixin() {
    }

    @WrapOperation(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/DeathScreen;drawCenteredTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;III)V",
                    ordinal = 1
            )
    )
    private void whatAreYouScoring_renderDeathScreenText(DeathScreen instance, TextRenderer textRenderer, String text, int centerX, int y, int color, Operation<Void> original) {
        ArrayList<String> scoresToDisplay = new ArrayList<>();
        int totalBasicScore    = ModHelper.ModHelperFields.CurrentBasicScore;
        int totalDaysScore     = ModHelper.ModHelperFields.CurrentDaysScore;
        int total404Score      = ModHelper.ModHelperFields.Current404Score;

        if (Config.config.SPECIAL_DEATH_EFFECT_ON_SCORE) {
            totalBasicScore    += ModHelper.ModHelperFields.PrevCumulativeBasicScore;
            totalDaysScore     += ModHelper.ModHelperFields.PrevCumulativeDaysScore;
            total404Score      += ModHelper.ModHelperFields.PrevCumulative404Score;
        }

        if (ScoreDisplayEnum.COMBINED == Config.config.SCORE_RENDERING_CONFIG.DEATH_SCORE_DISPLAY_MODE) {
            int totalCombinedScore = 0;

            if (Config.config.BASIC_SCORE_CONFIG.BASIC_SCORING_ENABLED) {
                totalCombinedScore += totalBasicScore;
            }

            if (Config.config.DAYS_SCORE_CONFIG.DAYS_SCORING_ENABLED) {
                totalCombinedScore += totalDaysScore;
            }

            if (Config.config.CHALLENGE_404_CONFIG.CHALLENGE_404_SCORING_ENABLED) {
                totalCombinedScore += total404Score;
            }

            instance.drawCenteredTextWithShadow(textRenderer, "Total: §7" + totalCombinedScore, centerX, y, color);
        } else if (ScoreDisplayEnum.LISTED == Config.config.SCORE_RENDERING_CONFIG.DEATH_SCORE_DISPLAY_MODE) {
            if (Config.config.BASIC_SCORE_CONFIG.BASIC_SCORING_ENABLED) {
                scoresToDisplay.add("Score: §e" + totalBasicScore);
            }

            if (Config.config.DAYS_SCORE_CONFIG.DAYS_SCORING_ENABLED) {
                scoresToDisplay.add("Days: §b" + totalDaysScore);
            }

            if (Config.config.CHALLENGE_404_CONFIG.CHALLENGE_404_SCORING_ENABLED) {
                scoresToDisplay.add("Challenge: §c" + total404Score);
            }

            whatAreYouScoring_drawScores(textRenderer, scoresToDisplay, centerX, y, color);
        } else if (ScoreDisplayEnum.CUSTOM == Config.config.SCORE_RENDERING_CONFIG.DEATH_SCORE_DISPLAY_MODE) {
            if (  Config.config.BASIC_SCORE_CONFIG.BASIC_SCORING_ENABLED
               && Config.config.SCORE_RENDERING_CONFIG.CUSTOM_DEATH_SCORE_DISPLAY.DISPLAY_BASIC_SCORE
            ) {
                scoresToDisplay.add("Score: §e" + totalBasicScore);
            }

            if (  Config.config.DAYS_SCORE_CONFIG.DAYS_SCORING_ENABLED
               && Config.config.SCORE_RENDERING_CONFIG.CUSTOM_DEATH_SCORE_DISPLAY.DISPLAY_DAYS_SCORE
            ) {
                scoresToDisplay.add("Days: §b" + totalDaysScore);
            }

            if (  Config.config.CHALLENGE_404_CONFIG.CHALLENGE_404_SCORING_ENABLED
               && Config.config.SCORE_RENDERING_CONFIG.CUSTOM_DEATH_SCORE_DISPLAY.DISPLAY_404_CHALLENGE_SCORE
            ) {
                scoresToDisplay.add("Challenge: §c" + total404Score);
            }

            whatAreYouScoring_drawScores(textRenderer, scoresToDisplay, centerX, y, color);
        } else {
            original.call(instance, textRenderer, text, centerX, y, color);
        }
    }

    @Unique
    private void whatAreYouScoring_drawScores(TextRenderer textRenderer, ArrayList<String> scoresToDisplay, int centerX, int y, int color) {
        if (2 < scoresToDisplay.size()) {
            this.drawCenteredTextWithShadow(textRenderer, scoresToDisplay.get(0), centerX, y - 10, color);
            this.drawCenteredTextWithShadow(textRenderer, scoresToDisplay.get(1), centerX, y, color);
            this.drawCenteredTextWithShadow(textRenderer, scoresToDisplay.get(2), centerX, y + 10, color);
        } else if (1 < scoresToDisplay.size()) {
            this.drawCenteredTextWithShadow(textRenderer, scoresToDisplay.get(0), centerX, y - 5, color);
            this.drawCenteredTextWithShadow(textRenderer, scoresToDisplay.get(1), centerX, y + 5, color);
        } else if (!scoresToDisplay.isEmpty()) {
            this.drawCenteredTextWithShadow(textRenderer, scoresToDisplay.get(0), centerX, y, color);
        } else {
            /* Do nothing */
        }
    }
}