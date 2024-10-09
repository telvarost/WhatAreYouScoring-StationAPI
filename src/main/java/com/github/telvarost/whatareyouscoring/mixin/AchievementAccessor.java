package com.github.telvarost.whatareyouscoring.mixin;

import net.minecraft.achievement.Achievement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Achievement.class)
public interface AchievementAccessor {
    @Accessor("translationKey")
    public void setAchievementDescription(String description);
}
