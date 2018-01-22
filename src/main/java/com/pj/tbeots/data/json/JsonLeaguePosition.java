package com.pj.tbeots.data.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value="team")
public class JsonLeaguePosition {
    @JsonProperty(value="position")
    private int position;

    @JsonProperty(value="name")
    private String name;

    @JsonProperty(value="played")
    private int played;

    @JsonProperty(value="points")
    private int points;

    public int getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public int getPlayed() {
        return played;
    }

    public int getPoints() {
        return points;
    }
}
