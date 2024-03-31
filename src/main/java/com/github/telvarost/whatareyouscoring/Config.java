package com.github.telvarost.whatareyouscoring;

import blue.endless.jankson.Comment;
import net.glasslauncher.mods.api.gcapi.api.*;

public class Config {

    @GConfig(value = "config", visibleName = "WhatAreYouScoring")
    public static ConfigFields config = new ConfigFields();

    public static class ConfigFields {
        @ConfigCategory("Basic Score Config")
        public BasicScoreConfig BASIC_SCORE_CONFIG = new BasicScoreConfig();

        @ConfigCategory("Days Score Config")
        public DaysConfig DAYS_SCORE_CONFIG = new DaysConfig();

        @ConfigCategory("404 Challenge Score Config")
        public Challenge404Config CHALLENGE_404_CONFIG = new Challenge404Config();

        @ConfigName("Reset Score Instead With Difficulty Multipliers")
        @Comment("Hard=0, Normal=0.5, Easy=0.75, Peaceful=1")
        public Boolean SPECIAL_DEATH_EFFECT_ON_SCORE = false;

        @ConfigName("Show Score On Begin Scoring Achievement")
        @Comment("Score will display at the end of the tooltip")
        public Boolean DISPLAY_SCORE_ON_BEGIN_ACHIEVEMENT = true;
    }

    public static class BasicScoreConfig {
        @ConfigName("Display Basic Score On Death")
        public Boolean DISPLAY_BASIC_SCORE_ON_DEATH = true;
        
        @ConfigName("Enable Basic Scoring")
        @Comment("Reload world for changes to take effect")
        public Boolean BASIC_SCORING_ENABLED = true;

        @ConfigName("Score: +1 From Each Block Placed")
        public Boolean ADD_SCORE_ON_BLOCK_PLACED = true;

        @ConfigName("Score: +1 From Each Block Removed")
        public Boolean ADD_SCORE_ON_BLOCK_REMOVED = true;

        @ConfigName("Score: +1 From Each Monster Mob Killed")
        public Boolean ADD_SCORE_ON_MONSTER_KILLED = true;

        @ConfigName("Score: +1 From Each Passive Mob Killed")
        public Boolean ADD_SCORE_ON_PASSIVE_KILLED = true;
    }

    public static class DaysConfig {
        @ConfigName("Display Days Score On Death")
        public Boolean DISPLAY_DAYS_SCORE_ON_DEATH = false;

        @ConfigName("Enable Days Scoring")
        @Comment("Reload world for changes to take effect")
        public Boolean DAYS_SCORING_ENABLED = true;

        @ConfigName("Score: +1 From Each Day Survived")
        public Boolean ADD_SCORE_FOR_EACH_DAY_SURVIVED = true;

        @ConfigName("Score: +25 For Every 100 Days Survived")
        public Boolean ADD_100_DAYS_BONUS_SCORE = false;

        @ConfigName("Score: +100 For Every 365 Days Survived")
        public Boolean ADD_YEAR_BONUS_SCORE = false;
    }

    public static class Challenge404Config {
        @ConfigName("Display 404 Challenge Score On Death")
        public Boolean DISPLAY_404_CHALLENGE_SCORE_ON_DEATH = false;

        @ConfigName("Enable 404 Challenge Scoring")
        @Comment("Reload world for changes to take effect")
        public Boolean CHALLENGE_404_SCORING_ENABLED = false;

        @ConfigName("Enable Hard Mode Multiplier")
        public Boolean ENABLE_HARD_MODE_MULTIPLIER = true;

        @ConfigName("Never Sleep/Wear Armor Gives Points")
        @Comment("When false they subtract points when failed")
        public Boolean POSITIVE_NEVER_SLEEP_WEAR_ARMOR = true;

        @ConfigName("Score Mob Kills")
        public Boolean SCORE_MOB_KILLS_404 = true;
    }
}
