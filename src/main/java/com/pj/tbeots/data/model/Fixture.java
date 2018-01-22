package com.pj.tbeots.data.model;

public class Fixture {
    private final String date;
    private final String opponent;

    public Fixture(String date, String opponent) {
        this.date = date;
        this.opponent = opponent;
    }

    public String getDate() {
        return date;
    }

    public String getOpponent() {
        return opponent;
    }
}
