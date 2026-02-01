package com.github.telvarost.whatareyouscoring.mixin;

import com.github.telvarost.whatareyouscoring.Config;
import com.github.telvarost.whatareyouscoring.ModHelper;
import com.github.telvarost.whatareyouscoring.achievement.Ways404Achievements;
import com.github.telvarost.whatareyouscoring.achievement.WaysBasicAchievements;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.mob.MonsterEntity;
import net.minecraft.entity.mob.PigZombieEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    public LivingEntityMixin(World arg) {
        super(arg);
    }

    @Inject(method = "onKilledBy", at = @At("HEAD"), cancellable = true)
    public void whatAreYouScoring_onKilledBy(Entity entity, CallbackInfo ci) {
        if (Config.config.BASIC_SCORE_CONFIG.BASIC_SCORING_ENABLED) {
            if (entity instanceof PlayerEntity) {
                LivingEntity instance = ((LivingEntity) (Object)this);

                if (  (Config.config.BASIC_SCORE_CONFIG.BASIC_SCORING_ENABLED)
                   && (Config.config.BASIC_SCORE_CONFIG.ADD_SCORE_ON_MONSTER_KILLED)
                   && (  (instance instanceof MonsterEntity)
                      || (instance instanceof GhastEntity)
                      || (instance instanceof SlimeEntity)
                      )
                ) {
                    if (0 == ModHelper.ModHelperFields.MONSTER_MOBS_KILLED) {
                        ((PlayerEntity)entity).incrementStat(WaysBasicAchievements.MONSTER_MOBS_KILLED);
                    }

                    ModHelper.ModHelperFields.MONSTER_MOBS_KILLED++;
                    ModHelper.ModHelperFields.CurrentBasicScore = ModHelper.calculateBasicScore();
                }

                if (  (Config.config.BASIC_SCORE_CONFIG.BASIC_SCORING_ENABLED)
                   && (Config.config.BASIC_SCORE_CONFIG.ADD_SCORE_ON_PASSIVE_KILLED)
                   && (  (instance instanceof AnimalEntity)
                      || (instance instanceof SquidEntity)
                      )
                ) {
                    if (0 == ModHelper.ModHelperFields.PASSIVE_MOBS_KILLED) {
                        ((PlayerEntity)entity).incrementStat(WaysBasicAchievements.PASSIVE_MOBS_KILLED);
                    }

                    ModHelper.ModHelperFields.PASSIVE_MOBS_KILLED++;
                    ModHelper.ModHelperFields.CurrentBasicScore = ModHelper.calculateBasicScore();
                }
            }
        }

        if (Config.config.CHALLENGE_404_CONFIG.CHALLENGE_404_SCORING_ENABLED) {
            if (entity instanceof PlayerEntity) {
                LivingEntity instance = ((LivingEntity) (Object) this);

                if (Config.config.CHALLENGE_404_CONFIG.SCORE_MOB_KILLS_404) {
                    if (instance instanceof ZombieEntity) {
                        if (instance instanceof PigZombieEntity) {
                            if (0 == ModHelper.ModHelperFields.ZOMBIE_PIGMAN_KILLED) {
                                ((PlayerEntity) entity).incrementStat(Ways404Achievements.ZOMBIE_PIGMAN_KILLED);
                            }
                            ModHelper.ModHelperFields.ZOMBIE_PIGMAN_KILLED++;
                        } else {
                            if (0 == ModHelper.ModHelperFields.ZOMBIE_KILLED) {
                                ((PlayerEntity) entity).incrementStat(Ways404Achievements.ZOMBIE_KILLED);
                            }
                            ModHelper.ModHelperFields.ZOMBIE_KILLED++;
                        }
                    } else if (instance instanceof SkeletonEntity) {
                        if (0 == ModHelper.ModHelperFields.SKELETON_KILLED) {
                            ((PlayerEntity) entity).incrementStat(Ways404Achievements.SKELETON_KILLED);
                        }
                        ModHelper.ModHelperFields.SKELETON_KILLED++;
                    } else if (instance instanceof SpiderEntity) {
                        if (0 == ModHelper.ModHelperFields.SPIDER_KILLED) {
                            ((PlayerEntity) entity).incrementStat(Ways404Achievements.SPIDER_KILLED);
                        }
                        ModHelper.ModHelperFields.SPIDER_KILLED++;
                    } else if (instance instanceof CreeperEntity) {
                        if (0 == ModHelper.ModHelperFields.CREEPER_KILLED) {
                            ((PlayerEntity) entity).incrementStat(Ways404Achievements.CREEPER_KILLED);
                        }
                        ModHelper.ModHelperFields.CREEPER_KILLED++;
                    } else if (instance instanceof GhastEntity) {
                        if (0 == ModHelper.ModHelperFields.GHAST_KILLED) {
                            ((PlayerEntity) entity).incrementStat(Ways404Achievements.GHAST_KILLED);
                        }
                        ModHelper.ModHelperFields.GHAST_KILLED++;
                    }

                    ModHelper.ModHelperFields.Current404Score = ModHelper.calculate404ChallengeScore(world);
                }
            }
        }
    }
}
