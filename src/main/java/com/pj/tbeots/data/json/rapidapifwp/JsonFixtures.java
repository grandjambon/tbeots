package com.pj.tbeots.data.json.rapidapifwp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.pj.tbeots.data.TeamNameMapper;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName(value="fixtures-results")
public class JsonFixtures {

    private String teamName;
    private String teamId;

    @JsonProperty("matches")
    private List<JsonMatch> matches;

    @JsonSetter("team")
    public void setTeam(Map<String, String> values) {
        this.teamId = values.get("id");
        this.teamName = TeamNameMapper.mapTeamName(values.get("name"));
    }

    public String getTeamName() {
        return teamName;
    }

    public String getTeamId() {
        return teamId;
    }

    public List<JsonMatch> getMatches() {
        return matches;
    }
}
