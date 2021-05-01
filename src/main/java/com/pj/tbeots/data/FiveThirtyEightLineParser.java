package com.pj.tbeots.data;

import com.pj.tbeots.data.fivethirtyeight.FiveThirtyEightMatch;
import com.pj.tbeots.data.fivethirtyeight.ResultStatistics;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.lang.Integer.parseInt;

public class FiveThirtyEightLineParser {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final int SEASON_STARTED = 0;
    private static final int DATE_STRING = 1;
    private static final int LEAGUE_ID = 2;
    private static final int COMPETITION = 3;
    private static final int HOME_TEAM = 4;
    private static final int AWAY_TEAM = 5;
    private static final int HOME_SPI = 6;
    private static final int AWAY_SPI = 7;
    private static final int PROB_HOME_WIN = 8;
    private static final int PROB_AWAY_WIN = 9;
    private static final int PROB_DRAW = 10;
    private static final int HOME_GOALS_SCORED = 11;
    private static final int AWAY_GOALS_SCORED = 12;
    private static final int HOME_XG = 13;
    private static final int AWAY_XG = 14;

    public static FiveThirtyEightMatch parseLine(String line) {
        String[] values = line.split(",");
        return new FiveThirtyEightMatch(
                values[SEASON_STARTED],
                LocalDate.parse(values[DATE_STRING], DATE_FORMAT),
                parseInt(values[LEAGUE_ID]),
                values[COMPETITION],
                TeamNameMapper.mapTeamName(values[HOME_TEAM]),
                TeamNameMapper.mapTeamName(values[AWAY_TEAM]),
                new BigDecimal(values[HOME_SPI]),
                new BigDecimal(values[AWAY_SPI]),
                new BigDecimal(values[PROB_HOME_WIN]),
                new BigDecimal(values[PROB_AWAY_WIN]),
                new BigDecimal(values[PROB_DRAW]),
                parseMatchStats(values)
        );
    }

    private static ResultStatistics parseMatchStats(String[] values) {
        String homeGoals = values[HOME_GOALS_SCORED];
        String awayGoals = values[AWAY_GOALS_SCORED];
        String homeXG = values[HOME_XG];
        String awayXG = values[AWAY_XG];

        try {
            return new ResultStatistics(parseInt(homeGoals), parseInt(awayGoals), new BigDecimal(homeXG), new BigDecimal(awayXG));
        } catch (NumberFormatException nfe) {
            return null;
        }
    }
}
