package com.pj.tbeots.data.json.rapidapifwp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

// from fixture-results in Fixtures/Results
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonMatch {

    public enum Status { RESULT, LIVE, FIXTURE;}

    @JsonProperty("id")
    private int matchId;

    private String dateString;
    private LocalDate date;

    @JsonProperty
    private String venue;

    // set in competition setter
    private String competitionName;
    private String competitionId;

    private int homeGoals;
    private String homeTeam;
    private int homeTeamId;

    private int awayGoals;
    private String awayTeam;
    private int awayTeamId;

    private String round;
    private int roundId;

    @JsonProperty
    private String time;
    @JsonProperty
    private int attendance;

    @JsonProperty
    private String referee;

    private Status status;

    private String fullStatus;

    private String shortStatus;

    @JsonSetter
    private void setDate(String dateString) {
        this.dateString = dateString;
        this.date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @JsonSetter("competition")
    private void setCompetition(Map<String, String> values) {
        this.competitionName = values.get("name");
        this.competitionId = values.get("id");
    }
    @JsonSetter("home-team")
    private void setHomeTeam(Map<String, String> values) {
        this.homeTeam = values.get("name");
        this.homeGoals = Integer.parseInt(values.get("score"));
        this.homeTeamId = Integer.parseInt(values.get("id"));
    }
    @JsonSetter("away-team")
    private void setAwayTeam(Map<String, String> values) {
        this.awayTeam = values.get("name");
        this.awayGoals = Integer.parseInt(values.get("score"));
        this.awayTeamId = Integer.parseInt(values.get("id"));
    }

    @JsonSetter("status")
    private void setStatus(Map<String, String> values) {
        this.fullStatus = values.get("full");
        this.shortStatus = values.get("short");
        if ("Full Time".equals(fullStatus)) {
            this.status = Status.RESULT;
        } else if (fullStatus.startsWith("Kick off")) {
            this.status = Status.FIXTURE;
        } else {
            this.status = Status.LIVE;
        }
    }

    @JsonSetter("round")
    private void setRound(Map<String, String> values) {
        this.round = values.get("round");
        this.roundId = Integer.parseInt(values.get("id"));
    }

    public int getAttendence() {
        return attendance;
    }

    public String getDateString() {
        return dateString;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getVenue() {
        return venue;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public String getCompetitionId() {
        return competitionId;
    }

    public int getHomeGoals() {
        return homeGoals;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public int getHomeTeamId() {
        return homeTeamId;
    }

    public int getAwayGoals() {
        return awayGoals;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getAwayTeamId() {
        return awayTeamId;
    }

    public String getTime() {
        return time;
    }

    public String getReferee() {
        return referee;
    }

    public Status getStatus() {
        return status;
    }

    public String getFullStatus() {
        return fullStatus;
    }

    public String getShortStatus() {
        return shortStatus;
    }

    public int getMatchId() {
        return matchId;
    }

    public String getRound() {
        return round;
    }

    public int getRoundId() {
        return roundId;
    }
}
