package com.github.telvarost.whatareyouscoring.events.init;

import com.github.telvarost.whatareyouscoring.achievement.*;
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
        AchievementPage achievementPageWaysBasic = new WaysBasicAchievementPage(namespace.id("waysbasic"));
        event.achievements.addAll(WaysBasicAchievements.ACHIEVEMENTS);
        achievementPageWaysBasic.addAchievements(WaysBasicAchievements.ACHIEVEMENTS.toArray(Achievement[]::new));
        WaysBasicAchievements.ACHIEVEMENTS.forEach(Stat::register);

        AchievementPage achievementPageWaysDays = new WaysDaysAchievementPage(namespace.id("waysdays"));
        event.achievements.addAll(WaysDaysAchievements.ACHIEVEMENTS);
        achievementPageWaysDays.addAchievements(WaysDaysAchievements.ACHIEVEMENTS.toArray(Achievement[]::new));
        WaysDaysAchievements.ACHIEVEMENTS.forEach(Stat::register);

//        AchievementPage achievementPageWays404 = new Ways404AchievementPage(namespace.id("ways404"));
//        event.achievements.addAll(Ways404Achievements.ACHIEVEMENTS);
//        achievementPageWays404.addAchievements(Ways404Achievements.ACHIEVEMENTS.toArray(Achievement[]::new));
//        Ways404Achievements.ACHIEVEMENTS.forEach(Stat::register);
    }
}
