package com.github.telvarost.whatareyouscoring.mixin;

import com.github.telvarost.whatareyouscoring.Config;
import com.github.telvarost.whatareyouscoring.ModHelper;
import com.github.telvarost.whatareyouscoring.achievement.Ways404Achievements;
import com.github.telvarost.whatareyouscoring.achievement.WaysBasicAchievements;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Living;
import net.minecraft.entity.animal.AnimalBase;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Living.class)
public abstract class LivingMixin extends EntityBase {

    public LivingMixin(Level arg) {
        super(arg);
    }


    @Inject(method = "onKilledBy", at = @At("HEAD"), cancellable = true)
    public void onKilledBy(EntityBase entity, CallbackInfo ci) {
        if (Config.config.BASIC_SCORING_ENABLED) {
            if (entity instanceof PlayerBase)
            {
                Living instance = ((Living) (Object)this);

                if (  (Config.config.SCORE_CONFIG.ADD_SCORE_ON_MONSTER_KILLED)
                   && (  (instance instanceof MonsterBase)
                      || (instance instanceof Ghast)
                      || (instance instanceof Slime)
                      )
                ) {
                    if (0 == ModHelper.ModHelperFields.MONSTER_MOBS_KILLED) {
                        ((PlayerBase)entity).incrementStat(WaysBasicAchievements.MONSTER_MOBS_KILLED);
                    }

                    ModHelper.ModHelperFields.MONSTER_MOBS_KILLED++;
                    ((PlayerBase)entity).score++;
                }

                if (  (Config.config.SCORE_CONFIG.ADD_SCORE_ON_PASSIVE_KILLED)
                   && (instance instanceof AnimalBase)
                ) {
                    if (0 == ModHelper.ModHelperFields.PASSIVE_MOBS_KILLED) {
                        ((PlayerBase)entity).incrementStat(WaysBasicAchievements.PASSIVE_MOBS_KILLED);
                    }

                    ModHelper.ModHelperFields.PASSIVE_MOBS_KILLED++;
                    ((PlayerBase)entity).score++;
                }
            }
        }

        if (Config.config.CHALLENGE_404_SCORING_ENABLED) {
            if (entity instanceof PlayerBase) {
                Living instance = ((Living) (Object) this);

                if (Config.config.CHALLENGE_404_CONFIG.SCORE_MOB_KILLS_404) {
                    if (instance instanceof Zombie) {
                        if (0 == ModHelper.ModHelperFields.ZOMBIE_KILLED) {
                            ((PlayerBase) entity).incrementStat(Ways404Achievements.ZOMBIE_KILLED);
                        }
                        ModHelper.ModHelperFields.ZOMBIE_KILLED++;
                    } else if (instance instanceof Skeleton) {
                        if (0 == ModHelper.ModHelperFields.SKELETON_KILLED) {
                            ((PlayerBase) entity).incrementStat(Ways404Achievements.SKELETON_KILLED);
                        }
                        ModHelper.ModHelperFields.SKELETON_KILLED++;
                    } else if (instance instanceof Spider) {
                        if (0 == ModHelper.ModHelperFields.SPIDER_KILLED) {
                            ((PlayerBase) entity).incrementStat(Ways404Achievements.SPIDER_KILLED);
                        }
                        ModHelper.ModHelperFields.SPIDER_KILLED++;
                    } else if (instance instanceof Creeper) {
                        if (0 == ModHelper.ModHelperFields.CREEPER_KILLED) {
                            ((PlayerBase) entity).incrementStat(Ways404Achievements.CREEPER_KILLED);
                        }
                        ModHelper.ModHelperFields.CREEPER_KILLED++;
                    } else if (instance instanceof Ghast) {
                        if (0 == ModHelper.ModHelperFields.GHAST_KILLED) {
                            ((PlayerBase) entity).incrementStat(Ways404Achievements.GHAST_KILLED);
                        }
                        ModHelper.ModHelperFields.GHAST_KILLED++;
                    } else if (instance instanceof ZombiePigman) {
                        if (0 == ModHelper.ModHelperFields.ZOMBIE_PIGMAN_KILLED) {
                            ((PlayerBase) entity).incrementStat(Ways404Achievements.ZOMBIE_PIGMAN_KILLED);
                        }
                        ModHelper.ModHelperFields.ZOMBIE_PIGMAN_KILLED++;
                    }
                }
            }
        }
    }
}
