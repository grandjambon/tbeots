package com.pj.tbeots.data.json.rapidapifwp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.pj.tbeots.data.TeamNameMapper;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonTeam {
    @JsonProperty("id")
    private int id;

    private String name;

    @JsonProperty("total-points")
    private int points;

    @JsonProperty("position")
    private int position;

    @JsonProperty("all-matches")
    private Map<String, Integer> allMatches;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @JsonSetter("name")
    public void setName(String name) {
        this.name = TeamNameMapper.mapTeamName(name);
    }

    public int getPoints() {
        return points;
    }

    public int getPosition() {
        return position;
    }

    public int getPlayed() {
        return allMatches.get("played");
    }
}
