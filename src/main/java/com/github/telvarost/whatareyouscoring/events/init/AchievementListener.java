package com.github.telvarost.whatareyouscoring.events.init;

import com.github.telvarost.whatareyouscoring.achievement.WhatAreYouScoringAchievementPage;
import com.github.telvarost.whatareyouscoring.achievement.WhatAreYouScoringAchievements;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.achievement.Achievement;
import net.minecraft.stat.Stat;
import net.modificationstation.stationapi.api.client.gui.screen.achievement.AchievementPage;
import net.modificationstation.stationapi.api.event.achievement.AchievementRegisterEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Namespace;

import java.util.List;

public class AchievementListener {

    @Entrypoint.Namespace
    private Namespace namespace;

    @EventListener
    public void registerAchievements(AchievementRegisterEvent event) {
        AchievementPage achievementPage = new WhatAreYouScoringAchievementPage(namespace.id("achievements"));
        event.achievements.addAll(WhatAreYouScoringAchievements.ACHIEVEMENTS);
        achievementPage.addAchievements(WhatAreYouScoringAchievements.ACHIEVEMENTS.toArray(Achievement[]::new));
        WhatAreYouScoringAchievements.ACHIEVEMENTS.forEach(Stat::register);
    }
}
