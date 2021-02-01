package com.pj.tbeots.data.model;

public class LeaguePosition {
    private final int position;
    private final int points;
    private final int played;
    private final Team team;
    private final int maxPoints;

    private String canWinLeague = "NO";

    public LeaguePosition(int position, int points, int played, Team team) {
        this.position = position;
        this.points = points;
        this.played = played;
        this.team = team;
        this.maxPoints = points+((38-played)*3);
    }

    public int getPosition() {
        return position;
    }

    // used by ftl
    public int getPoints() {
        return points;
    }

    // used by ftl
    public int getPlayed() {
        return played;
    }

    // used by ftl
    public Team getTeam() {
        return team;
    }

    // used by ftl
    public int getMaxPoints() {
        return maxPoints;
    }

    public void setCanWinLeague(String canWinLeague) {
        this.canWinLeague = canWinLeague;
    }

    // used by ftl
    public String getCanWinLeague() {
        return canWinLeague;
    }
}
