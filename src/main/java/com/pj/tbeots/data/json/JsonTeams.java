package com.pj.tbeots.data.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName(value="teams")
public class JsonTeams {
    @JsonProperty(value="competition")
    private String competition;

    @JsonProperty(value="description")
    private String description;


    @JsonProperty(value="team")
    private List<JsonTeam> teams;

    public String getCompetition() {
        return competition;
    }

    public List<JsonTeam> getTeams() {
        return teams;
    }
}
