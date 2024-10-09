package com.github.telvarost.whatareyouscoring.mixin;

import com.github.telvarost.whatareyouscoring.Config;
import com.github.telvarost.whatareyouscoring.ModHelper;
import com.github.telvarost.whatareyouscoring.achievement.Ways404Achievements;
import com.github.telvarost.whatareyouscoring.achievement.WaysDaysAchievements;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.stat.Stat;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.time.Duration;

@Mixin(PlayerEntity.class)
public abstract class PlayerBaseMixin extends LivingEntity {

    @Shadow public abstract void incrementStat(Stat arg);

    @Shadow public int dimensionId;

    public PlayerBaseMixin(World arg) {
        super(arg);
    }


    @Inject(method = "onKilledBy", at = @At("HEAD"))
    public void onKilledBy(Entity par1, CallbackInfo ci) {

        /** - Calculate score and reset score fields */
        ModHelper.resetFieldsOnDeath(this.world, false);

        if (Config.config.SPECIAL_DEATH_EFFECT_ON_SCORE) {
            /** - Get basic score */
            int currentScoreBasic = ModHelper.ModHelperFields.DEATH_SCORE_BASIC_MODE;
            /** - Get days survived */
            int currentScoreDays = ModHelper.ModHelperFields.DEATH_SCORE_DAYS_MODE;
            /** - Get 404 challenge score */
            int currentScore404 = ModHelper.ModHelperFields.DEATH_SCORE_404_MODE;

            double saveScoreMultiplier;

            if (0 == this.world.difficulty) {
                saveScoreMultiplier = 1;
            } else if (1 == this.world.difficulty) {
                saveScoreMultiplier = 0.75;
            } else if (2 == this.world.difficulty) {
                saveScoreMultiplier = 0.5;
            } else {
                saveScoreMultiplier = 0;
            }

            ModHelper.ModHelperFields.PrevCumulativeBasicScore = ModHelper.ModHelperFields.CumulativeBasicScore;
            ModHelper.ModHelperFields.PrevCumulativeDaysScore = ModHelper.ModHelperFields.CumulativeDaysScore;
            ModHelper.ModHelperFields.PrevCumulative404Score = ModHelper.ModHelperFields.Cumulative404Score;
            ModHelper.ModHelperFields.CumulativeBasicScore = (int)((currentScoreBasic + ModHelper.ModHelperFields.CumulativeBasicScore) * saveScoreMultiplier);
            ModHelper.ModHelperFields.CumulativeDaysScore = (int)((currentScoreDays + ModHelper.ModHelperFields.CumulativeDaysScore) * saveScoreMultiplier);
            ModHelper.ModHelperFields.Cumulative404Score = (int)((currentScore404 + ModHelper.ModHelperFields.Cumulative404Score) * saveScoreMultiplier);
        }
    }

    @Inject(method = "writeNbt", at = @At("HEAD"))
    private void betaTweaks_writeCustomDataToTag(NbtCompound tag, CallbackInfo info) {
        if (Config.config.SPECIAL_DEATH_EFFECT_ON_SCORE) {
            tag.putInt("SB", ModHelper.ModHelperFields.CumulativeBasicScore);
            tag.putInt("SD", ModHelper.ModHelperFields.CumulativeDaysScore);
            tag.putInt("SC", ModHelper.ModHelperFields.Cumulative404Score);
        }

        if (Config.config.BASIC_SCORE_CONFIG.BASIC_SCORING_ENABLED) {
            tag.putInt("BP", ModHelper.ModHelperFields.BLOCKS_PLACED);
            tag.putInt("BR", ModHelper.ModHelperFields.BLOCKS_REMOVED);
            tag.putInt("BM", ModHelper.ModHelperFields.MONSTER_MOBS_KILLED);
            tag.putInt("BA", ModHelper.ModHelperFields.PASSIVE_MOBS_KILLED);
        }

        if (Config.config.DAYS_SCORE_CONFIG.DAYS_SCORING_ENABLED) {
            ModHelper.ModHelperFields.DAYS_PLAYED = (int)Math.floor(this.world.getProperties().getTime() / 24000);
            tag.putInt("DP", ModHelper.ModHelperFields.DAYS_PLAYED);
            tag.putInt("DL", ModHelper.ModHelperFields.LAST_DEATH_DAY);

            if (ModHelper.ModHelperFields.PREV_DAYS_PLAYED != ModHelper.ModHelperFields.DAYS_PLAYED) {
                ModHelper.ModHelperFields.PREV_DAYS_PLAYED = ModHelper.ModHelperFields.DAYS_PLAYED;
                this.incrementStat(WaysDaysAchievements.MINECRAFT_DAY);
                if (100 <= ModHelper.ModHelperFields.DAYS_PLAYED) {
                    this.incrementStat(WaysDaysAchievements.MINECRAFT_100);
                    if (365 <= ModHelper.ModHelperFields.DAYS_PLAYED) {
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

            tag.putInt("CKZ", ModHelper.ModHelperFields.ZOMBIE_KILLED);
            tag.putInt("CKK", ModHelper.ModHelperFields.SKELETON_KILLED);
            tag.putInt("CKS", ModHelper.ModHelperFields.SPIDER_KILLED);
            tag.putInt("CKC", ModHelper.ModHelperFields.CREEPER_KILLED);
            tag.putInt("CKG", ModHelper.ModHelperFields.GHAST_KILLED);
            tag.putInt("CKP", ModHelper.ModHelperFields.ZOMBIE_PIGMAN_KILLED);
            tag.putInt("CBW", ModHelper.ModHelperFields.WHEAT_BROKEN);
            tag.putInt("CBC", ModHelper.ModHelperFields.CACTI_BROKEN);
            tag.putInt("CBS", ModHelper.ModHelperFields.SUGAR_CANES_BROKEN);
            tag.putInt("CBP", ModHelper.ModHelperFields.PUMPKINS_BROKEN);
            tag.putInt("CPG", ModHelper.ModHelperFields.GLASS_PLACED);
            tag.putInt("CPB", ModHelper.ModHelperFields.BRICKS_PLACED);
            tag.putInt("CPW", ModHelper.ModHelperFields.WOOL_TYPES_PLACED);
            tag.putInt("CW", ModHelper.ModHelperFields.WOOL_PLACED_BITFIELD);
            tag.putInt("CB", ModHelper.ModHelperFields.BOW_AND_ARROW_CRAFTING_BITFIELD);
            tag.putInt("CM", ModHelper.ModHelperFields.MISC_CRAFTING_BITFIELD);
            tag.putInt("CA", ModHelper.ModHelperFields.ARMOR_CRAFTING_BITFIELD);
            tag.putInt("CE", ModHelper.ModHelperFields.EXPLOSION_STATUS_BITFIELD);
            tag.putInt("CO", ModHelper.ModHelperFields.OTHER_BITFIELD);
        }
    }

    @Inject(method = "readNbt", at = @At("HEAD"))
    private void betaTweaks_readCustomDataFromTag(NbtCompound tag, CallbackInfo info) {
        if (Config.config.SPECIAL_DEATH_EFFECT_ON_SCORE) {
            ModHelper.ModHelperFields.CumulativeBasicScore = tag.getInt("SB");
            ModHelper.ModHelperFields.CumulativeDaysScore  = tag.getInt("SD");
            ModHelper.ModHelperFields.Cumulative404Score   = tag.getInt("SC");
            ModHelper.ModHelperFields.PrevCumulativeBasicScore = ModHelper.ModHelperFields.CumulativeBasicScore;
            ModHelper.ModHelperFields.PrevCumulativeDaysScore = ModHelper.ModHelperFields.CumulativeDaysScore;
            ModHelper.ModHelperFields.PrevCumulative404Score = ModHelper.ModHelperFields.Cumulative404Score;
        }

        if (Config.config.BASIC_SCORE_CONFIG.BASIC_SCORING_ENABLED) {
            ModHelper.ModHelperFields.BLOCKS_PLACED       = tag.getInt("BP");
            ModHelper.ModHelperFields.BLOCKS_REMOVED      = tag.getInt("BR");
            ModHelper.ModHelperFields.MONSTER_MOBS_KILLED = tag.getInt("BM");
            ModHelper.ModHelperFields.PASSIVE_MOBS_KILLED = tag.getInt("BA");
        }

        if (Config.config.DAYS_SCORE_CONFIG.DAYS_SCORING_ENABLED) {
            ModHelper.ModHelperFields.DAYS_PLAYED    = tag.getInt("DP");
            ModHelper.ModHelperFields.LAST_DEATH_DAY = tag.getInt("DL");

            Minecraft minecraft = MinecraftAccessor.getInstance();
            long realDaysPlayed = Duration.ofSeconds(minecraft.field_2773.method_1989(Stats.PLAY_ONE_MINUTE) / 20).toDays();
            if (0 < realDaysPlayed) {
                this.incrementStat(WaysDaysAchievements.REAL_DAY);
                if (10 <= realDaysPlayed) {
                    this.incrementStat(WaysDaysAchievements.REAL_10_DAYS);
                    if (100 <= realDaysPlayed) {
                        this.incrementStat(WaysDaysAchievements.REAL_100_DAYS);
                    }
                }
            }
        }

        if (Config.config.CHALLENGE_404_CONFIG.CHALLENGE_404_SCORING_ENABLED) {
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
