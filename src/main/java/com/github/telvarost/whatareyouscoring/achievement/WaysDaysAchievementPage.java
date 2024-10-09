package com.github.telvarost.whatareyouscoring.achievement;

import net.minecraft.block.Block;
import net.modificationstation.stationapi.api.client.gui.screen.achievement.AchievementPage;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class WaysDaysAchievementPage extends AchievementPage {
    private static WaysDaysAchievementPage instance;

    public WaysDaysAchievementPage(Identifier id) {
        super(id);
        instance = this;
    }

    @Override
    public int getBackgroundTexture(Random random, int column, int row, int randomizedRow, int currentTexture) {
        int k = Block.BEDROCK.textureId;
        int l = random.nextInt(1 + row) + row / 2;
        if (l <= 37 && row != 35) {
            if (l == 22) {
                k = Block.LAPIS_BLOCK.textureId;
            } else if (l == 10) {
                k = Block.LAPIS_BLOCK.textureId;
            } else if (l == 8) {
                k = Block.LAPIS_BLOCK.textureId;
            } else if (l > 4) {
                k = Block.LAPIS_BLOCK.textureId;
            } else if (l > 0) {
                k = Block.GLOWSTONE.textureId;
            }
        } else {
            k = Block.ICE.textureId;
        }

        return k;
    }

    public static WaysDaysAchievementPage getInstance() {
        return instance;
    }
}
