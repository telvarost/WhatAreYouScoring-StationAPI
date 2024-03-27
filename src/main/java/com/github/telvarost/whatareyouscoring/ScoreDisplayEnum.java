package com.github.telvarost.whatareyouscoring;

public enum ScoreDisplayEnum {

    VANILLA("Vanilla"),
    BASIC_SCORE("Basic"),
    DAYS_SCORE("Days");
    //CHALLENGE_404("404 Challenge");

    final String stringValue;

    ScoreDisplayEnum() {
        this.stringValue = "Vanilla";
    }

    ScoreDisplayEnum(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}