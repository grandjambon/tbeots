package com.pj.tbeots.data.fivethirtyeight;

import java.math.BigDecimal;
import java.util.Comparator;

public class FiveThirtyEightLeaguePosition {
    private final String team;

    private int homePlayed = 0;
    private int homeWins = 0;
    private int homeDraws = 0;
    private int homeLosses = 0;
    private int homeGoalsFor = 0;
    private int homeGoalsAgainst = 0;
    private int homePoints = 0;
    private BigDecimal homeXgFor = BigDecimal.ZERO;
    private BigDecimal homeXgAgainst = BigDecimal.ZERO;

    private int awayPlayed = 0;
    private int awayWins = 0;
    private int awayDraws = 0;
    private int awayLosses = 0;
    private int awayGoalsFor = 0;
    private int awayGoalsAgainst = 0;
    private int awayPoints = 0;
    private BigDecimal awayXgFor = BigDecimal.ZERO;
    private BigDecimal awayXgAgainst = BigDecimal.ZERO;

    public FiveThirtyEightLeaguePosition(String team) {
        this.team = team;
    }

    public void homeMatch(int goalsFor, int goalsAgainst, BigDecimal xgFor, BigDecimal xgAgainst) {
        if (goalsFor > goalsAgainst) {
            homeWins++;
        } else if (goalsFor < goalsAgainst) {
            homeLosses++;
        } else {
            homeDraws++;
        }
        homeGoalsFor += goalsFor;
        homeGoalsAgainst += goalsAgainst;
        homeXgFor = homeXgFor.add(xgFor);
        homeXgAgainst = homeXgAgainst.add(xgAgainst);
    }

    public void awayMatch(int goalsFor, int goalsAgainst, BigDecimal xgFor, BigDecimal xgAgainst) {
        if (goalsFor > goalsAgainst) {
            awayWins++;
        } else if (goalsFor < goalsAgainst) {
            awayLosses++;
        } else {
            awayDraws++;
        }
        awayGoalsFor += goalsFor;
        awayGoalsAgainst += goalsAgainst;
        awayXgFor = awayXgFor.add(xgFor);
        awayXgAgainst = awayXgAgainst.add(xgAgainst);
    }

    public String getTeam() {
        return team;
    }

    public int getHomePlayed() {
        return homePlayed;
    }

    public int getHomeWins() {
        return homeWins;
    }

    public int getHomeDraws() {
        return homeDraws;
    }

    public int getHomeLosses() {
        return homeLosses;
    }

    public int getHomeGoalsFor() {
        return homeGoalsFor;
    }

    public int getHomeGoalsAgainst() {
        return homeGoalsAgainst;
    }

    public BigDecimal getHomeXgFor() {
        return homeXgFor;
    }

    public BigDecimal getHomeXgAgainst() {
        return homeXgAgainst;
    }

    public int getAwayPlayed() {
        return awayPlayed;
    }

    public int getAwayWins() {
        return awayWins;
    }

    public int getAwayDraws() {
        return awayDraws;
    }

    public int getAwayLosses() {
        return awayLosses;
    }

    public int getAwayGoalsFor() {
        return awayGoalsFor;
    }

    public int getAwayGoalsAgainst() {
        return awayGoalsAgainst;
    }

    public BigDecimal getAwayXgFor() {
        return awayXgFor;
    }

    public BigDecimal getAwayXgAgainst() {
        return awayXgAgainst;
    }

    public int getPlayed() {
        return homePlayed + awayPlayed;
    }

    public int getWins() {
        return homeWins + awayWins;
    }

    public int getDraws() {
        return homeDraws + awayDraws;
    }

    public int getLosses() {
        return homeLosses + awayLosses;
    }

    public int getGoalsFor() {
        return homeGoalsFor + awayGoalsFor;
    }

    public int getGoalsAgainst() {
        return homeGoalsAgainst + awayGoalsAgainst;
    }

    public BigDecimal getXgFor() {
        return homeXgFor.add(awayXgFor);
    }

    public BigDecimal getXgAgainst() {
        return homeXgAgainst.add(awayXgAgainst);
    }

    public int getHomePoints() {
        return homePoints;
    }

    public int getAwayPoints() {
        return awayPoints;
    }

    public int getPoints() {
        return homePoints + awayPoints;
    }

    public int getHomeGoalDiff() {
        return homeGoalsFor - homeGoalsAgainst;
    }

    public int getAwayGoalDiff() {
        return awayGoalsFor - awayGoalsAgainst;
    }

    public int getGoalDiff() {
        return getHomeGoalDiff() + getAwayGoalDiff();
    }

    public static Comparator<FiveThirtyEightLeaguePosition> positionComparator() {
        return Comparator.comparing(FiveThirtyEightLeaguePosition::getPoints)
                .thenComparing(FiveThirtyEightLeaguePosition::getGoalDiff)
                .thenComparing(FiveThirtyEightLeaguePosition::getGoalsFor)
                .thenComparing(FiveThirtyEightLeaguePosition::getTeam);
    }
}
