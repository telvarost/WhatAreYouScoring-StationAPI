package com.github.telvarost.whatareyouscoring;

public enum ScoreDisplayEnum {

    VANILLA("Vanilla"),
    COMBINED("Combined"),
    LISTED("Listed"),
    CUSTOM("Custom");

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