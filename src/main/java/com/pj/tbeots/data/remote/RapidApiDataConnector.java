package com.pj.tbeots.data.remote;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.io.Reader;

public class RapidApiDataConnector {

    private static final String TOP_6_TABLE = "https://football-web-pages1.p.rapidapi.com/league-table.json?comp=1";
    private static final String FIXTURES_URL = "https://football-web-pages1.p.rapidapi.com/fixtures-results.json?team=%s";

    public Reader getFixturesForTeam(int teamId) throws IOException {
        return getReader(String.format(FIXTURES_URL, teamId));
    }

    public Reader getLeagueTable() throws IOException {
        return getReader(TOP_6_TABLE);
    }

    private Reader getReader(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-key", "04add170e1mshff012d38da62bc4p15b7ebjsn838418bb9df5")
                .addHeader("x-rapidapi-host", "football-web-pages1.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();
        ResponseBody body = response.body();
        return body.charStream();
    }
}
