package com.github.telvarost.whatareyouscoring.achievement;

import com.github.telvarost.whatareyouscoring.ModHelper;
import com.github.telvarost.whatareyouscoring.mixin.AchievementAccessor;
import com.github.telvarost.whatareyouscoring.mixin.MinecraftAccessor;
import net.minecraft.achievement.Achievement;
import net.minecraft.block.BlockBase;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemBase;
import net.minecraft.stat.Stats;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WaysDaysAchievements {
    public static final List<Achievement> ACHIEVEMENTS = new ArrayList<>();

    public static final Achievement START_DAYS = make("start_days", ItemBase.book, 0, 0, null, false);
    public static final Achievement MINECRAFT_DAY = make("minecraft_day", ItemBase.ironIngot, 0, 2, START_DAYS, false);
    public static final Achievement MINECRAFT_100 = make("minecraft_100_days", ItemBase.goldIngot, -2, 2, MINECRAFT_DAY, true);
    public static final Achievement MINECRAFT_YEAR = make("minecraft_year", ItemBase.diamond, -2, 0, MINECRAFT_100, true);
    public static final Achievement REAL_DAY = make("real_day", BlockBase.IRON_BLOCK, 0, -2, START_DAYS, false);
    public static final Achievement REAL_100 = make("real_100_days", BlockBase.GOLD_BLOCK, 2, -2, REAL_DAY, true);
    public static final Achievement REAL_YEAR = make("real_year", BlockBase.DIAMOND_BLOCK, 2, 0, REAL_100, true);

    private static Achievement make(String name, BlockBase icon, int x, int y, Achievement parent, boolean isChallenge) {
        Achievement achievement = new Achievement(ModHelper.ModHelperFields.ACHIEVEMENT_ID++, "whatareyouscoring:" + name, x, y, icon, parent);
        if (isChallenge) {
            achievement.setUnusual();
        }
        ACHIEVEMENTS.add(achievement);
        return achievement;
    }

    private static Achievement make(String name, ItemBase icon, int x, int y, Achievement parent, boolean isChallenge) {
        Achievement achievement = new Achievement(ModHelper.ModHelperFields.ACHIEVEMENT_ID++, "whatareyouscoring:" + name, x, y, icon, parent);
        if (isChallenge) {
            achievement.setUnusual();
        }
        ACHIEVEMENTS.add(achievement);
        return achievement;
    }

    public static void updateAchievementCounts() {
        ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(MINECRAFT_DAY))).setAchievementDescription("Count: " + ModHelper.ModHelperFields.DAYS_SURVIVED);
        ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(MINECRAFT_100))).setAchievementDescription("Count: " + (int)Math.floor(ModHelper.ModHelperFields.DAYS_SURVIVED / 100));
        ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(MINECRAFT_YEAR))).setAchievementDescription("Count: " + (int)Math.floor(ModHelper.ModHelperFields.DAYS_SURVIVED / 365));
        Minecraft minecraft = MinecraftAccessor.getInstance();
        long realDaysPlayed = Duration.ofSeconds(minecraft.statFileWriter.write(Stats.playOneMinute) / 20).toDays();
        ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(REAL_DAY))).setAchievementDescription("Count: " + realDaysPlayed);
    }
}
