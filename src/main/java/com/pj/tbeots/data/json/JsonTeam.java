package com.pj.tbeots.data.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.pj.tbeots.data.TeamNameMapper;

import java.util.HashMap;
import java.util.Map;

@JsonRootName(value="team")
public class JsonTeam {

    @JsonProperty(value="id")
    private int id;

    private String name;

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

}
