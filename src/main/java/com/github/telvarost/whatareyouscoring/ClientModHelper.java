package com.github.telvarost.whatareyouscoring;

import com.github.telvarost.whatareyouscoring.achievement.WaysDaysAchievements;
import com.github.telvarost.whatareyouscoring.mixin.MinecraftAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.stat.Stats;
import net.modificationstation.stationapi.api.entity.player.PlayerHelper;

import java.time.Duration;

public class ClientModHelper {

    public static void clientsideTimeKeeping() {
        Minecraft minecraft = MinecraftAccessor.getInstance();
        PlayerEntity player = PlayerHelper.getPlayerFromGame();
        long realDaysPlayed = Duration.ofSeconds(minecraft.stats.get(Stats.PLAY_ONE_MINUTE) / 20).toDays();
        if (0 < realDaysPlayed) {
            player.incrementStat(WaysDaysAchievements.REAL_DAY);
            if (10 <= realDaysPlayed) {
                player.incrementStat(WaysDaysAchievements.REAL_10_DAYS);
                if (100 <= realDaysPlayed) {
                    player.incrementStat(WaysDaysAchievements.REAL_100_DAYS);
                }
            }
        }
    }
}
