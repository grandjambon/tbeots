package com.pj.tbeots.data.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;

import static com.pj.tbeots.data.TeamNameMapper.mapTeamName;
import static com.pj.tbeots.data.json.JsonUtils.formatDate;

@SuppressWarnings("unused")
@JsonRootName(value="match")
public class JsonMultiCompFixture {

    @JsonProperty(value="id")
    private int id;

    private String date;

    @JsonProperty(value="time")
    private String time;
    @JsonProperty(value="competition")
    private String competition;
    @JsonProperty(value="status")
    private String status;
    @JsonProperty(value="homeTeamNo")
    private int homeTeamId;

    private String homeTeam;
    @JsonProperty(value="awayTeamNo")
    private int awayTeamId;

    private String awayTeam;
    @JsonProperty(value="round")
    private String round;

    @JsonProperty(value="homeTeamAggregateScore")
    private int homeTeamAggScore;
    @JsonProperty(value="awayTeamAggregateScore")
    private int awayTeamAggScore;



    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getCompetition() {
        return competition;
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

    @JsonSetter(value="date")
    public void setDate(String date) {
        this.date = formatDate(date);
    }

    @JsonSetter(value="homeTeamName")
    public void setHomeTeam(String homeTeam) {
        this.homeTeam = mapTeamName(homeTeam);
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
}
