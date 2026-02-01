package com.github.telvarost.whatareyouscoring.achievement;

import com.github.telvarost.whatareyouscoring.Config;
import com.github.telvarost.whatareyouscoring.ModHelper;
import com.github.telvarost.whatareyouscoring.mixin.AchievementAccessor;
import net.minecraft.achievement.Achievement;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class WaysBasicAchievements {
	public static final List<Achievement> ACHIEVEMENTS = new ArrayList<>();

	public static final Achievement START_BASIC = make("start_basic", Item.BOOK, 0, 0, null, false);
	public static final Achievement BLOCKS_PLACED = make("block_placed", Block.COBBLESTONE, 2, 0, START_BASIC, false);
	public static final Achievement BLOCKS_REMOVED = make("block_removed", Block.DIRT, 0, 2, START_BASIC, false);
	public static final Achievement MONSTER_MOBS_KILLED = make("monster_mob_killed", Item.BONE, 0, -2, START_BASIC, false);
	public static final Achievement PASSIVE_MOBS_KILLED = make("passive_mob_killed", Item.LEATHER, -2, 0, START_BASIC, false);

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
			/** - Get basic score */
			int currentScore = ModHelper.calculateBasicScore();
			if (Config.config.SPECIAL_DEATH_EFFECT_ON_SCORE) {
				currentScore += ModHelper.ModHelperFields.PrevCumulativeBasicScore;
			}
			((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(START_BASIC))).setAchievementDescription("Scores each block placed/removed and each mob killed.\n\nScore: " + currentScore);
		} else {
			((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(START_BASIC))).setAchievementDescription("Scores each block placed/removed and each mob killed.");
		}

		((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(BLOCKS_PLACED))).setAchievementDescription("Count: " + ModHelper.ModHelperFields.BLOCKS_PLACED);
		((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(BLOCKS_REMOVED))).setAchievementDescription("Count: " + ModHelper.ModHelperFields.BLOCKS_REMOVED);
		((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(MONSTER_MOBS_KILLED))).setAchievementDescription("Count: " + ModHelper.ModHelperFields.MONSTER_MOBS_KILLED);
		((AchievementAccessor) ACHIEVEMENTS.get(ACHIEVEMENTS.indexOf(PASSIVE_MOBS_KILLED))).setAchievementDescription("Count: " + ModHelper.ModHelperFields.PASSIVE_MOBS_KILLED);
	}
}
