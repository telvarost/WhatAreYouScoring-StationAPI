package com.github.telvarost.whatareyouscoring.mixin;

import com.github.telvarost.whatareyouscoring.Config;
import com.github.telvarost.whatareyouscoring.ModHelper;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
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
        ArrayList scoresToDisplay = new ArrayList<Integer>();
        ArrayList scoresToDisplayColor = new ArrayList<Character>();

        if (Config.config.BASIC_SCORE_CONFIG.DISPLAY_BASIC_SCORE_ON_DEATH) {
            /** - Get basic score */
            int currentScore = ModHelper.ModHelperFields.DEATH_SCORE_BASIC_MODE;
            if (Config.config.SPECIAL_DEATH_EFFECT_ON_SCORE) {
                currentScore += ModHelper.ModHelperFields.PrevCumulativeBasicScore;
            }
            scoresToDisplay.add(currentScore);
            scoresToDisplayColor.add('e');
        }

        if (Config.config.DAYS_SCORE_CONFIG.DISPLAY_DAYS_SCORE_ON_DEATH) {
            /** - Get days survived */
            int currentScore = ModHelper.ModHelperFields.DEATH_SCORE_DAYS_MODE;
            if (Config.config.SPECIAL_DEATH_EFFECT_ON_SCORE) {
                currentScore += ModHelper.ModHelperFields.PrevCumulativeDaysScore;
            }
            scoresToDisplay.add(currentScore);
            scoresToDisplayColor.add('b');
        }

        if (Config.config.CHALLENGE_404_CONFIG.DISPLAY_404_CHALLENGE_SCORE_ON_DEATH) {
            /** - Get 404 challenge score */
            int currentScore = ModHelper.ModHelperFields.DEATH_SCORE_404_MODE;
            if (Config.config.SPECIAL_DEATH_EFFECT_ON_SCORE) {
                currentScore += ModHelper.ModHelperFields.PrevCumulative404Score;
            }
            scoresToDisplay.add(currentScore);
            scoresToDisplayColor.add('c');
        }

        if (3 == scoresToDisplay.size()) {
            instance.drawCenteredTextWithShadow(textRenderer, "Score: \u00a7" + scoresToDisplayColor.get(0) + scoresToDisplay.get(0), centerX, y - 10, color);
            instance.drawCenteredTextWithShadow(textRenderer, "Score: \u00a7" + scoresToDisplayColor.get(1) + scoresToDisplay.get(1), centerX, y, color);
            instance.drawCenteredTextWithShadow(textRenderer, "Score: \u00a7" + scoresToDisplayColor.get(2) + scoresToDisplay.get(2), centerX, y + 10, color);
        } else if (2 == scoresToDisplay.size()) {
            instance.drawCenteredTextWithShadow(textRenderer, "Score: \u00a7" + scoresToDisplayColor.get(0) + scoresToDisplay.get(0), centerX, y - 5, color);
            instance.drawCenteredTextWithShadow(textRenderer, "Score: \u00a7" + scoresToDisplayColor.get(1) + scoresToDisplay.get(1), centerX, y + 5, color);
        } else if (1 == scoresToDisplay.size()) {
            instance.drawCenteredTextWithShadow(textRenderer, "Score: \u00a7" + scoresToDisplayColor.get(0) + scoresToDisplay.get(0), centerX, y, color);
        } else {
            original.call(instance, textRenderer, text, centerX, y, color);
        }
    }
}