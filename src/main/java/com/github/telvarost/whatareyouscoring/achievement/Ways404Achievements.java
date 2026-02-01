package com.github.telvarost.whatareyouscoring.achievement;

import com.github.telvarost.whatareyouscoring.Config;
import com.github.telvarost.whatareyouscoring.ModHelper;
import com.github.telvarost.whatareyouscoring.mixin.AchievementAccessor;
import net.minecraft.achievement.Achievement;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.entity.player.PlayerHelper;

import java.util.ArrayList;
import java.util.List;

public class Ways404Achievements {
    public static final List<Achievement> ACHIEVEMENTS = new ArrayList<>();

    public static final Achievement START_404 = make("start_404", Item.BOOK, 0, 0, null, false);

    public static final Achievement CRAFT_BOW_AND_ARROWS = make("craft_bow_and_arrows", Item.BOW, 2, -1, START_404, false);
    public static final Achievement CRAFT_BREAD = make("craft_bread", Item.BREAD, 2, -2, START_404, false);
    public static final Achievement CRAFT_JACK_O_LANTERN = make("craft_jack_o_lantern", Block.JACK_O_LANTERN, 2, -3, START_404, false);

    public static final Achievement LEATHER_ARMOR = make("leather_armor", Item.LEATHER_CHESTPLATE, 3, 0, START_404, false);
    public static final Achievement IRON_ARMOR = make("iron_armor", Item.IRON_CHESTPLATE, 3, -1, START_404, false);
    public static final Achievement GOLD_ARMOR = make("gold_armor", Item.GOLDEN_CHESTPLATE, 3, -2, START_404, false);
    public static final Achievement DIAMOND_ARMOR = make("diamond_armor", Item.DIAMOND_CHESTPLATE, 3, -3, START_404, false);
    public static final Achievement CRAFT_LAPIS_LAZULI_BLOCK = make("craft_lapis_lazuli_block", Block.LAPIS_BLOCK, 4, 0, START_404, false);
    public static final Achievement CRAFT_IRON_BLOCK = make("craft_iron_block", Block.IRON_BLOCK, 4, -1, START_404, false);
    public static final Achievement CRAFT_GOLD_BLOCK = make("craft_gold_block", Block.GOLD_BLOCK, 4, -2, START_404, false);
    public static final Achievement CRAFT_DIAMOND_BLOCK = make("craft_diamond_block", Block.DIAMOND_BLOCK, 4, -3, START_404, false);
    public static final Achievement CREEPER_LAPIS_LAZULI_BLOCK = make("creeper_lapis_lazuli_block", Item.DYE, 5, 0, CRAFT_LAPIS_LAZULI_BLOCK, false);
    public static final Achievement CREEPER_IRON_BLOCK = make("creeper_iron_block", Item.IRON_INGOT, 5, -1, CRAFT_IRON_BLOCK, false);
    public static final Achievement CREEPER_GOLD_BLOCK = make("creeper_gold_block", Item.GOLD_INGOT, 5, -2, CRAFT_GOLD_BLOCK, false);
    public static final Achievement CREEPER_DIAMOND_BLOCK = make("creeper_diamond_block", Item.DIAMOND, 5, -3, CRAFT_DIAMOND_BLOCK, true);

    public static final Achievement NEVER_SLEEP = make("never_sleep", Item.BED, 4, 2, START_404, true);
    public static final Achievement NEVER_WEAR_ARMOR = make("never_wear_armor", Item.CHAIN_CHESTPLATE, 5, 2, START_404, true);

    public static final Achievement ZOMBIE_KILLED = make("zombie_killed", Item.FEATHER, 2, 2, START_404, false);
    public static final Achievement SKELETON_KILLED = make("skeleton_killed", Item.BONE, 1, 2, START_404, false);
    public static final Achievement SPIDER_KILLED = make("spider_killed", Item.STRING, 0, 2, START_404, false);
    public static final Achievement CREEPER_KILLED = make("creeper_killed", Item.GUNPOWDER, -1, 2, START_404, false);
    public static final Achievement ZOMBIE_PIGMAN_KILLED = make("zombie_pigman_killed", Item.COOKED_PORKCHOP, -2, 2, START_404, false);
    public static final Achievement GHAST_KILLED = make("ghast_killed", Item.SNOWBALL, -3, 2, START_404, true);

    public static final Achievement PLACE_8_TYPES_OF_WOOL = make("place_8_types_of_wool", Block.WOOL, 0, -2, START_404, false);
    public static final Achievement PLACE_ALL_TYPES_OF_WOOL = make("place_all_types_of_wool", Block.ROSE, -1, -2, START_404, false);
    public static final Achievement PLACE_32_GLASS = make("place_32_glass", Block.GLASS, 0, -3, START_404, false);
    public static final Achievement PLACE_20_BRICKS = make("place_20_bricks", Block.BRICKS, -1, -3, START_404, false);
    public static final Achievement PLACE_A_CRASH_SLAB = make("place_a_crash_slab", Block.SLAB, -2, -3, START_404, true);
    public static final Achievement BREAK_20_SUGAR_CANES = make("break_20_sugar_canes", Item.SUGAR_CANE, -2, 0, START_404, false);
    public static final Achievement BREAK_32_CACTI = make("break_32_cacti", Block.CACTUS, -2, -1, START_404, false);
    public static final Achievement BREAK_15_WHEAT = make("break_15_wheat", Item.WHEAT, -2, -2, START_404, false);
    public static final Achievement BREAK_3_PUMPKINS = make("break_3_pumpkins", Block.PUMPKIN, -3, 0, START_404, false);
    public static final Achievement BREAK_AN_OBSIDIAN_BLOCK = make("break_an_obsidian_block", Block.OBSIDIAN, -3, -1, START_404, false);
    public static final Achievement EXIT_THE_NETHER = make("exit_the_nether", Block.NETHER_PORTAL, -3, -2, BREAK_AN_OBSIDIAN_BLOCK, false);
    public static final Achievement PLACE_GLOWSTONE_IN_THE_OVERWORLD = make("place_glowstone_in_the_overworld", Block.GLOWSTONE, -3, -3, EXIT_THE_NETHER, true);

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
        if (Config.config.SCORE_RENDERING_CONFIG.DISPLAY_SCORE_ON_BEGIN_ACHIEVEMENT) {
            PlayerEntity player = PlayerHelper.getPlayerFromGame();
            if ((null != player) && (null != player.world)) {
                /** - Get 404 challenge score */
                int currentScore = ModHelper.calculate404ChallengeScore(player.world);
                if (Config.config.SPECIAL_DEATH_EFFECT_ON_SCORE) {
                    currentScore += ModHelper.ModHelperFields.PrevCumulative404Score;
                }
                ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(START_404))).setAchievementDescription("Scores certain aspects of the game for the 404 challenge.\n\nScore: " + currentScore);
            } else {
                ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(START_404))).setAchievementDescription("Scores certain aspects of the game for the 404 challenge.\nCurrent Score: N/A");
            }
        } else {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(START_404))).setAchievementDescription("Scores certain aspects of the game for the 404 challenge.");
        }

        ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(ZOMBIE_KILLED))).setAchievementDescription("Count: " + ModHelper.ModHelperFields.ZOMBIE_KILLED);
        ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(SKELETON_KILLED))).setAchievementDescription("Count: " + ModHelper.ModHelperFields.SKELETON_KILLED);
        ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(SPIDER_KILLED))).setAchievementDescription("Count: " + ModHelper.ModHelperFields.SPIDER_KILLED);
        ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(CREEPER_KILLED))).setAchievementDescription("Count: " + ModHelper.ModHelperFields.CREEPER_KILLED);
        ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(GHAST_KILLED))).setAchievementDescription("Count: " + ModHelper.ModHelperFields.GHAST_KILLED);
        ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(ZOMBIE_PIGMAN_KILLED))).setAchievementDescription("Count: " + ModHelper.ModHelperFields.ZOMBIE_PIGMAN_KILLED);

        if (15 <= ModHelper.ModHelperFields.WHEAT_BROKEN) {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(BREAK_15_WHEAT))).setAchievementDescription("Break 15 wheat. [Completed!]");
        } else {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(BREAK_15_WHEAT))).setAchievementDescription("Break 15 wheat. [Incomplete]");
        }

        if (32 <= ModHelper.ModHelperFields.CACTI_BROKEN) {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(BREAK_32_CACTI))).setAchievementDescription("Break 32 cacti. [Completed!]");
        } else {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(BREAK_32_CACTI))).setAchievementDescription("Break 32 cacti. [Incomplete]");
        }

        if (20 <= ModHelper.ModHelperFields.SUGAR_CANES_BROKEN) {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(BREAK_20_SUGAR_CANES))).setAchievementDescription("Break 20 sugar canes. [Completed!]");
        } else {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(BREAK_20_SUGAR_CANES))).setAchievementDescription("Break 20 sugar canes. [Incomplete]");
        }

        if (3 <= ModHelper.ModHelperFields.PUMPKINS_BROKEN) {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(BREAK_3_PUMPKINS))).setAchievementDescription("Break 3 pumpkins. [Completed!]");
        } else {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(BREAK_3_PUMPKINS))).setAchievementDescription("Break 3 pumpkins. [Incomplete]");
        }

        if (32 <= ModHelper.ModHelperFields.GLASS_PLACED) {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(PLACE_32_GLASS))).setAchievementDescription("Place 32 glass. [Completed!]");
        } else {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(PLACE_32_GLASS))).setAchievementDescription("Place 32 glass. [Incomplete]");
        }

        if (20 <= ModHelper.ModHelperFields.BRICKS_PLACED) {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(PLACE_20_BRICKS))).setAchievementDescription("Place 20 bricks. [Completed!]");
        } else {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(PLACE_20_BRICKS))).setAchievementDescription("Place 20 bricks. [Incomplete]");
        }

        if (8 <= ModHelper.ModHelperFields.WOOL_TYPES_PLACED) {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(PLACE_8_TYPES_OF_WOOL))).setAchievementDescription("Place 8 different wool types. [Completed!]");
        } else {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(PLACE_8_TYPES_OF_WOOL))).setAchievementDescription("Place 8 different wool types. [Incomplete]");
        }

        if (16 <= ModHelper.ModHelperFields.WOOL_TYPES_PLACED) {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(PLACE_ALL_TYPES_OF_WOOL))).setAchievementDescription("Place all different wool types. [Completed!]");
        } else {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(PLACE_ALL_TYPES_OF_WOOL))).setAchievementDescription("Place all different wool types. [Incomplete]");
        }

        if (0xC0 == ModHelper.ModHelperFields.BOW_AND_ARROW_CRAFTING_BITFIELD) {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(CRAFT_BOW_AND_ARROWS))).setAchievementDescription("Craft a bow and 64 arrows. [Completed!]");
        } else {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(CRAFT_BOW_AND_ARROWS))).setAchievementDescription("Craft a bow and 64 arrows. [Incomplete]");
        }

        if (0x0001 == (0x0001 & ModHelper.ModHelperFields.MISC_CRAFTING_BITFIELD)) {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(CRAFT_LAPIS_LAZULI_BLOCK))).setAchievementDescription("Craft a lapiz lazuli block. [Completed!]");
        } else {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(CRAFT_LAPIS_LAZULI_BLOCK))).setAchievementDescription("Craft a lapiz lazuli block. [Incomplete]");
        }

        if (0x0002 == (0x0002 & ModHelper.ModHelperFields.MISC_CRAFTING_BITFIELD)) {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(CRAFT_IRON_BLOCK))).setAchievementDescription("Craft an iron block. [Completed!]");
        } else {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(CRAFT_IRON_BLOCK))).setAchievementDescription("Craft an iron block. [Incomplete]");
        }

        if (0x0004 == (0x0004 & ModHelper.ModHelperFields.MISC_CRAFTING_BITFIELD)) {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(CRAFT_GOLD_BLOCK))).setAchievementDescription("Craft a gold block. [Completed!]");
        } else {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(CRAFT_GOLD_BLOCK))).setAchievementDescription("Craft a gold block. [Incomplete]");
        }

        if (0x0008 == (0x0008 & ModHelper.ModHelperFields.MISC_CRAFTING_BITFIELD)) {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(CRAFT_DIAMOND_BLOCK))).setAchievementDescription("Craft a diamond block. [Completed!]");
        } else {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(CRAFT_DIAMOND_BLOCK))).setAchievementDescription("Craft a diamond block. [Incomplete]");
        }

        if (0x0010 == (0x0010 & ModHelper.ModHelperFields.MISC_CRAFTING_BITFIELD)) {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(CRAFT_JACK_O_LANTERN))).setAchievementDescription("Craft a jack o' lantern. [Completed!]");
        } else {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(CRAFT_JACK_O_LANTERN))).setAchievementDescription("Craft a jack o' lantern. [Incomplete]");
        }

        if (0x0020 == (0x0020 & ModHelper.ModHelperFields.MISC_CRAFTING_BITFIELD)) {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(CRAFT_BREAD))).setAchievementDescription("Craft bread. [Completed!]");
        } else {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(CRAFT_BREAD))).setAchievementDescription("Craft bread. [Incomplete]");
        }

        if (0x000F == (0x000F & ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD)) {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(LEATHER_ARMOR))).setAchievementDescription("Craft a full set of leather armor. [Completed!]");
        } else {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(LEATHER_ARMOR))).setAchievementDescription("Craft a full set of leather armor. [Incomplete]");
        }

        if (0x00F0 == (0x00F0 & ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD)) {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(IRON_ARMOR))).setAchievementDescription("Craft a full set of iron armor. [Completed!]");
        } else {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(IRON_ARMOR))).setAchievementDescription("Craft a full set of iron armor. [Incomplete]");
        }

        if (0x0F00 == (0x0F00 & ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD)) {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(GOLD_ARMOR))).setAchievementDescription("Craft a full set of gold armor. [Completed!]");
        } else {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(GOLD_ARMOR))).setAchievementDescription("Craft a full set of gold armor. [Incomplete]");
        }

        if (0xF000 == (0xF000 & ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD)) {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(DIAMOND_ARMOR))).setAchievementDescription("Craft a full set of diamond armor. [Completed!]");
        } else {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(DIAMOND_ARMOR))).setAchievementDescription("Craft a full set of diamond armor. [Incomplete]");
        }

        if (0x0001 == (0x0001 & ModHelper.ModHelperFields.EXPLOSION_STATUS_BITFIELD)) {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(CREEPER_IRON_BLOCK))).setAchievementDescription("Have a creeper destroy an iron block. [Completed!]");
        } else {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(CREEPER_IRON_BLOCK))).setAchievementDescription("Have a creeper destroy an iron block. [Incomplete]");
        }

        if (0x0002 == (0x0002 & ModHelper.ModHelperFields.EXPLOSION_STATUS_BITFIELD)) {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(CREEPER_GOLD_BLOCK))).setAchievementDescription("Have a creeper destroy a gold block. [Completed!]");
        } else {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(CREEPER_GOLD_BLOCK))).setAchievementDescription("Have a creeper destroy a gold block. [Incomplete]");
        }

        if (0x0004 == (0x0004 & ModHelper.ModHelperFields.EXPLOSION_STATUS_BITFIELD)) {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(CREEPER_LAPIS_LAZULI_BLOCK))).setAchievementDescription("Have a creeper destroy a lapis lazuli block. [Completed!]");
        } else {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(CREEPER_LAPIS_LAZULI_BLOCK))).setAchievementDescription("Have a creeper destroy a lapis lazuli block. [Incomplete]");
        }

        if (0x0008 == (0x0008 & ModHelper.ModHelperFields.EXPLOSION_STATUS_BITFIELD)) {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(CREEPER_DIAMOND_BLOCK))).setAchievementDescription("Have a creeper destroy a diamond block. [Completed!]");
        } else {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(CREEPER_DIAMOND_BLOCK))).setAchievementDescription("Have a creeper destroy a diamond block. [Incomplete]");
        }

        if (0x0001 == (0x0001 & ModHelper.ModHelperFields.OTHER_BITFIELD)) {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(NEVER_SLEEP))).setAchievementDescription("Never sleep. [Failed]");
        } else {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(NEVER_SLEEP))).setAchievementDescription("Never sleep. [Success!]");
        }

        if (0x0002 == (0x0002 & ModHelper.ModHelperFields.OTHER_BITFIELD)) {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(NEVER_WEAR_ARMOR))).setAchievementDescription("Never wear armor. [Failed]");
        } else {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(NEVER_WEAR_ARMOR))).setAchievementDescription("Never wear armor. [Success!]");
        }

        if (0x0004 == (0x0004 & ModHelper.ModHelperFields.OTHER_BITFIELD)) {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(BREAK_AN_OBSIDIAN_BLOCK))).setAchievementDescription("Break an obsidian block. [Completed!]");
        } else {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(BREAK_AN_OBSIDIAN_BLOCK))).setAchievementDescription("Break an obsidian block. [Incomplete]");
        }

        if (0x0008 == (0x0008 & ModHelper.ModHelperFields.OTHER_BITFIELD)) {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(EXIT_THE_NETHER))).setAchievementDescription("Exit the nether. [Completed!]");
        } else {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(EXIT_THE_NETHER))).setAchievementDescription("Exit the nether. [Incomplete]");
        }

        if (0x0010 == (0x0010 & ModHelper.ModHelperFields.OTHER_BITFIELD)) {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(PLACE_GLOWSTONE_IN_THE_OVERWORLD))).setAchievementDescription("Place a glowstone block in the overworld. [Completed!]");
        } else {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(PLACE_GLOWSTONE_IN_THE_OVERWORLD))).setAchievementDescription("Place a glowstone block in the overworld. [Incomplete]");
        }

        if (0x0020 == (0x0020 & ModHelper.ModHelperFields.OTHER_BITFIELD)) {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(PLACE_A_CRASH_SLAB))).setAchievementDescription("Place a crash slab. [Completed!]");
        } else {
            ((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(PLACE_A_CRASH_SLAB))).setAchievementDescription("Place a crash slab. [Incomplete]");
        }
    }
}
