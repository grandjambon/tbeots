package com.pj.tbeots.data.model;

public class Fixture {

    public static final Fixture BLANK = new Fixture(null, "", "", "", HomeOrAway.home);

    public enum HomeOrAway {home, away}

    private final String date;
    private final String koTime;
    private final String opponent;
    private final String competition;
    private final HomeOrAway homeOrAway;

    public Fixture(String date, String koTime, String opponent, String competition, HomeOrAway homeOrAway) {
        this.date = date;
        this.koTime = koTime;
        this.opponent = opponent;
        this.competition = competition;
        this.homeOrAway = homeOrAway;
    }

    public String getDate() {
        return date;
    }

    public String getOpponent() {
        return opponent;
    }

    public String getCompetition() {
        return competition;
    }

    public String getKoTime() {
        return koTime;
    }

    public HomeOrAway getHomeOrAway() {
        return homeOrAway;
    }

}
