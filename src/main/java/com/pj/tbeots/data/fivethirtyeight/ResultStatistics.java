package com.pj.tbeots.data.fivethirtyeight;

import java.math.BigDecimal;

public class ResultStatistics {

    public enum Result {HOME_WIN, DRAW, AWAY_WIN}

    private final int homeGoals;
    private final int awayGoals;
    private final BigDecimal homeXG;
    private final BigDecimal awayXG;
    private final Result result;

    public ResultStatistics(int homeGoals, int awayGoals, BigDecimal homeXG, BigDecimal awayXG) {
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
        this.homeXG = homeXG;
        this.awayXG = awayXG;

        if (homeGoals > awayGoals) {
            this.result = Result.HOME_WIN;
        } else if (awayGoals > homeGoals) {
            this.result = Result.AWAY_WIN;
        } else {
            this.result = Result.DRAW;
        }
    }

    public int getHomeGoals() {
        return homeGoals;
    }

    public int getAwayGoals() {
        return awayGoals;
    }

    public BigDecimal getHomeXG() {
        return homeXG;
    }

    public BigDecimal getAwayXG() {
        return awayXG;
    }

    public Result getResult() {
        return result;
    }
}
