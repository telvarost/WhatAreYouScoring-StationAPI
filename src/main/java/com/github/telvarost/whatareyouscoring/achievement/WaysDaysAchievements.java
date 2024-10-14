package com.github.telvarost.whatareyouscoring.achievement;

import com.github.telvarost.whatareyouscoring.Config;
import com.github.telvarost.whatareyouscoring.ModHelper;
import com.github.telvarost.whatareyouscoring.mixin.AchievementAccessor;
import com.github.telvarost.whatareyouscoring.mixin.MinecraftAccessor;
import net.minecraft.achievement.Achievement;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.stat.Stats;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WaysDaysAchievements {
    public static final List<Achievement> ACHIEVEMENTS = new ArrayList<>();

    public static final Achievement START_DAYS = make("start_days", Item.BOOK, 0, 0, null, false);
    public static final Achievement MINECRAFT_DAY = make("minecraft_day", Item.IRON_INGOT, 0, 2, START_DAYS, false);
    public static final Achievement MINECRAFT_100 = make("minecraft_100_days", Item.GOLD_INGOT, -2, 2, MINECRAFT_DAY, true);
    public static final Achievement MINECRAFT_YEAR = make("minecraft_year", Item.DIAMOND, -2, 0, MINECRAFT_100, true);
    public static final Achievement REAL_DAY = make("real_day", Block.IRON_BLOCK, 0, -2, START_DAYS, false);
    public static final Achievement REAL_10_DAYS = make("real_10_days", Block.GOLD_BLOCK, 2, -2, REAL_DAY, true);
    public static final Achievement REAL_100_DAYS = make("real_100_days", Block.DIAMOND_BLOCK, 2, 0, REAL_10_DAYS, true);

    private static Achievement make(String name, Block icon, int x, int y, Achievement parent, boolean isChallenge) {
        Achievement achievement = new Achievement(ModHelper.ModHelperFields.ACHIEVEMENT_ID++, "whatareyouscoring." + name, x, y, icon, parent);
        if (isChallenge) {
            achievement.challenge();
        }
        ACHIEVEMENTS.add(achievement);
        return achievement;
    }

    private static Achievement make(String name, Item icon, int x, int y, Achievement parent, boolean isChallenge) {
        Achievement achievement = new Achievement(ModHelper.ModHelperFields.ACHIEVEMENT_ID++, "whatareyouscoring." + name, x, y, icon, parent);
        if (isChallenge) {
            achievement.challenge();
        }
        ACHIEVEMENTS.add(achievement);
        return achievement;
    }

    public static void updateAchievementCounts() {
        if (Config.config.DISPLAY_SCORE_ON_BEGIN_ACHIEVEMENT) {
            /** - Get basic score */
            int currentScore = ModHelper.calculateDaysScore();
            if (Config.config.SPECIAL_DEATH_EFFECT_ON_SCORE) {
                currentScore += ModHelper.ModHelperFields.PrevCumulativeDaysScore;
            }
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(START_DAYS))).setAchievementDescription("Scores each in-game day survived (and tracks real world days).\n\nScore: " + currentScore);
        } else {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(START_DAYS))).setAchievementDescription("Scores each in-game day survived (and tracks real world days).");
        }

        int daysSurvived = (ModHelper.ModHelperFields.DAYS_PLAYED - ModHelper.ModHelperFields.LAST_DEATH_DAY);
        ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(MINECRAFT_DAY))).setAchievementDescription("Current: " + daysSurvived);
        ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(MINECRAFT_100))).setAchievementDescription("Current: " + (int)Math.floor(daysSurvived / 100));
        ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(MINECRAFT_YEAR))).setAchievementDescription("Current: " + (int)Math.floor(daysSurvived / 365));
        Minecraft minecraft = MinecraftAccessor.getInstance();
        long realDaysPlayed = Duration.ofSeconds(minecraft.stats.get(Stats.PLAY_ONE_MINUTE) / 20).toDays();
        ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(REAL_DAY))).setAchievementDescription("Count: " + realDaysPlayed);
    }
}
