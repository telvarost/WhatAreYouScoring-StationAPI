package com.github.telvarost.whatareyouscoring.achievement;

import net.minecraft.block.Block;
import net.modificationstation.stationapi.api.client.gui.screen.achievement.AchievementPage;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.math.MathHelper;

import java.util.Random;

public class WaysBasicAchievementPage extends AchievementPage {
	private static WaysBasicAchievementPage instance;

	public WaysBasicAchievementPage(Identifier id) {
		super(id);
		instance = this;
	}

	@Override
	public int getBackgroundTexture(Random random, int column, int row, int randomizedRow, int currentTexture) {
		int rand = Math.abs((int) MathHelper.hashCode(column, 5, row)) & 31;
		return switch (rand) {
			case 3, 2, 1, 0 -> Block.DIRT.textureId;
			default -> Block.SOUL_SAND.textureId;
		};
	}

	public static WaysBasicAchievementPage getInstance() {
		return instance;
	}
}
