package com.github.telvarost.whatareyouscoring.achievement;

import net.minecraft.block.BlockBase;
import net.modificationstation.stationapi.api.client.gui.screen.achievement.AchievementPage;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class WaysBasicAchievementPage extends AchievementPage {
	private static WaysBasicAchievementPage instance;
	
	public WaysBasicAchievementPage(Identifier id) {
		super(id);
		instance = this;
	}
	
	@Override
	public int getBackgroundTexture(Random random, int column, int row, int randomizedRow, int currentTexture) {
		int k = BlockBase.STONE.texture;
		int l = random.nextInt(1 + row) + row / 2;
		if (l <= 37 && row != 35) {
			if (l == 22) {
				k = BlockBase.WOOD.texture;
			} else if (l == 10) {
				k = BlockBase.WOOD.texture;
			} else if (l == 8) {
				k = BlockBase.WOOD.texture;
			} else if (l > 4) {
				k = BlockBase.WOOD.texture;
			} else if (l > 0) {
				k = BlockBase.DOUBLE_STONE_SLAB.texture;
			}
		} else {
			k = BlockBase.DOUBLE_STONE_SLAB.texture;
		}

		return k;
	}
	
	public static WaysBasicAchievementPage getInstance() {
		return instance;
	}
}
