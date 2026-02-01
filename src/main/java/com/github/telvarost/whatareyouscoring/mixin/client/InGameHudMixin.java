package com.github.telvarost.whatareyouscoring.mixin.client;

import com.github.telvarost.whatareyouscoring.Config;
import com.github.telvarost.whatareyouscoring.ModHelper;
import com.github.telvarost.whatareyouscoring.ScoreDisplayEnum;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.ScreenScaler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public abstract class InGameHudMixin extends DrawContext {

    @Shadow
    private Minecraft minecraft;

    @Inject(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/ClientPlayerEntity;getSleepTimer()I",
                    ordinal = 0
            ),
            cancellable = true
    )
    public void whatAreYouScoring_renderScore(float tickDelta, boolean screenOpen, int mouseX, int mouseY, CallbackInfo ci) {
        if (this.minecraft.player.health > 0 && !this.minecraft.options.debugHud) {
            TextRenderer textRenderer = this.minecraft.textRenderer;
            ScreenScaler screenScaler = new ScreenScaler(this.minecraft.options, this.minecraft.displayWidth, this.minecraft.displayHeight);
            int centerX = screenScaler.getScaledWidth() / 2;
            int color = 0xFFFFFF;
            int y = 2;
            ArrayList<String> scoresToDisplay = new ArrayList<>();
            int totalBasicScore    = ModHelper.ModHelperFields.CurrentBasicScore;
            int totalDaysScore     = ModHelper.ModHelperFields.CurrentDaysScore;
            int total404Score      = ModHelper.ModHelperFields.Current404Score;

            if (Config.config.SPECIAL_DEATH_EFFECT_ON_SCORE) {
                totalBasicScore    += ModHelper.ModHelperFields.PrevCumulativeBasicScore;
                totalDaysScore     += ModHelper.ModHelperFields.PrevCumulativeDaysScore;
                total404Score      += ModHelper.ModHelperFields.PrevCumulative404Score;
            }

            if (ScoreDisplayEnum.COMBINED == Config.config.SCORE_RENDERING_CONFIG.IN_GAME_SCORE_DISPLAY_MODE) {
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

                this.drawCenteredTextWithShadow(textRenderer, "Total: §7" + totalCombinedScore, centerX, y, color);
            } else if (ScoreDisplayEnum.LISTED == Config.config.SCORE_RENDERING_CONFIG.IN_GAME_SCORE_DISPLAY_MODE) {
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
            } else if (ScoreDisplayEnum.CUSTOM == Config.config.SCORE_RENDERING_CONFIG.IN_GAME_SCORE_DISPLAY_MODE) {
                if (  Config.config.BASIC_SCORE_CONFIG.BASIC_SCORING_ENABLED
                   && Config.config.SCORE_RENDERING_CONFIG.CUSTOM_IN_GAME_HUD_SCORE_DISPLAY.DISPLAY_BASIC_SCORE
                ) {
                    scoresToDisplay.add("Score: §e" + totalBasicScore);
                }

                if (  Config.config.DAYS_SCORE_CONFIG.DAYS_SCORING_ENABLED
                   && Config.config.SCORE_RENDERING_CONFIG.CUSTOM_IN_GAME_HUD_SCORE_DISPLAY.DISPLAY_DAYS_SCORE
                ) {
                    scoresToDisplay.add("Days: §b" + totalDaysScore);
                }

                if (  Config.config.CHALLENGE_404_CONFIG.CHALLENGE_404_SCORING_ENABLED
                   && Config.config.SCORE_RENDERING_CONFIG.CUSTOM_IN_GAME_HUD_SCORE_DISPLAY.DISPLAY_404_CHALLENGE_SCORE
                ) {
                    scoresToDisplay.add("Challenge: §c" + total404Score);
                }

                whatAreYouScoring_drawScores(textRenderer, scoresToDisplay, centerX, y, color);
            } else {
                /* Do nothing */
            }
        }
    }

    @Unique
    private void whatAreYouScoring_drawScores(TextRenderer textRenderer, ArrayList<String> scoresToDisplay, int centerX, int y, int color) {
        if (2 < scoresToDisplay.size()) {
            this.drawCenteredTextWithShadow(
                    textRenderer,
                    scoresToDisplay.get(0) + "§7 - §f" + scoresToDisplay.get(1) + "§7 - §f" + scoresToDisplay.get(2),
                    centerX, y, color);
        } else if (1 < scoresToDisplay.size()) {
            this.drawCenteredTextWithShadow(
                    textRenderer,
                    scoresToDisplay.get(0) + "§7 - §f" + scoresToDisplay.get(1),
                    centerX, y, color);
        } else if (!scoresToDisplay.isEmpty()) {
            this.drawCenteredTextWithShadow(
                    textRenderer,
                    scoresToDisplay.get(0),
                    centerX, y, color);
        } else {
            /* Do nothing */
        }
    }
}
