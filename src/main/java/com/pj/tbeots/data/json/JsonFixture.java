package com.pj.tbeots.data.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value="match")
public class JsonFixture {

    @JsonProperty(value="id")
    private int id;
    @JsonProperty(value="date")
    private String date;
    @JsonProperty(value="time")
    private String time;
    @JsonProperty(value="competition")
    private String competition;
    @JsonProperty(value="status")
    private String status;
    @JsonProperty(value="homeTeamNo")
    private int homeTeamId;
    @JsonProperty(value="homeTeamName")
    private String homeTeam;
    @JsonProperty(value="awayTeamNo")
    private int awayTeamId;
    @JsonProperty(value="awayTeamName")
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

    public int getAwayTeamId() {
        return awayTeamId;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

}
