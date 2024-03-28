package com.github.telvarost.whatareyouscoring.mixin;

import com.github.telvarost.whatareyouscoring.Config;
import com.github.telvarost.whatareyouscoring.ModHelper;
import com.github.telvarost.whatareyouscoring.achievement.Ways404Achievements;
import com.github.telvarost.whatareyouscoring.achievement.WaysBasicAchievements;
import com.github.telvarost.whatareyouscoring.achievement.WaysDaysAchievements;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityBase;
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

    @Shadow public int dimensionId;

    public PlayerBaseMixin(Level arg) {
        super(arg);
    }


    @Inject(method = "onKilledBy", at = @At("HEAD"))
    public void onKilledBy(EntityBase par1, CallbackInfo ci) {
        ModHelper.ModHelperFields.DEATH_SCORE_DAYS_SURVIVED = ModHelper.ModHelperFields.DAYS_SURVIVED;
        ModHelper.ModHelperFields.LAST_DEATH_DAY = (int)Math.floor(this.level.getProperties().getTime() / 24000);
    }

    @Inject(method = "writeCustomDataToTag", at = @At("HEAD"))
    private void betaTweaks_writeCustomDataToTag(CompoundTag tag, CallbackInfo info) {
        if (Config.config.BASIC_SCORE_CONFIG.BASIC_SCORING_ENABLED) {
            tag.put("BP", ModHelper.ModHelperFields.BLOCKS_PLACED);
            tag.put("BR", ModHelper.ModHelperFields.BLOCKS_REMOVED);
            tag.put("MK", ModHelper.ModHelperFields.MONSTER_MOBS_KILLED);
            tag.put("PK", ModHelper.ModHelperFields.PASSIVE_MOBS_KILLED);
        }

        if (Config.config.DAYS_SCORE_CONFIG.DAYS_SCORING_ENABLED) {
            ModHelper.ModHelperFields.DAYS_SURVIVED = (int)Math.floor(this.level.getProperties().getTime() / 24000) - ModHelper.ModHelperFields.LAST_DEATH_DAY;
            tag.put("DS", ModHelper.ModHelperFields.DAYS_SURVIVED);
            tag.put("LD", ModHelper.ModHelperFields.LAST_DEATH_DAY);

            if (ModHelper.ModHelperFields.PREV_DAYS_SURVIVED != ModHelper.ModHelperFields.DAYS_SURVIVED) {
                ModHelper.ModHelperFields.PREV_DAYS_SURVIVED = ModHelper.ModHelperFields.DAYS_SURVIVED;
                this.incrementStat(WaysDaysAchievements.MINECRAFT_DAY);
                if (100 <= ModHelper.ModHelperFields.DAYS_SURVIVED) {
                    this.incrementStat(WaysDaysAchievements.MINECRAFT_100);
                    if (365 <= ModHelper.ModHelperFields.DAYS_SURVIVED) {
                        this.incrementStat(WaysDaysAchievements.MINECRAFT_YEAR);
                    }
                }
            }
        }

        if (Config.config.CHALLENGE_404_CONFIG.CHALLENGE_404_SCORING_ENABLED) {
            if (ModHelper.ModHelperFields.HAS_PLAYER_EXITED_THE_NETHER) {
                if (-1 == this.dimensionId) {
                    ModHelper.ModHelperFields.IS_PLAYER_IN_NETHER = true;
                } else if (ModHelper.ModHelperFields.IS_PLAYER_IN_NETHER) {
                    if (-1 != this.dimensionId) {
                        ModHelper.ModHelperFields.HAS_PLAYER_EXITED_THE_NETHER = true;
                        this.incrementStat(Ways404Achievements.EXIT_THE_NETHER);
                    }
                }
            }

            tag.put("OC", ModHelper.ModHelperFields.BOW_AND_ARROW_CRAFTING_BITFIELD);
            tag.put("IC", ModHelper.ModHelperFields.MISC_CRAFTING_BITFIELD);
            tag.put("AC", ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD);
            tag.put("ES", ModHelper.ModHelperFields.EXPLOSION_STATUS_BITFIELD);
            tag.put("GP", ModHelper.ModHelperFields.GLASS_PLACED);
            tag.put("IP", ModHelper.ModHelperFields.BRICKS_PLACED);
            tag.put("WT", ModHelper.ModHelperFields.WOOL_TYPES_PLACED);
            tag.put("WP", ModHelper.ModHelperFields.WOOL_PLACED_BITFIELD);
            tag.put("CP", ModHelper.ModHelperFields.HAS_CRASH_SLAB_BEEN_PLACED);
        }
    }

    @Inject(method = "readCustomDataFromTag", at = @At("HEAD"))
    private void betaTweaks_readCustomDataFromTag(CompoundTag tag, CallbackInfo info) {
        if (Config.config.BASIC_SCORE_CONFIG.BASIC_SCORING_ENABLED) {
            this.incrementStat(WaysBasicAchievements.START_BASIC);
            ModHelper.ModHelperFields.BLOCKS_PLACED = tag.getInt("BP");
            ModHelper.ModHelperFields.BLOCKS_REMOVED = tag.getInt("BR");
            ModHelper.ModHelperFields.MONSTER_MOBS_KILLED = tag.getInt("MK");
            ModHelper.ModHelperFields.PASSIVE_MOBS_KILLED = tag.getInt("PK");
        }

        if (Config.config.DAYS_SCORE_CONFIG.DAYS_SCORING_ENABLED) {
            this.incrementStat(WaysDaysAchievements.START_DAYS);
            ModHelper.ModHelperFields.DAYS_SURVIVED = tag.getInt("DS");
            ModHelper.ModHelperFields.LAST_DEATH_DAY = tag.getInt("LD");

            Minecraft minecraft = MinecraftAccessor.getInstance();
            long realDaysPlayed = Duration.ofSeconds(minecraft.statFileWriter.write(Stats.playOneMinute) / 20).toDays();
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

        if (Config.config.CHALLENGE_404_CONFIG.CHALLENGE_404_SCORING_ENABLED) {
            this.incrementStat(Ways404Achievements.START_404);
            ModHelper.ModHelperFields.BOW_AND_ARROW_CRAFTING_BITFIELD = tag.getInt("OC");
            ModHelper.ModHelperFields.MISC_CRAFTING_BITFIELD = tag.getInt("IC");
            ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD = tag.getInt("AC");
            ModHelper.ModHelperFields.EXPLOSION_STATUS_BITFIELD = tag.getInt("ES");
            ModHelper.ModHelperFields.GLASS_PLACED = tag.getInt("GP");
            ModHelper.ModHelperFields.BRICKS_PLACED = tag.getInt("IP");
            ModHelper.ModHelperFields.WOOL_TYPES_PLACED = tag.getInt("WT");
            ModHelper.ModHelperFields.WOOL_PLACED_BITFIELD = tag.getInt("WP");
            ModHelper.ModHelperFields.HAS_CRASH_SLAB_BEEN_PLACED = tag.getBoolean("CP");
            ModHelper.ModHelperFields.GLASS_PLACED = 0;
            ModHelper.ModHelperFields.BRICKS_PLACED = 0;
            ModHelper.ModHelperFields.WOOL_TYPES_PLACED = 0;
            ModHelper.ModHelperFields.WOOL_PLACED_BITFIELD = 0;
            ModHelper.ModHelperFields.HAS_CRASH_SLAB_BEEN_PLACED = false;
        }
    }
}
