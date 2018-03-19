package com.pj.tbeots.data;

import java.io.IOException;
import java.io.Reader;

public interface FootballFeeds {
    Reader getTeamsReader() throws IOException;

    Reader getLeagueTableReader() throws IOException;

    Reader getFixtureListReader(int teamId) throws IOException;

    Reader getAllFixturesReader(int competition) throws IOException;
}
