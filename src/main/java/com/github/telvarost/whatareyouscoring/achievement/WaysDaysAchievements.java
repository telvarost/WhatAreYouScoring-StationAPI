package com.github.telvarost.whatareyouscoring.achievement;

import com.github.telvarost.whatareyouscoring.ModHelper;
import com.github.telvarost.whatareyouscoring.mixin.AchievementAccessor;
import net.minecraft.achievement.Achievement;
import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemBase;

import java.util.ArrayList;
import java.util.List;

public class WaysDaysAchievements {
    public static final List<Achievement> ACHIEVEMENTS = new ArrayList<>();

    public static final Achievement START_DAYS = make("start_days", ItemBase.ironIngot, 0, 0, null, false);
    public static final Achievement REAL_DAY = make("real_day", BlockBase.IRON_BLOCK, 0, 1, START_DAYS, false);

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

    public static void modifyThisGuy() {
        ((AchievementAccessor) ACHIEVEMENTS.get(0)).setAchievementDescription("Cooked: " + ModHelper.ModHelperFields.TEST_ONE_TWO_THREE);
    }
}
