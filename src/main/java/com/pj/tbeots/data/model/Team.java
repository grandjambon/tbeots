package com.pj.tbeots.data.model;

public class Team {
    private final String name;
    private final int id;
    private final int position;
    private final int points;
    private final int played;

    public Team(String name, int id, int position, int points, int played) {
        this.name = name;
        this.id = id;
        this.position = position;
        this.points = points;
        this.played = played;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
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
}
