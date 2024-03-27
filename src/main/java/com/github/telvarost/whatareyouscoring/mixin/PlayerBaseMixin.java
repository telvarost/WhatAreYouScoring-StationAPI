package com.github.telvarost.whatareyouscoring.mixin;

import com.github.telvarost.whatareyouscoring.Config;
import com.github.telvarost.whatareyouscoring.achievement.Ways404Achievements;
import com.github.telvarost.whatareyouscoring.achievement.WaysBasicAchievements;
import com.github.telvarost.whatareyouscoring.achievement.WaysDaysAchievements;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import net.minecraft.stat.Stat;
import net.minecraft.stat.Stats;
import net.minecraft.util.io.CompoundTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.time.Duration;

@Mixin(PlayerBase.class)
public abstract class PlayerBaseMixin extends Living {

    @Shadow public abstract void incrementStat(Stat arg);

    public PlayerBaseMixin(Level arg) {
        super(arg);
    }

    @Inject(method = "writeCustomDataToTag", at = @At("HEAD"))
    private void betaTweaks_writeCustomDataToTag(CompoundTag tag, CallbackInfo info) {
        if (0 >= Config.config.SCORING_DISPLAY_TYPE.ordinal()) {
            return;
        }
    }

    @Inject(method = "readCustomDataFromTag", at = @At("HEAD"))
    private void betaTweaks_readCustomDataFromTag(CompoundTag tag, CallbackInfo info) {
        if (0 < Config.config.SCORING_DISPLAY_TYPE.ordinal()) {
            this.incrementStat(WaysBasicAchievements.START_BASIC);
            this.incrementStat(WaysDaysAchievements.START_DAYS);
            this.incrementStat(Ways404Achievements.START_404);

            Minecraft minecraft = MinecraftAccessor.getInstance();
            long gameDaysPlayed = Duration.ofSeconds(minecraft.statFileWriter.write(Stats.playOneMinute) / 20).toMinutes() / 20;
            long realDaysPlayed = Duration.ofSeconds(minecraft.statFileWriter.write(Stats.playOneMinute) / 20).toDays();
            if (null != this) {
                if (0 < gameDaysPlayed) {
                    this.incrementStat(WaysDaysAchievements.MINECRAFT_DAY);
                    if (100 <= gameDaysPlayed) {
                        this.incrementStat(WaysDaysAchievements.MINECRAFT_100);
                        if (365 <= gameDaysPlayed) {
                            this.incrementStat(WaysDaysAchievements.MINECRAFT_YEAR);
                        }
                    }
                }

                if (0 < realDaysPlayed) {
                    this.incrementStat(WaysDaysAchievements.REAL_DAY);
                    if (100 <= realDaysPlayed) {
                        this.incrementStat(WaysDaysAchievements.REAL_100);
                        if (365 <= realDaysPlayed) {
                            this.incrementStat(WaysDaysAchievements.REAL_YEAR);
                        }
                    }
                }
            }
        }
    }
}
