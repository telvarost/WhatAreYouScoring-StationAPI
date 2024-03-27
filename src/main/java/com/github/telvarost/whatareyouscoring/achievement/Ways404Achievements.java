package com.github.telvarost.whatareyouscoring.achievement;

import com.github.telvarost.whatareyouscoring.ModHelper;
import com.github.telvarost.whatareyouscoring.mixin.AchievementAccessor;
import net.minecraft.achievement.Achievement;
import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemBase;

import java.util.ArrayList;
import java.util.List;

public class Ways404Achievements {
    public static final List<Achievement> ACHIEVEMENTS = new ArrayList<>();

    public static final Achievement START_404 = make("start_404", ItemBase.book, 0, 0, null, false);
    public static final Achievement ZOMBIE_KILLED = make("zombie_killed", ItemBase.feather, -2, 2, START_404, false);
    public static final Achievement SKELETON_KILLED = make("skeleton_killed", ItemBase.bone, -1, 2, START_404, false);
    public static final Achievement SPIDER_KILLED = make("spider_killed", ItemBase.string, 0, 2, START_404, false);
    public static final Achievement CREEPER_KILLED = make("creeper_killed", ItemBase.gunpowder, 1, 2, START_404, false);
    public static final Achievement GHAST_KILLED = make("ghast_killed", ItemBase.snowball, 2, 2, START_404, false);
    public static final Achievement ZOMBIE_PIGMAN_KILLED = make("zombie_pigman_killed", ItemBase.cookedPorkchop, 3, 2, START_404, false);

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
