package com.pj.tbeots.data.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

@JsonRootName(value="matchesTeam")
public class JsonFixtureList {
    @JsonProperty(value="team")
    private String team;

    @JsonProperty(value="competition")
    private String competition;

    @JsonProperty(value="description")
    private String description;


    @JsonProperty(value="match")
    private List<JsonFixture> fixtures;

    public String getCompetition() {
        return competition;
    }

    public List<JsonFixture> getFixtures() {
        return fixtures;
    }

}
