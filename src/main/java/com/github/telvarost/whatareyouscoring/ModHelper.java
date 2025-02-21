package com.github.telvarost.whatareyouscoring;

import net.minecraft.world.World;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class ModHelper {

    //public static final Namespace NAMESPACE = Namespace.resolve();
    @Entrypoint.Namespace
    public static Namespace NAMESPACE = Null.get();

    public static int calculateBasicScore() {
        int basicScore = 0;

        basicScore += ModHelper.ModHelperFields.BLOCKS_PLACED;
        basicScore += ModHelper.ModHelperFields.BLOCKS_REMOVED;
        basicScore += ModHelper.ModHelperFields.MONSTER_MOBS_KILLED;
        basicScore += ModHelper.ModHelperFields.PASSIVE_MOBS_KILLED;

        return basicScore;
    }

    public static int calculateDaysScore() {
        int daysScore = 0;

        int daysSurvived = (ModHelperFields.DAYS_PLAYED - ModHelperFields.LAST_DEATH_DAY);

        if (Config.config.DAYS_SCORE_CONFIG.ADD_SCORE_FOR_EACH_DAY_SURVIVED) {
            daysScore += daysSurvived;
        }

        if (Config.config.DAYS_SCORE_CONFIG.ADD_100_DAYS_BONUS_SCORE) {
            daysScore += ((daysSurvived / 100) * 25);
        }

        if (Config.config.DAYS_SCORE_CONFIG.ADD_YEAR_BONUS_SCORE) {
            daysScore += ((daysSurvived / 365) * 100);
        }

        return daysScore;
    }

    public static int calculate404ChallengeScore(World level) {
        double challenge404Score = 0;

        if (Config.config.CHALLENGE_404_CONFIG.SCORE_MOB_KILLS_404) {
            challenge404Score += (0.5 * ModHelper.ModHelperFields.ZOMBIE_KILLED);
            challenge404Score += (1 * ModHelper.ModHelperFields.SKELETON_KILLED);
            challenge404Score += (1 * ModHelper.ModHelperFields.SPIDER_KILLED);
            challenge404Score += (2 * ModHelper.ModHelperFields.CREEPER_KILLED);
            challenge404Score += (3 * ModHelper.ModHelperFields.ZOMBIE_PIGMAN_KILLED);
            challenge404Score += (6 * ModHelper.ModHelperFields.GHAST_KILLED);
        }

        if (15 <= ModHelper.ModHelperFields.WHEAT_BROKEN) {
            challenge404Score += 10;
        }

        if (32 <= ModHelper.ModHelperFields.CACTI_BROKEN) {
            challenge404Score += 4;
        }

        if (20 <= ModHelper.ModHelperFields.SUGAR_CANES_BROKEN) {
            challenge404Score += 4;
        }

        if (3 <= ModHelper.ModHelperFields.PUMPKINS_BROKEN) {
            challenge404Score += 2;
        }

        if (32 <= ModHelper.ModHelperFields.GLASS_PLACED) {
            challenge404Score += 5;
        }

        if (20 <= ModHelper.ModHelperFields.BRICKS_PLACED) {
            challenge404Score += 10;
        }

        if (8 <= ModHelper.ModHelperFields.WOOL_TYPES_PLACED) {
            challenge404Score += 10;
        }

        if (16 <= ModHelper.ModHelperFields.WOOL_TYPES_PLACED) {
            challenge404Score += 15;
        }

        if (0xC0 == ModHelper.ModHelperFields.BOW_AND_ARROW_CRAFTING_BITFIELD) {
            challenge404Score += 10;
        }

        if (0x0001 == (0x0001 & ModHelper.ModHelperFields.MISC_CRAFTING_BITFIELD)) {
            challenge404Score += 5;
        }

        if (0x0002 == (0x0002 & ModHelper.ModHelperFields.MISC_CRAFTING_BITFIELD)) {
            challenge404Score += 2;
        }

        if (0x0004 == (0x0004 & ModHelper.ModHelperFields.MISC_CRAFTING_BITFIELD)) {
            challenge404Score += 5;
        }

        if (0x0008 == (0x0008 & ModHelper.ModHelperFields.MISC_CRAFTING_BITFIELD)) {
            challenge404Score += 20;
        }

        if (0x0010 == (0x0010 & ModHelper.ModHelperFields.MISC_CRAFTING_BITFIELD)) {
            challenge404Score += 2;
        }

        if (0x0020 == (0x0020 & ModHelper.ModHelperFields.MISC_CRAFTING_BITFIELD)) {
            challenge404Score += 4;
        }

        if (0x000F == (0x000F & ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD)) {
            challenge404Score += 10;
        }

        if (0x00F0 == (0x00F0 & ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD)) {
            challenge404Score += 10;
        }

        if (0x0F00 == (0x0F00 & ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD)) {
            challenge404Score += 20;
        }

        if (0xF000 == (0xF000 & ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD)) {
            challenge404Score += 50;
        }

        if (0x0001 == (0x0001 & ModHelper.ModHelperFields.EXPLOSION_STATUS_BITFIELD)) {
            challenge404Score += 10;
        }

        if (0x0002 == (0x0002 & ModHelper.ModHelperFields.EXPLOSION_STATUS_BITFIELD)) {
            challenge404Score += 10;
        }

        if (0x0004 == (0x0004 & ModHelper.ModHelperFields.EXPLOSION_STATUS_BITFIELD)) {
            challenge404Score += 10;
        }

        if (0x0008 == (0x0008 & ModHelper.ModHelperFields.EXPLOSION_STATUS_BITFIELD)) {
            challenge404Score += 20;
        }

        if (ModHelperFields.HAS_PLAYER_SLEPT != (ModHelperFields.HAS_PLAYER_SLEPT & ModHelperFields.OTHER_BITFIELD)) {
            if (true == Config.config.CHALLENGE_404_CONFIG.POSITIVE_NEVER_SLEEP_WEAR_ARMOR) {
                challenge404Score += 15;
            }
        } else {
            if (false == Config.config.CHALLENGE_404_CONFIG.POSITIVE_NEVER_SLEEP_WEAR_ARMOR) {
                challenge404Score -= 15;
            }
        }

        if (ModHelperFields.HAS_PLAYER_WORN_ARMOR != (ModHelperFields.HAS_PLAYER_WORN_ARMOR & ModHelperFields.OTHER_BITFIELD)) {
            if (true == Config.config.CHALLENGE_404_CONFIG.POSITIVE_NEVER_SLEEP_WEAR_ARMOR) {
                challenge404Score += 15;
            }
        } else {
            if (false == Config.config.CHALLENGE_404_CONFIG.POSITIVE_NEVER_SLEEP_WEAR_ARMOR) {
                challenge404Score -= 15;
            }
        }

        if (ModHelperFields.HAS_OBSIDIAN_BEEN_BROKEN == (ModHelperFields.HAS_OBSIDIAN_BEEN_BROKEN & ModHelperFields.OTHER_BITFIELD)) {
            challenge404Score += 5;
        }

        if (ModHelperFields.HAS_PLAYER_EXITED_THE_NETHER == (ModHelperFields.HAS_PLAYER_EXITED_THE_NETHER & ModHelperFields.OTHER_BITFIELD)) {
            challenge404Score += 15;
        }

        if (ModHelperFields.HAS_OVERWORLD_GLOWSTONE_BEEN_PLACED == (ModHelperFields.HAS_OVERWORLD_GLOWSTONE_BEEN_PLACED & ModHelperFields.OTHER_BITFIELD)) {
            challenge404Score += 10;
        }

        if (ModHelperFields.HAS_CRASH_SLAB_BEEN_PLACED == (ModHelperFields.HAS_CRASH_SLAB_BEEN_PLACED & ModHelperFields.OTHER_BITFIELD)) {
            challenge404Score += 15;
        }

        if (Config.config.CHALLENGE_404_CONFIG.ENABLE_HARD_MODE_MULTIPLIER) {
            if (3 <= level.difficulty) {
                challenge404Score *= 1.5;
            }
        }

        return (int)Math.round(challenge404Score);
    }

    public static void resetFieldsOnDeath(World level, boolean isLevelLoad) {
        /** - Reset Basic Score Fields */
        if (isLevelLoad) {
            ModHelperFields.CumulativeBasicScore = 0;
            ModHelperFields.DEATH_SCORE_BASIC_MODE = 0;
        } else {
            ModHelperFields.DEATH_SCORE_BASIC_MODE = calculateBasicScore();
        }
        ModHelperFields.BLOCKS_PLACED = 0;
        ModHelperFields.BLOCKS_REMOVED = 0;
        ModHelperFields.MONSTER_MOBS_KILLED = 0;
        ModHelperFields.PASSIVE_MOBS_KILLED = 0;

        /** - Reset Days Score Fields */
        if (isLevelLoad) {
            ModHelperFields.CumulativeDaysScore = 0;
            ModHelperFields.DEATH_SCORE_DAYS_MODE = 0;
            ModHelperFields.LAST_DEATH_DAY = 0;
            ModHelperFields.DAYS_PLAYED = 0;
            ModHelperFields.PREV_DAYS_PLAYED = 0;
        } else {
            ModHelperFields.DEATH_SCORE_DAYS_MODE = calculateDaysScore();
            ModHelperFields.LAST_DEATH_DAY = (int) Math.floor(level.getProperties().getTime() / 24000);
        }

        /** - Reset 404 Challenge Score Fields */
        if (isLevelLoad) {
            ModHelperFields.Cumulative404Score = 0;
            ModHelperFields.DEATH_SCORE_404_MODE = 0;
        } else {
            ModHelperFields.DEATH_SCORE_404_MODE = calculate404ChallengeScore(level);
        }
        ModHelperFields.ZOMBIE_KILLED = 0;
        ModHelperFields.SKELETON_KILLED = 0;
        ModHelperFields.SPIDER_KILLED = 0;
        ModHelperFields.CREEPER_KILLED = 0;
        ModHelperFields.GHAST_KILLED = 0;
        ModHelperFields.ZOMBIE_PIGMAN_KILLED = 0;
        ModHelperFields.WHEAT_BROKEN = 0;
        ModHelperFields.CACTI_BROKEN = 0;
        ModHelperFields.SUGAR_CANES_BROKEN = 0;
        ModHelperFields.PUMPKINS_BROKEN = 0;
        ModHelperFields.GLASS_PLACED = 0;
        ModHelperFields.BRICKS_PLACED = 0;
        ModHelperFields.WOOL_TYPES_PLACED = 0;
        ModHelperFields.WOOL_PLACED_BITFIELD = 0;
        ModHelperFields.BOW_AND_ARROW_CRAFTING_BITFIELD = 0;
        ModHelperFields.MISC_CRAFTING_BITFIELD = 0;
        ModHelperFields.ARMOR_CRAFTING_BITFIELD = 0;
        ModHelperFields.EXPLOSION_STATUS_BITFIELD = 0;
        ModHelperFields.OTHER_BITFIELD = 0;
    }

    public static class ModHelperFields {
        public static int ACHIEVEMENT_ID = 11500;
        public static Integer DETECT_CREEPER_EXPLOSION = 0;

        /** - Basic helper fields */
        public static Integer PrevCumulativeBasicScore = 0;
        public static Integer CumulativeBasicScore = 0;
        public static Integer DEATH_SCORE_BASIC_MODE = 0;
        public static Integer BLOCKS_PLACED = 0;
        public static Integer BLOCKS_REMOVED = 0;
        public static Integer MONSTER_MOBS_KILLED = 0;
        public static Integer PASSIVE_MOBS_KILLED = 0;

        /** - Days helper fields */
        public static Integer PrevCumulativeDaysScore = 0;
        public static Integer CumulativeDaysScore = 0;
        public static Integer DEATH_SCORE_DAYS_MODE = 0;
        public static Integer LAST_DEATH_DAY = 0;
        public static Integer DAYS_PLAYED = 0;
        public static Integer PREV_DAYS_PLAYED = 0;

        /** - 404 Challenge helper fields */
        public static Integer PrevCumulative404Score = 0;
        public static Integer Cumulative404Score = 0;
        public static Integer DEATH_SCORE_404_MODE = 0;
        public static Integer ZOMBIE_KILLED = 0;
        public static Integer SKELETON_KILLED = 0;
        public static Integer SPIDER_KILLED = 0;
        public static Integer CREEPER_KILLED = 0;
        public static Integer GHAST_KILLED = 0;
        public static Integer ZOMBIE_PIGMAN_KILLED = 0;
        public static Integer WHEAT_BROKEN = 0;
        public static Integer CACTI_BROKEN = 0;
        public static Integer SUGAR_CANES_BROKEN = 0;
        public static Integer PUMPKINS_BROKEN = 0;
        public static Integer GLASS_PLACED = 0;
        public static Integer BRICKS_PLACED = 0;
        public static Integer WOOL_TYPES_PLACED = 0;
        public static Integer WOOL_PLACED_BITFIELD = 0x0000;
        public static Integer BOW_AND_ARROW_CRAFTING_BITFIELD = 0x0000;
        public static Integer MISC_CRAFTING_BITFIELD = 0x0000;
        public static Integer ARMOR_CRAFTING_BITFIELD = 0x0000;
        public static Integer EXPLOSION_STATUS_BITFIELD = 0x0000;
        public static Integer OTHER_BITFIELD = 0x0000;
        public static final Integer HAS_PLAYER_SLEPT = 0x0001;
        public static final Integer HAS_PLAYER_WORN_ARMOR = 0x0002;
        public static final Integer HAS_OBSIDIAN_BEEN_BROKEN = 0x0004;
        public static final Integer HAS_PLAYER_EXITED_THE_NETHER = 0x0008;
        public static final Integer HAS_OVERWORLD_GLOWSTONE_BEEN_PLACED = 0x0010;
        public static final Integer HAS_CRASH_SLAB_BEEN_PLACED = 0x0020;
        public static Boolean IS_PLAYER_IN_NETHER = false;
    }
}
