package com.github.telvarost.whatareyouscoring;

import blue.endless.jankson.Comment;
import net.glasslauncher.mods.api.gcapi.api.*;

public class Config {

    @GConfig(value = "config", visibleName = "WhatAreYouScoring")
    public static ConfigFields config = new ConfigFields();

    public static class ConfigFields {
        @ConfigName("What Score To Display")
        public ScoreDisplayEnum SCORING_DISPLAY_TYPE = ScoreDisplayEnum.BASIC_SCORE;

        @ConfigName("Basic Scoring Enabled")
        public Boolean BASIC_SCORING_ENABLED = true;

        @ConfigName("Days Scoring Enabled")
        public Boolean DAYS_SCORING_ENABLED = true;

        @ConfigName("404 Challenge Scoring Enabled")
        public Boolean CHALLENGE_404_SCORING_ENABLED = false;

        @ConfigCategory("Basic Score Config")
        public ScoreConfig SCORE_CONFIG = new ScoreConfig();
    }

    public static class ScoreConfig {

        @ConfigName("Each Block Placed Adds +1 To Score")
        public Boolean ADD_SCORE_ON_BLOCK_PLACED = true;

        @ConfigName("Each Block Removed Adds +1 To Score")
        public Boolean ADD_SCORE_ON_BLOCK_REMOVED = true;

        @ConfigName("Each Monster Mob Killed Adds +1 To Score")
        public Boolean ADD_SCORE_ON_MONSTER_KILLED = true;

        @ConfigName("Each Passive Mob Killed Adds +1 To Score")
        public Boolean ADD_SCORE_ON_PASSIVE_KILLED = true;
    }
}
