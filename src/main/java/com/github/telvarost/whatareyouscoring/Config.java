package com.github.telvarost.whatareyouscoring;

import net.glasslauncher.mods.gcapi3.api.*;

public class Config {

    @ConfigRoot(value = "config", visibleName = "WhatAreYouScoring")
    public static ConfigFields config = new ConfigFields();

    public static class ConfigFields {
        @ConfigCategory(name = "Basic Score Config")
        public BasicScoreConfig BASIC_SCORE_CONFIG = new BasicScoreConfig();

        @ConfigCategory(name = "Days Score Config")
        public DaysConfig DAYS_SCORE_CONFIG = new DaysConfig();

        @ConfigCategory(name = "404 Challenge Score Config")
        public Challenge404Config CHALLENGE_404_CONFIG = new Challenge404Config();

        @ConfigEntry(
                name = "Reset Score Instead With Difficulty Multipliers",
                description = "Hard=0, Normal=0.5, Easy=0.75, Peaceful=1"
        )
        public Boolean SPECIAL_DEATH_EFFECT_ON_SCORE = false;

        @ConfigEntry(
                name = "Show Score On Begin Scoring Achievement",
                description = "Score will display at the end of the tooltip"
        )
        public Boolean DISPLAY_SCORE_ON_BEGIN_ACHIEVEMENT = true;
    }

    public static class BasicScoreConfig {
        @ConfigEntry(name = "Display Basic Score On Death")
        public Boolean DISPLAY_BASIC_SCORE_ON_DEATH = true;

        @ConfigEntry(
                name = "Enable Basic Scoring",
                description = "Reload world for changes to take effect"
        )
        public Boolean BASIC_SCORING_ENABLED = true;

        @ConfigEntry(name = "Score: +1 From Each Block Placed")
        public Boolean ADD_SCORE_ON_BLOCK_PLACED = true;

        @ConfigEntry(name = "Score: +1 From Each Block Removed")
        public Boolean ADD_SCORE_ON_BLOCK_REMOVED = true;

        @ConfigEntry(name = "Score: +1 From Each Monster Mob Killed")
        public Boolean ADD_SCORE_ON_MONSTER_KILLED = true;

        @ConfigEntry(name = "Score: +1 From Each Passive Mob Killed")
        public Boolean ADD_SCORE_ON_PASSIVE_KILLED = true;
    }

    public static class DaysConfig {
        @ConfigEntry(name = "Display Days Score On Death")
        public Boolean DISPLAY_DAYS_SCORE_ON_DEATH = false;

        @ConfigEntry(
                name = "Enable Days Scoring",
                description = "Reload world for changes to take effect"
        )
        public Boolean DAYS_SCORING_ENABLED = true;

        @ConfigEntry(name = "Score: +1 From Each Day Survived")
        public Boolean ADD_SCORE_FOR_EACH_DAY_SURVIVED = true;

        @ConfigEntry(name = "Score: +25 For Every 100 Days Survived")
        public Boolean ADD_100_DAYS_BONUS_SCORE = false;

        @ConfigEntry(name = "Score: +100 For Every 365 Days Survived")
        public Boolean ADD_YEAR_BONUS_SCORE = false;
    }

    public static class Challenge404Config {
        @ConfigEntry(name = "Display 404 Challenge Score On Death")
        public Boolean DISPLAY_404_CHALLENGE_SCORE_ON_DEATH = false;

        @ConfigEntry(
                name = "Enable 404 Challenge Scoring",
                description = "Reload world for changes to take effect"
        )
        public Boolean CHALLENGE_404_SCORING_ENABLED = false;

        @ConfigEntry(name = "Enable Hard Mode Multiplier")
        public Boolean ENABLE_HARD_MODE_MULTIPLIER = true;

        @ConfigEntry(
                name = "Never Sleep/Wear Armor Gives Points",
                description = "When false they subtract points when failed"
        )
        public Boolean POSITIVE_NEVER_SLEEP_WEAR_ARMOR = true;

        @ConfigEntry(name = "Score Mob Kills")
        public Boolean SCORE_MOB_KILLS_404 = true;
    }
}
