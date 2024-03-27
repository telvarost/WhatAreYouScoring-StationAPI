package com.github.telvarost.whatareyouscoring.achievement;

import com.github.telvarost.whatareyouscoring.ModHelper;
import com.github.telvarost.whatareyouscoring.mixin.AchievementAccessor;
import net.minecraft.achievement.Achievement;
import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemBase;

import java.util.ArrayList;
import java.util.List;

public class WaysBasicAchievements {
	public static final List<Achievement> ACHIEVEMENTS = new ArrayList<>();

	public static final Achievement START_BASIC = make("start_basic", ItemBase.book, 0, 0, null, false);
	public static final Achievement BLOCKS_PLACED = make("block_placed", BlockBase.COBBLESTONE, 2, 0, START_BASIC, false);
	public static final Achievement BLOCKS_REMOVED = make("block_removed", BlockBase.DIRT, 0, 2, START_BASIC, false);
	public static final Achievement MONSTER_MOBS_KILLED = make("monster_mob_killed", ItemBase.bone, 0, -2, START_BASIC, false);
	public static final Achievement PASSIVE_MOBS_KILLED = make("passive_mob_killed", ItemBase.leather, -2, 0, START_BASIC, false);

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
