package com.github.telvarost.whatareyouscoring.mixin;

import com.github.telvarost.whatareyouscoring.Config;
import com.github.telvarost.whatareyouscoring.ModHelper;
import com.github.telvarost.whatareyouscoring.ScoreDisplayEnum;
import com.github.telvarost.whatareyouscoring.achievement.WaysBasicAchievements;
import com.github.telvarost.whatareyouscoring.achievement.WaysDaysAchievements;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ScreenBase;
import net.minecraft.client.render.TextRenderer;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.stat.Stats;
import net.modificationstation.stationapi.api.entity.player.PlayerHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.client.gui.screen.ingame.Death;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.time.Duration;
import java.util.ArrayList;

@Environment(EnvType.CLIENT)
@Mixin(Death.class)
public class DeathScreenMixin extends ScreenBase {

    public DeathScreenMixin() {
    }

    @Redirect(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/ingame/Death;drawTextWithShadowCentred(Lnet/minecraft/client/render/TextRenderer;Ljava/lang/String;III)V",
                    ordinal = 1
            )
    )
    private void clientsideEssentials_renderDeathScreenText(Death instance, TextRenderer textRenderer, String s, int i, int j, int k) {
        if (ScoreDisplayEnum.VANILLA == Config.config.SCORING_DISPLAY_TYPE) {
            this.drawTextWithShadowCentred(textRenderer, s, i, j, k);
        } else if (ScoreDisplayEnum.BASIC_SCORE == Config.config.SCORING_DISPLAY_TYPE) {
            /** - Get basic score */
            int currentScore = ModHelper.ModHelperFields.DEATH_SCORE_BASIC_MODE;
            if (Config.config.SPECIAL_DEATH_EFFECT_ON_SCORE) {
                currentScore += ModHelper.ModHelperFields.PrevCumulativeBasicScore;
            }
            this.drawTextWithShadowCentred(textRenderer, "Score: \u00a7e" + currentScore, i, j, k);
        } else if (ScoreDisplayEnum.DAYS_SCORE == Config.config.SCORING_DISPLAY_TYPE) {
            /** - Get days survived */
            int currentScore = ModHelper.ModHelperFields.DEATH_SCORE_DAYS_MODE;
            if (Config.config.SPECIAL_DEATH_EFFECT_ON_SCORE) {
                currentScore += ModHelper.ModHelperFields.PrevCumulativeDaysScore;
            }
            this.drawTextWithShadowCentred(textRenderer, "Score: \u00a7b" + currentScore, i, j, k);
        } else if (ScoreDisplayEnum.CHALLENGE_404 == Config.config.SCORING_DISPLAY_TYPE) {
            /** - Get 404 challenge score */
            int currentScore = ModHelper.ModHelperFields.DEATH_SCORE_404_MODE;
            if (Config.config.SPECIAL_DEATH_EFFECT_ON_SCORE) {
                currentScore += ModHelper.ModHelperFields.PrevCumulative404Score;
            }
            this.drawTextWithShadowCentred(textRenderer, "Score: \u00a7c" + currentScore, i, j, k);
        } else if (ScoreDisplayEnum.ALL_WAYS == Config.config.SCORING_DISPLAY_TYPE) {
            ArrayList scoresToDisplay = new ArrayList<Integer>();
            ArrayList scoresToDisplayColor = new ArrayList<Character>();

            if (Config.config.BASIC_SCORE_CONFIG.BASIC_SCORING_ENABLED) {
                /** - Get basic score */
                int currentScore = ModHelper.ModHelperFields.DEATH_SCORE_BASIC_MODE;
                if (Config.config.SPECIAL_DEATH_EFFECT_ON_SCORE) {
                    currentScore += ModHelper.ModHelperFields.PrevCumulativeBasicScore;
                }
                scoresToDisplay.add(currentScore);
                scoresToDisplayColor.add('e');
            }

            if (Config.config.DAYS_SCORE_CONFIG.DAYS_SCORING_ENABLED) {
                /** - Get days survived */
                int currentScore = ModHelper.ModHelperFields.DEATH_SCORE_DAYS_MODE;
                if (Config.config.SPECIAL_DEATH_EFFECT_ON_SCORE) {
                    currentScore += ModHelper.ModHelperFields.PrevCumulativeDaysScore;
                }
                scoresToDisplay.add(currentScore);
                scoresToDisplayColor.add('b');
            }

            if (Config.config.CHALLENGE_404_CONFIG.CHALLENGE_404_SCORING_ENABLED) {
                /** - Get 404 challenge score */
                int currentScore = ModHelper.ModHelperFields.DEATH_SCORE_404_MODE;
                if (Config.config.SPECIAL_DEATH_EFFECT_ON_SCORE) {
                    currentScore += ModHelper.ModHelperFields.PrevCumulative404Score;
                }
                scoresToDisplay.add(currentScore);
                scoresToDisplayColor.add('c');
            }

            if (3 == scoresToDisplay.size()) {
                this.drawTextWithShadowCentred(textRenderer, "Score: \u00a7" + scoresToDisplayColor.get(0) + scoresToDisplay.get(0), i, j - 10, k);
                this.drawTextWithShadowCentred(textRenderer, "Score: \u00a7" + scoresToDisplayColor.get(1) + scoresToDisplay.get(1), i, j, k);
                this.drawTextWithShadowCentred(textRenderer, "Score: \u00a7" + scoresToDisplayColor.get(2) + scoresToDisplay.get(2), i, j + 10, k);
            } else if (2 == scoresToDisplay.size()) {
                this.drawTextWithShadowCentred(textRenderer, "Score: \u00a7" + scoresToDisplayColor.get(0) + scoresToDisplay.get(0), i, j - 5, k);
                this.drawTextWithShadowCentred(textRenderer, "Score: \u00a7" + scoresToDisplayColor.get(1) + scoresToDisplay.get(1), i, j + 5, k);
            } else if (1 == scoresToDisplay.size()) {
                this.drawTextWithShadowCentred(textRenderer, "Score: \u00a7" + scoresToDisplayColor.get(0) + scoresToDisplay.get(0), i, j, k);
            } else {
                this.drawTextWithShadowCentred(textRenderer, s, i, j, k);
            }
        } else {
            this.drawTextWithShadowCentred(textRenderer, s, i, j, k);
        }
    }
}