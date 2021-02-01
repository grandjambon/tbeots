package com.pj.tbeots.data.json.rapidapifwp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName(value="league-table")
public class JsonLeagueTable {

    @JsonProperty(value="teams")
    private List<JsonTeam> teams;

    public List<JsonTeam> getTeams() {
        return teams;
    }
}
