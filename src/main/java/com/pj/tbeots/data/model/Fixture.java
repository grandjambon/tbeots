package com.pj.tbeots.data.model;

public class Fixture {

    public static final Fixture BLANK = new Fixture(null, null, "", "", "", HomeOrAway.home);
    public enum HomeOrAway {home, away, neutral}

    // this is easy to sort across years - "d MMMM yyyy"
    private final String longDate;
    // this is shorter for the web page - "EEE dd/MM"
    private final String shortDate;
    private final String koTime;
    private final String opponent;
    private final String competition;
    private final HomeOrAway homeOrAway;

    public Fixture(String longDate, String shortDate, String koTime, String opponent, String competition, HomeOrAway homeOrAway) {
        this.longDate = longDate;
        this.shortDate = shortDate;
        this.koTime = koTime;
        this.opponent = opponent;
        this.competition = competition;
        this.homeOrAway = homeOrAway;
    }

    public String getShortDate() {
        return shortDate;
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
