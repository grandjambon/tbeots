package com.pj.tbeots.data.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value="team")
public class JsonTeam {

    @JsonProperty(value="id")
    private int id;

    @JsonProperty(value="name")
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
