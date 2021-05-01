package com.pj.tbeots.data.remote;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class FiveThirtyEightDataConnector {
    public InputStream getInputStream() throws IOException {
        URL url = new URL("https://projects.fivethirtyeight.com/soccer-api/club/spi_matches_latest.csv");
        URLConnection urlConn = url.openConnection();
        urlConn.setDoInput(true);
        urlConn.setUseCaches(false);

        return urlConn.getInputStream();
    }
}
