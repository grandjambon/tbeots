package com.pj.tbeots.data;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Loads the data in from static files
 */
public class StaticFootballFeeds implements FootballFeeds {
    @Override
    public Reader getTeamsReader() throws IOException {
        String filename = "teams.json";
        return getReader(filename);
    }

    @Override
    public Reader getLeagueTableReader() throws IOException {
        String filename = "league-table.json";
        return getReader(filename);
    }

    @Override
    public Reader getFixtureListReader(int teamId) throws IOException {
        String filename = "team-fixtures.json";
        return getReader(filename);
    }

    @Override
    public Reader getAllFixturesReader(int competition) throws IOException {
        String filename = "all-fixtures.json";
        return getReader(filename);
    }

    private Reader getReader(String filename) {
        return new InputStreamReader(getClass().getResourceAsStream("/data/json/"+filename));
    }
}
