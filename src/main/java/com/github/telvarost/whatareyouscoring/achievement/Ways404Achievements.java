package com.github.telvarost.whatareyouscoring.achievement;

import com.github.telvarost.whatareyouscoring.ModHelper;
import com.github.telvarost.whatareyouscoring.mixin.AchievementAccessor;
import net.minecraft.achievement.Achievement;
import net.minecraft.block.BlockBase;
import net.minecraft.item.Block;
import net.minecraft.item.ItemBase;

import java.util.ArrayList;
import java.util.List;

public class Ways404Achievements {
    public static final List<Achievement> ACHIEVEMENTS = new ArrayList<>();

    public static final Achievement START_404 = make("start_404", ItemBase.book, 0, 0, null, false);
    public static final Achievement LEATHER_ARMOR = make("leather_armor", ItemBase.leatherChestplate, -1, 1, START_404, false);
    public static final Achievement IRON_ARMOR = make("iron_armor", ItemBase.ironChestplate, -1, 0, START_404, false);
    public static final Achievement GOLD_ARMOR = make("gold_armor", ItemBase.goldChestplate, -1, -1, START_404, false);
    public static final Achievement DIAMOND_ARMOR = make("diamond_armor", ItemBase.diamondChestplate, -1, -2, START_404, false);
    public static final Achievement CRAFT_LAPIS_LAZULI_BLOCK = make("craft_lapis_lazuli_block", BlockBase.LAPIS_LAZULI_BLOCK, -2, 1, START_404, false);
    public static final Achievement CRAFT_IRON_BLOCK = make("craft_iron_block", BlockBase.IRON_BLOCK, -2, 0, START_404, false);
    public static final Achievement CRAFT_GOLD_BLOCK = make("craft_gold_block", BlockBase.GOLD_BLOCK, -2, -1, START_404, false);
    public static final Achievement CRAFT_DIAMOND_BLOCK = make("craft_diamond_block", BlockBase.DIAMOND_BLOCK, -2, -2, START_404, false);
    public static final Achievement CREEPER_LAPIS_LAZULI_BLOCK = make("creeper_lapis_lazuli_block", ItemBase.dyePowder, -3, 1, CRAFT_LAPIS_LAZULI_BLOCK, false);
    public static final Achievement CREEPER_IRON_BLOCK = make("creeper_iron_block", ItemBase.ironIngot, -3, 0, CRAFT_IRON_BLOCK, false);
    public static final Achievement CREEPER_GOLD_BLOCK = make("creeper_gold_block", ItemBase.goldIngot, -3, -1, CRAFT_GOLD_BLOCK, false);
    public static final Achievement CREEPER_DIAMOND_BLOCK = make("creeper_diamond_block", ItemBase.diamond, -3, -2, CRAFT_DIAMOND_BLOCK, true);
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
