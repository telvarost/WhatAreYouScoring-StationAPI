//package com.github.telvarost.whatareyouscoring.achievement;
//
//import com.github.telvarost.whatareyouscoring.ModHelper;
//import com.github.telvarost.whatareyouscoring.mixin.AchievementAccessor;
//import net.minecraft.achievement.Achievement;
//import net.minecraft.block.BlockBase;
//import net.minecraft.item.ItemBase;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Ways404Achievements {
//    public static final List<Achievement> ACHIEVEMENTS = new ArrayList<>();
//
//    public static final Achievement START_404 = make("start_404", BlockBase.STILL_LAVA, 0, 0, null);
//
//    private static Achievement make(String name, BlockBase icon, int x, int y, Achievement parent) {
//        Achievement achievement = new Achievement(ModHelper.ModHelperFields.ACHIEVEMENT_ID++, "whatareyouscoring:" + name, x, y, icon, parent);
//        ACHIEVEMENTS.add(achievement);
//        return achievement;
//    }
//
//    private static Achievement make(String name, ItemBase icon, int x, int y, Achievement parent, boolean isChallenge) {
//        Achievement achievement = new Achievement(ModHelper.ModHelperFields.ACHIEVEMENT_ID++, "whatareyouscoring:" + name, x, y, icon, parent);
//        if (isChallenge) {
//            achievement.setUnusual();
//        }
//        ACHIEVEMENTS.add(achievement);
//        return achievement;
//    }
//
//    public static void modifyThisGuy() {
//        ((AchievementAccessor) ACHIEVEMENTS.get(0)).setAchievementDescription("Cooked: " + ModHelper.ModHelperFields.TEST_ONE_TWO_THREE);
//    }
//}
