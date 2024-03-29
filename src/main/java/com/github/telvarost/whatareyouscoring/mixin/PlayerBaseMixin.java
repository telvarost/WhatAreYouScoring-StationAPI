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
        ModHelper.resetFieldsOnDeath(this.level);
    }

    @Inject(method = "writeCustomDataToTag", at = @At("HEAD"))
    private void betaTweaks_writeCustomDataToTag(CompoundTag tag, CallbackInfo info) {
        if (Config.config.BASIC_SCORE_CONFIG.BASIC_SCORING_ENABLED) {
            tag.put("BP", ModHelper.ModHelperFields.BLOCKS_PLACED);
            tag.put("BR", ModHelper.ModHelperFields.BLOCKS_REMOVED);
            tag.put("BM", ModHelper.ModHelperFields.MONSTER_MOBS_KILLED);
            tag.put("BA", ModHelper.ModHelperFields.PASSIVE_MOBS_KILLED);
        }

        if (Config.config.DAYS_SCORE_CONFIG.DAYS_SCORING_ENABLED) {
            ModHelper.ModHelperFields.DAYS_SURVIVED = (int)Math.floor(this.level.getProperties().getTime() / 24000) - ModHelper.ModHelperFields.LAST_DEATH_DAY;
            tag.put("DS", ModHelper.ModHelperFields.DAYS_SURVIVED);
            tag.put("DL", ModHelper.ModHelperFields.LAST_DEATH_DAY);

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
            if (ModHelper.ModHelperFields.HAS_PLAYER_EXITED_THE_NETHER != (ModHelper.ModHelperFields.HAS_PLAYER_EXITED_THE_NETHER & ModHelper.ModHelperFields.OTHER_BITFIELD)) {
                if (-1 == this.dimensionId) {
                    ModHelper.ModHelperFields.IS_PLAYER_IN_NETHER = true;
                } else if (ModHelper.ModHelperFields.IS_PLAYER_IN_NETHER) {
                    if (-1 != this.dimensionId) {
                        ModHelper.ModHelperFields.OTHER_BITFIELD |= ModHelper.ModHelperFields.HAS_PLAYER_EXITED_THE_NETHER;
                        this.incrementStat(Ways404Achievements.EXIT_THE_NETHER);
                    }
                }
            }

            tag.put("CKZ", ModHelper.ModHelperFields.ZOMBIE_KILLED);
            tag.put("CKK", ModHelper.ModHelperFields.SKELETON_KILLED);
            tag.put("CKS", ModHelper.ModHelperFields.SPIDER_KILLED);
            tag.put("CKC", ModHelper.ModHelperFields.CREEPER_KILLED);
            tag.put("CKG", ModHelper.ModHelperFields.GHAST_KILLED);
            tag.put("CKP", ModHelper.ModHelperFields.ZOMBIE_PIGMAN_KILLED);
            tag.put("CBW", ModHelper.ModHelperFields.WHEAT_BROKEN);
            tag.put("CBC", ModHelper.ModHelperFields.CACTI_BROKEN);
            tag.put("CBS", ModHelper.ModHelperFields.SUGAR_CANES_BROKEN);
            tag.put("CBP", ModHelper.ModHelperFields.PUMPKINS_BROKEN);
            tag.put("CPG", ModHelper.ModHelperFields.GLASS_PLACED);
            tag.put("CPB", ModHelper.ModHelperFields.BRICKS_PLACED);
            tag.put("CPW", ModHelper.ModHelperFields.WOOL_TYPES_PLACED);
            tag.put("CW", ModHelper.ModHelperFields.WOOL_PLACED_BITFIELD);
            tag.put("CB", ModHelper.ModHelperFields.BOW_AND_ARROW_CRAFTING_BITFIELD);
            tag.put("CM", ModHelper.ModHelperFields.MISC_CRAFTING_BITFIELD);
            tag.put("CA", ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD);
            tag.put("CE", ModHelper.ModHelperFields.EXPLOSION_STATUS_BITFIELD);
            tag.put("CO", ModHelper.ModHelperFields.OTHER_BITFIELD);
        }
    }

    @Inject(method = "readCustomDataFromTag", at = @At("HEAD"))
    private void betaTweaks_readCustomDataFromTag(CompoundTag tag, CallbackInfo info) {
        if (Config.config.BASIC_SCORE_CONFIG.BASIC_SCORING_ENABLED) {
            this.incrementStat(WaysBasicAchievements.START_BASIC);
            ModHelper.ModHelperFields.BLOCKS_PLACED       = tag.getInt("BP");
            ModHelper.ModHelperFields.BLOCKS_REMOVED      = tag.getInt("BR");
            ModHelper.ModHelperFields.MONSTER_MOBS_KILLED = tag.getInt("MK");
            ModHelper.ModHelperFields.PASSIVE_MOBS_KILLED = tag.getInt("PK");
        }

        if (Config.config.DAYS_SCORE_CONFIG.DAYS_SCORING_ENABLED) {
            this.incrementStat(WaysDaysAchievements.START_DAYS);
            ModHelper.ModHelperFields.DAYS_SURVIVED  = tag.getInt("DS");
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
            ModHelper.ModHelperFields.ZOMBIE_KILLED                     = tag.getInt("CKZ");
            ModHelper.ModHelperFields.SKELETON_KILLED                   = tag.getInt("CKK");
            ModHelper.ModHelperFields.SPIDER_KILLED                     = tag.getInt("CKS");
            ModHelper.ModHelperFields.CREEPER_KILLED                    = tag.getInt("CKC");
            ModHelper.ModHelperFields.GHAST_KILLED                      = tag.getInt("CKG");
            ModHelper.ModHelperFields.ZOMBIE_PIGMAN_KILLED              = tag.getInt("CKP");
            ModHelper.ModHelperFields.WHEAT_BROKEN                      = tag.getInt("CBW");
            ModHelper.ModHelperFields.CACTI_BROKEN                      = tag.getInt("CBC");
            ModHelper.ModHelperFields.SUGAR_CANES_BROKEN                = tag.getInt("CBS");
            ModHelper.ModHelperFields.PUMPKINS_BROKEN                   = tag.getInt("CBP");
            ModHelper.ModHelperFields.GLASS_PLACED                      = tag.getInt("CPG");
            ModHelper.ModHelperFields.BRICKS_PLACED                     = tag.getInt("CPB");
            ModHelper.ModHelperFields.WOOL_TYPES_PLACED                 = tag.getInt("CPW");
            ModHelper.ModHelperFields.WOOL_PLACED_BITFIELD              = tag.getInt("CW" );
            ModHelper.ModHelperFields.BOW_AND_ARROW_CRAFTING_BITFIELD   = tag.getInt("CB" );
            ModHelper.ModHelperFields.MISC_CRAFTING_BITFIELD            = tag.getInt("CM" );
            ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD           = tag.getInt("CA" );
            ModHelper.ModHelperFields.EXPLOSION_STATUS_BITFIELD         = tag.getInt("CE" );
            ModHelper.ModHelperFields.OTHER_BITFIELD                    = tag.getInt("CO" );
        }
    }
}
