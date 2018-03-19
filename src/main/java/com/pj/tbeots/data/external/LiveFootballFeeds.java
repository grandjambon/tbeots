package com.pj.tbeots.data.external;

import com.pj.tbeots.data.FootballFeeds;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LiveFootballFeeds implements FootballFeeds {

    public static final String LEAGUE_TABLE = "https://www.footballwebpages.co.uk/league-table.json?comp=1&show=pos,p,pts";
    public static final String PL_TEAMS_JSON_URL = "https://www.footballwebpages.co.uk/teams.json?comp=1";
 //   public static final String TEAM_FIXTURES_URL = "https://www.footballwebpages.co.uk/fixtures-results.json?comp=1&results=0&team=%s";
    public static final String TEAM_FIXTURES_URL = "https://www.footballwebpages.co.uk/fixtures-results.json?results=0&team=%s";

    public static final String ALL_FIXTURES_URL = "https://www.footballwebpages.co.uk/fixtures-results.json?results=0&comp=%s";

    @Override
    public Reader getLeagueTableReader() throws IOException {
        URL url = new URL(LEAGUE_TABLE);
        return getReader(url);
    }

    @Override
    public Reader getFixtureListReader(int teamId) throws IOException {
        URL url = new URL(String.format(TEAM_FIXTURES_URL, teamId));
        return getReader(url);
    }

    @Override
    public Reader getAllFixturesReader(int competitionId) throws IOException {
        URL url = new URL(String.format(ALL_FIXTURES_URL, competitionId));
        return getReader(url);
    }

    @Override
    public Reader getTeamsReader() throws IOException {
        URL url = new URL(PL_TEAMS_JSON_URL);
        return getReader(url);
    }

    private Reader getReader(URL url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        return new InputStreamReader(con.getInputStream());

    }
}
