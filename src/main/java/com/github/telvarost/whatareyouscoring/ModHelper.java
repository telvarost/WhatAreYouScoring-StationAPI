package com.github.telvarost.whatareyouscoring;

import net.minecraft.level.Level;

public class ModHelper {

    public static void resetFieldsOnDeath(Level level) {
        /** - Reset Basic Score Fields */
        ModHelperFields.BLOCKS_PLACED = 0;
        ModHelperFields.BLOCKS_REMOVED = 0;
        ModHelperFields.MONSTER_MOBS_KILLED = 0;
        ModHelperFields.PASSIVE_MOBS_KILLED = 0;

        /** - Reset Days Score Fields */
        ModHelperFields.DEATH_SCORE_DAYS_SURVIVED = ModHelperFields.DAYS_SURVIVED;
        ModHelperFields.LAST_DEATH_DAY = (int)Math.floor(level.getProperties().getTime() / 24000);

        /** - Reset 404 Challenge Score Fields */
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
        public static Integer BLOCKS_PLACED = 0;
        public static Integer BLOCKS_REMOVED = 0;
        public static Integer MONSTER_MOBS_KILLED = 0;
        public static Integer PASSIVE_MOBS_KILLED = 0;

        /** - Days helper fields */
        public static Integer DEATH_SCORE_DAYS_SURVIVED = 0;
        public static Integer LAST_DEATH_DAY = 0;
        public static Integer DAYS_SURVIVED = 0;
        public static Integer PREV_DAYS_SURVIVED = 0;

        /** - 404 Challenge helper fields */
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
