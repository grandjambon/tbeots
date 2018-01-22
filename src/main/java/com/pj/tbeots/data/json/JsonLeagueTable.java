package com.pj.tbeots.data.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

@JsonRootName(value="leagueTable")
public class JsonLeagueTable {
    @JsonProperty(value="competition")
    private String competition;

    @JsonProperty(value="description")
    private String description;


    @JsonProperty(value="team")
    private List<JsonLeaguePosition> leaguePositions;

    public String getCompetition() {
        return competition;
    }

    public List<JsonLeaguePosition> getLeaguePositions() {
        return leaguePositions;
    }

}
