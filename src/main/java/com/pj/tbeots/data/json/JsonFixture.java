package com.pj.tbeots.data.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.pj.tbeots.data.model.Fixture;

import static com.pj.tbeots.data.TeamNameMapper.mapTeamName;
import static com.pj.tbeots.data.json.JsonUtils.formatDate;
import static com.pj.tbeots.data.model.Fixture.HomeOrAway.away;
import static com.pj.tbeots.data.model.Fixture.HomeOrAway.home;

@SuppressWarnings("unused")
@JsonRootName(value="match")
public class JsonFixture {

    @JsonProperty(value="id")
    private int id;

    private String date;

    @JsonProperty(value="time")
    private String time;

    @JsonProperty(value="status")
    private String status;

    @JsonProperty(value="homeTeamNo")
    private int homeTeamId;

    private String homeTeam;

    @JsonProperty(value="awayTeamNo")
    private int awayTeamId;

    private String awayTeam;

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }

    public int getHomeTeamId() {
        return homeTeamId;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    @JsonSetter(value="homeTeamName")
    public void setHomeTeam(String homeTeam) {
        this.homeTeam = mapTeamName(homeTeam);
    }

    @JsonSetter(value="date")
    public void setDate(String date) {
        this.date = formatDate(date);
    }

    public int getAwayTeamId() {
        return awayTeamId;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    @JsonSetter(value="awayTeamName")
    public void setAwayTeam(String awayTeam) {
        this.awayTeam = mapTeamName(awayTeam);
    }

    public boolean involved(String name) {
        return name.equals(homeTeam) || name.equals(awayTeam);
    }

    public Fixture toFixture(String nameFromPerspectiveOf) {
        if (nameFromPerspectiveOf.equals(homeTeam)) {
            return new Fixture(date, time, awayTeam, "Premier League", home);
        } else if (nameFromPerspectiveOf.equals(awayTeam)) {
            return new Fixture(date, time, homeTeam, "Premier League", away);
        } else {
            throw new IllegalArgumentException("Team " + nameFromPerspectiveOf + " is not involved in this fixture");
        }
    }
}
