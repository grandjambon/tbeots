package com.pj.tbeots.data;

import com.pj.tbeots.data.json.JsonFixture;
import com.pj.tbeots.data.json.JsonLeaguePosition;
import com.pj.tbeots.data.json.JsonMultiCompFixture;
import com.pj.tbeots.data.json.JsonTeam;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FootballFeedsDataManagerTest {

    private FootballFeedsDataManager manager;

    @Before
    public void setup() throws IOException {
        manager = new FootballFeedsDataManager(new StaticFootballFeeds());
    }

    @Test
    public void testTeams() throws IOException {
        Map<String, JsonTeam> teams = manager.getTeams();
        assertNotNull(teams);
        assertEquals(20, teams.size());
    }

    @Test
    public void testLeagueTable() throws IOException {
        List<JsonLeaguePosition> leaguePositions = manager.getLeaguePositions();
        assertNotNull(leaguePositions);
        assertEquals(20, leaguePositions.size());
    }

    @Test
    public void testFixtures() throws IOException {
        int arsenalId = manager.getTeams().get("Arsenal").getId();

        List<JsonMultiCompFixture> fixtures = manager.getTeamFixtures(arsenalId);
        assertNotNull(fixtures);
        assertThat(fixtures.isEmpty(), is(false));
    }

//    @Test
//    public void testAllFixtures() throws IOException {
//        Map<String, List<Fixture>> fixtures = manager.getFixtures();
//        assertNotNull(fixtures);
//    }

    @Test
    public void testAllFixtures() throws IOException {
        List<JsonFixture> allFixtures = manager.getAllFixtures(1);
        assertNotNull(allFixtures);
        assertThat(allFixtures.isEmpty(), is(false));
    }

//    @Ignore("very slow - probably need to set up a 'slow tests' config")
//    @Test
//    public void testRebuild() throws IOException, InterruptedException {
//        for (int i=10; --i>=0; ) {
//            String cacheToken = getLiveToken();
//            logger.info("asking for refresh with token = {}", cacheToken);
//            manager.refreshCache(cacheToken);
//            Thread.sleep(10000);
//        }
//    }
}