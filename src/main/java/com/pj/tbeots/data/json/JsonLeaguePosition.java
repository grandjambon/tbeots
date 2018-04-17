package com.pj.tbeots.data.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.pj.tbeots.data.TeamNameMapper;

import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName(value="team")
public class JsonLeaguePosition {

    private static Map<String, String> backgroundColorMap = new HashMap<>();
    private static Map<String, String> foregroundColorMap = new HashMap<>();
    static {
        backgroundColorMap.put("City", "#80d4ff");
        backgroundColorMap.put("United", "#ff3300");
        backgroundColorMap.put("Arsenal", "#ff0000");
        backgroundColorMap.put("Chelsea", "#0000e6");
        backgroundColorMap.put("Liverpool", "#ff3333");
        backgroundColorMap.put("Spurs", "#ffffff");
        foregroundColorMap.put("Chelsea", "#ffffff");
    }

    @JsonProperty(value="position")
    private int position;

    private String name;

    @JsonProperty(value="played")
    private int played;

    @JsonProperty(value="points")
    private int points;

    private String canWinLeague;

    private String backgroundColor = "white";
    private String foregroundColor = "black";

    @JsonSetter(value="name")
    public void setName(String name) {
        this.name = TeamNameMapper.mapTeamName(name);
        if (backgroundColorMap.containsKey(this.name)) {
            this.backgroundColor = backgroundColorMap.get(this.name);
        }
        if (foregroundColorMap.containsKey(this.name)) {
            this.foregroundColor = foregroundColorMap.get(this.name);
        }
    }

    public int getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public int getPlayed() {
        return played;
    }

    public int getPoints() {
        return points;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getForegroundColor() {
        return foregroundColor;
    }

    public int getMaxPoints() {
        return ((38 - played) * 3) + points;
    }

    public void setCanWinLeague(String canWinLeague) {
        this.canWinLeague = canWinLeague;
    }

    public String getCanWinLeague() {
        return this.canWinLeague;
    }
}
