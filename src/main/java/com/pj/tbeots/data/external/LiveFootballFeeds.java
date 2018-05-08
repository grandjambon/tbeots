package com.pj.tbeots.data.external;

import com.pj.tbeots.data.FootballFeeds;
import com.pj.tbeots.data.model.Contenders;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LiveFootballFeeds implements FootballFeeds {

    public static final String TOP_6_TABLE = "https://www.footballwebpages.co.uk/league-table.json?comp=1&start=15&range=6&show=pos,p,pts";
    public static final String PL_TEAMS_JSON_URL = "https://www.footballwebpages.co.uk/teams.json?comp=1";
 //   public static final String FIXTURES_URL = "https://www.footballwebpages.co.uk/fixtures-results.json?comp=1&results=0&team=%s";
    public static final String FIXTURES_URL = "https://www.footballwebpages.co.uk/fixtures-results.json?results=0&team=%s";

    public Contenders getContenders() {
        return null;
    }

    @Override
    public Reader getTop6TeamsReader() throws IOException {
        URL obj = new URL(TOP_6_TABLE);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        return new InputStreamReader(con.getInputStream());
    }

    @Override
    public Reader getFixtureListReader(int teamId) throws IOException {
        URL obj = new URL(String.format(FIXTURES_URL, teamId));
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        return new InputStreamReader(con.getInputStream());
    }

    @Override
    public Reader getTeamsReader() throws IOException {
        URL obj = new URL(PL_TEAMS_JSON_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        return new InputStreamReader(con.getInputStream());
    }
}
