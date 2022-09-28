package com.tennis.utils;

public enum SetScore {
    ZERO("0"),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7");

    private String setScore;

    SetScore(String setScore) {
        this.setScore = setScore;
    }

    public String getSetScore() {
        return setScore;
    }
}
