package com.github.telvarost.whatareyouscoring;

import blue.endless.jankson.Comment;
import net.glasslauncher.mods.api.gcapi.api.*;

public class Config {

    @GConfig(value = "config", visibleName = "WhatAreYouScoring")
    public static ConfigFields config = new ConfigFields();

    public static class ConfigFields {
        @ConfigName("What Score To Display")
        public ScoreDisplayEnum SCORING_DISPLAY_TYPE = ScoreDisplayEnum.BASIC_SCORE;


        @ConfigCategory("Basic Score Config")
        public BasicScoreConfig BASIC_SCORE_CONFIG = new BasicScoreConfig();

        @ConfigCategory("Days Score Config")
        public DaysConfig DAYS_SCORE_CONFIG = new DaysConfig();

        @ConfigCategory("404 Challenge Score Config")
        public Challenge404Config CHALLENGE_404_CONFIG = new Challenge404Config();
    }

    public static class BasicScoreConfig {
        @ConfigName("Basic Scoring Enabled")
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
        @ConfigName("Days Scoring Enabled")
        public Boolean DAYS_SCORING_ENABLED = true;
    }

    public static class Challenge404Config {
        @ConfigName("404 Challenge Scoring Enabled")
        public Boolean CHALLENGE_404_SCORING_ENABLED = false;

        @ConfigName("Score Mob Kills")
        public Boolean SCORE_MOB_KILLS_404 = true;
    }
}
