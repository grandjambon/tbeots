package com.pj.tbeots.data.fivethirtyeight;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.pj.tbeots.data.fivethirtyeight.FiveThirtyEightMatch.Type.FIXTURE;
import static com.pj.tbeots.data.fivethirtyeight.FiveThirtyEightMatch.Type.RESULT;

public class FiveThirtyEightMatch {

    public enum Type {
        FIXTURE, RESULT;
    }

    private final String seasonYearStart;
    private final LocalDate date;
    private final int league;
    private final String leagueName;
    private final String homeTeam;
    private final String awayTeam;

    private final BigDecimal homeSpi;
    private final BigDecimal awaySpi;

    private final BigDecimal probHomeWin;
    private final BigDecimal probAwayWin;
    private final BigDecimal probDraw;

    private final ResultStatistics resultStatistics;

    public FiveThirtyEightMatch(String seasonYearStart, LocalDate date, int league, String leagueName, String homeTeam, String awayTeam, BigDecimal homeSpi, BigDecimal awaySpi, BigDecimal probHomeWin, BigDecimal probAwayWin, BigDecimal probDraw, ResultStatistics resultStatistics) {
        this.seasonYearStart = seasonYearStart;
        this.date = date;
        this.league = league;
        this.leagueName = leagueName;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeSpi = homeSpi;
        this.awaySpi = awaySpi;
        this.probHomeWin = probHomeWin;
        this.probAwayWin = probAwayWin;
        this.probDraw = probDraw;
        this.resultStatistics = resultStatistics;
    }

    public Type getType() {
        return resultStatistics == null ? FIXTURE : RESULT;
    }

    public String getSeasonYearStart() {
        return seasonYearStart;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getLeague() {
        return league;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public BigDecimal getHomeSpi() {
        return homeSpi;
    }

    public BigDecimal getAwaySpi() {
        return awaySpi;
    }

    public BigDecimal getProbHomeWin() {
        return probHomeWin;
    }

    public BigDecimal getProbAwayWin() {
        return probAwayWin;
    }

    public BigDecimal getProbDraw() {
        return probDraw;
    }

    public ResultStatistics getResultStatistics() {
        return resultStatistics;
    }
}
