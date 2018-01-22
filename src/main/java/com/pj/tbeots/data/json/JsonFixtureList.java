package com.pj.tbeots.data.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.pj.tbeots.data.TeamNameMapper;

import java.util.List;

@JsonRootName(value="matchesTeam")
public class JsonFixtureList {

    private String team;

    @JsonProperty(value="competition")
    private String competition;

    @JsonProperty(value="description")
    private String description;


    @JsonProperty(value="match")
    private List<JsonFixture> fixtures;

    @JsonSetter(value="team")
    public void setTeam(String team) {
        this.team = TeamNameMapper.mapTeamName(team);
    }

    public String getCompetition() {
        return competition;
    }

    public List<JsonFixture> getFixtures() {
        return fixtures;
    }

}
