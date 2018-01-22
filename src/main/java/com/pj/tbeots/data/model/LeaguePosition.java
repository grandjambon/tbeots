package com.pj.tbeots.data.model;

public class LeaguePosition {
    private final int position;
    private final int points;
    private final int played;
    private final Team team;
    private final String form;

    public LeaguePosition(int position, int points, int played, Team team, String form) {
        this.position = position;
        this.points = points;
        this.played = played;
        this.team = team;
        this.form = form;
    }

    public int getPosition() {
        return position;
    }

    public int getPoints() {
        return points;
    }

    public int getPlayed() {
        return played;
    }

    public Team getTeam() {
        return team;
    }

    public String getForm() {
        return form;
    }
}
