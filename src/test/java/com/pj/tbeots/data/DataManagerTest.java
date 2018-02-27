package com.pj.tbeots.data;

import com.pj.tbeots.data.external.LiveFootballFeeds;
import com.pj.tbeots.data.json.JsonFixture;
import com.pj.tbeots.data.json.JsonLeaguePosition;
import com.pj.tbeots.data.json.JsonTeam;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DataManagerTest {

    @Test
    public void testTeams() throws IOException {
        DataManager manager = new DataManager(new LiveFootballFeeds());
        Map<String, JsonTeam> teams = manager.getTeams();
        assertNotNull(teams);
        assertEquals(20, teams.size());
    }

    @Test
    public void testLeagueTable() throws IOException {
        DataManager manager = new DataManager(new LiveFootballFeeds());
        List<JsonLeaguePosition> leaguePositions = manager.getLeagueTable();
        assertNotNull(leaguePositions);
        assertEquals(6, leaguePositions.size());
    }

    @Test
    public void testFixtures() throws IOException {
        DataManager manager = new DataManager(new LiveFootballFeeds());

        int arsenalId = manager.getTeams().get("Arsenal").getId();

        List<JsonFixture> fixtures = manager.getFixtures(arsenalId);
        assertNotNull(fixtures);
        assertThat(fixtures.isEmpty(), is(false));
    }

    @Test
    public void testAllFixtures() throws IOException {
        DataManager manager = new DataManager(new LiveFootballFeeds());
        Map<String, List<String>> fixtures = manager.getFixtures();
        assertNotNull(fixtures);
    }
}