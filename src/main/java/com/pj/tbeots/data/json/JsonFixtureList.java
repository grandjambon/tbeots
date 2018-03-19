package com.pj.tbeots.data.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.pj.tbeots.data.TeamNameMapper;

import java.util.List;

@JsonRootName(value="matchesCompetition")
public class JsonFixtureList {

    private String team;

    @JsonProperty(value="description")
    private String description;

    @JsonProperty(value="competition")
    private String competition;

    @JsonProperty(value="match")
    private List<JsonFixture> fixtures;

    @JsonSetter(value="team")
    public void setTeam(String team) {
        this.team = TeamNameMapper.mapTeamName(team);
    }

    public List<JsonFixture> getFixtures() {
        return fixtures;
    }

    public String getCompetition() {
        return competition;
    }
}
