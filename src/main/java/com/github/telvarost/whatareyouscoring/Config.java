package com.github.telvarost.whatareyouscoring;

import blue.endless.jankson.Comment;
import net.glasslauncher.mods.api.gcapi.api.*;

public class Config {

    @GConfig(value = "config", visibleName = "WhatAreYouScoring")
    public static ConfigFields config = new ConfigFields();

    public static class ConfigFields {
        @ConfigName("Death Score To Display")
        @Comment("Enable/Disable Scoring Modes In Their Config")
        public ScoreDisplayEnum SCORING_DISPLAY_TYPE = ScoreDisplayEnum.BASIC_SCORE;

        @ConfigName("Reset Score Instead With Difficulty Multipliers")
        @Comment("Hard=0, Normal=0.5, Easy=0.75, Peaceful=1")
        public Boolean SPECIAL_DEATH_EFFECT_ON_SCORE = false;

        @ConfigName("Show Score On Begin Scoring Achievement")
        @Comment("Score will display at the end of the tooltip")
        public Boolean DISPLAY_SCORE_ON_BEGIN_ACHIEVEMENT = true;

        @ConfigCategory("Basic Score Config")
        public BasicScoreConfig BASIC_SCORE_CONFIG = new BasicScoreConfig();

        @ConfigCategory("Days Score Config")
        public DaysConfig DAYS_SCORE_CONFIG = new DaysConfig();

        @ConfigCategory("404 Challenge Score Config")
        public Challenge404Config CHALLENGE_404_CONFIG = new Challenge404Config();
    }

    public static class BasicScoreConfig {
        @ConfigName("Enable Basic Scoring")
        @Comment("Reload world for changes to take effect")
        public Boolean BASIC_SCORING_ENABLED = true;

        @ConfigName("Each Block Placed Adds +1 To Score")
        public Boolean ADD_SCORE_ON_BLOCK_PLACED = true;

        @ConfigName("Each Block Removed Adds +1 To Score")
        public Boolean ADD_SCORE_ON_BLOCK_REMOVED = true;

        @ConfigName("Each Monster Mob Killed Adds +1 To Score")
        public Boolean ADD_SCORE_ON_MONSTER_KILLED = true;

        @ConfigName("Each Passive Mob Killed Adds +1 To Score")
        public Boolean ADD_SCORE_ON_PASSIVE_KILLED = true;
    }

    public static class DaysConfig {
        @ConfigName("Enable Days Scoring")
        @Comment("Reload world for changes to take effect")
        public Boolean DAYS_SCORING_ENABLED = true;
    }

    public static class Challenge404Config {
        @ConfigName("Enable 404 Challenge Scoring")
        @Comment("Reload world for changes to take effect")
        public Boolean CHALLENGE_404_SCORING_ENABLED = false;

        @ConfigName("Enable Hard Mode Multiplier")
        public Boolean ENABLE_HARD_MODE_MULTIPLIER = true;

        @ConfigName("Score Mob Kills")
        public Boolean SCORE_MOB_KILLS_404 = true;

        @ConfigName("Never Sleep/Wear Armor Gives Points")
        @Comment("When false they subtract points when failed")
        public Boolean POSITIVE_NEVER_SLEEP_WEAR_ARMOR = true;
    }
}
