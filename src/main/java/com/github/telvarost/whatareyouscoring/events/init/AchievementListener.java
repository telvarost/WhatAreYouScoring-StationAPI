package com.github.telvarost.whatareyouscoring.events.init;

import com.github.telvarost.whatareyouscoring.ModHelper;
import com.github.telvarost.whatareyouscoring.achievement.*;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.achievement.Achievement;
import net.minecraft.stat.Stat;
import net.modificationstation.stationapi.api.client.gui.screen.achievement.AchievementPage;
import net.modificationstation.stationapi.api.event.achievement.AchievementRegisterEvent;

public class AchievementListener {

    @EventListener
    public void registerAchievements(AchievementRegisterEvent event) {
        AchievementPage achievementPageWaysBasic = new WaysBasicAchievementPage(ModHelper.NAMESPACE.id("waysbasic"));
        event.achievements.addAll(WaysBasicAchievements.ACHIEVEMENTS);
        achievementPageWaysBasic.addAchievements(WaysBasicAchievements.ACHIEVEMENTS.toArray(Achievement[]::new));
        WaysBasicAchievements.ACHIEVEMENTS.forEach(Stat::addStat);

        AchievementPage achievementPageWaysDays = new WaysDaysAchievementPage(ModHelper.NAMESPACE.id("waysdays"));
        event.achievements.addAll(WaysDaysAchievements.ACHIEVEMENTS);
        achievementPageWaysDays.addAchievements(WaysDaysAchievements.ACHIEVEMENTS.toArray(Achievement[]::new));
        WaysDaysAchievements.ACHIEVEMENTS.forEach(Stat::addStat);

        AchievementPage achievementPageWays404 = new Ways404AchievementPage(ModHelper.NAMESPACE.id("ways404"));
        event.achievements.addAll(Ways404Achievements.ACHIEVEMENTS);
        achievementPageWays404.addAchievements(Ways404Achievements.ACHIEVEMENTS.toArray(Achievement[]::new));
        Ways404Achievements.ACHIEVEMENTS.forEach(Stat::addStat);
    }
}
