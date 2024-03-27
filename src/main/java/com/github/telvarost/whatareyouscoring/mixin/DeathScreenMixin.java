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
            /** - Get current basic score */
            int currentScore = 0;
            currentScore += ModHelper.ModHelperFields.BLOCKS_PLACED;
            currentScore += ModHelper.ModHelperFields.BLOCKS_REMOVED;
            currentScore += ModHelper.ModHelperFields.MONSTER_MOBS_KILLED;
            currentScore += ModHelper.ModHelperFields.PASSIVE_MOBS_KILLED;
            this.drawTextWithShadowCentred(textRenderer, "Score: \u00a7e" + currentScore, i, j, k);
        } else if (ScoreDisplayEnum.DAYS_SCORE == Config.config.SCORING_DISPLAY_TYPE) {
            /** - Get days survived */
            int currentScore = ModHelper.ModHelperFields.DAYS_SURVIVED;
            this.drawTextWithShadowCentred(textRenderer, "Score: \u00a7b" + currentScore, i, j, k);
        } else if (ScoreDisplayEnum.CHALLENGE_404 == Config.config.SCORING_DISPLAY_TYPE) {
            /** - Get current 404 challenge score */
            int currentScore = 0;
            this.drawTextWithShadowCentred(textRenderer, "Score: \u00a7c" + currentScore, i, j, k);
        } else {
            this.drawTextWithShadowCentred(textRenderer, s, i, j, k);
        }
    }
}