package com.tennis.utils;

public enum GameScore {
    ZERO("0"),
    FIFTEEN("15"),
    THIRTY("30"),
    FORTY("40"),
    ADVANTAGE("AD");

    private String point;

    GameScore(String point) {
        this.point = point;
    }

    public String getScore() {
        return point;
    }
}
