package com.github.telvarost.whatareyouscoring.achievement;

import net.minecraft.block.BlockBase;
import net.modificationstation.stationapi.api.client.gui.screen.achievement.AchievementPage;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.math.MathHelper;

import java.util.Random;

public class WaysDaysAchievementPage extends AchievementPage {
    private static WaysDaysAchievementPage instance;

    public WaysDaysAchievementPage(Identifier id) {
        super(id);
        instance = this;
    }

    @Override
    public int getBackgroundTexture(Random random, int column, int row, int randomizedRow, int currentTexture) {
        int k = BlockBase.BEDROCK.texture;
        int l = random.nextInt(1 + row) + row / 2;
        if (l <= 37 && row != 35) {
            if (l == 22) {
                k = BlockBase.LAPIS_LAZULI_BLOCK.texture;
            } else if (l == 10) {
                k = BlockBase.LAPIS_LAZULI_BLOCK.texture;
            } else if (l == 8) {
                k = BlockBase.LAPIS_LAZULI_BLOCK.texture;
            } else if (l > 4) {
                k = BlockBase.LAPIS_LAZULI_BLOCK.texture;
            } else if (l > 0) {
                k = BlockBase.GLOWSTONE.texture;
            }
        } else {
            k = BlockBase.ICE.texture;
        }

        return k;
    }

    public static WaysDaysAchievementPage getInstance() {
        return instance;
    }
}
