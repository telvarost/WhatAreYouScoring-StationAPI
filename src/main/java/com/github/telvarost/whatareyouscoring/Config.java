package com.github.telvarost.whatareyouscoring;

import blue.endless.jankson.Comment;
import net.glasslauncher.mods.api.gcapi.api.*;

public class Config {

    @GConfig(value = "config", visibleName = "WhatAreYouScoring")
    public static ConfigFields config = new ConfigFields();

    public static class ConfigFields {
        @ConfigName("What Score To Display")
        public Boolean SCORING_DISPLAY_TYPE = true;

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
