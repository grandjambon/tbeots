package com.pj.tbeots.data;

import com.pj.tbeots.data.external.LiveFootballFeeds;
import com.pj.tbeots.data.json.JsonFixture;
import com.pj.tbeots.data.json.JsonLeaguePosition;
import com.pj.tbeots.data.json.JsonTeam;
import com.pj.tbeots.data.model.Fixture;
import com.pj.tbeots.data.model.FixtureDate;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.pj.tbeots.data.BufferedDataManager.getLiveToken;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DataManagerTest {

    private static final Logger logger = LoggerFactory.getLogger(DataManagerTest.class);

    private DataManager manager;

    @Before
    public void setup() throws IOException {
        manager = new BufferedDataManager(new FootballFeedsDataManager(new LiveFootballFeeds()));
        String token = getLiveToken();
        manager.refreshCache(token);
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
        assertEquals(6, leaguePositions.size());
    }

    @Ignore
    @Test
    public void testFixtures() throws IOException {
        FootballFeedsDataManager dataManager = new FootballFeedsDataManager(new LiveFootballFeeds());

        int arsenalId = dataManager.getTeams().get("Arsenal").getId();

        List<JsonFixture> fixtures = dataManager.getFixtures(arsenalId);
        assertNotNull(fixtures);
        assertThat(fixtures.isEmpty(), is(false));
    }

    @Test
    public void testAllFixtures() throws IOException {
        Map<FixtureDate, List<Fixture>> fixtures = manager.getFixtures();
        assertNotNull(fixtures);
    }

    @Ignore("very slow - probably need to set up a 'slow tests' config")
    @Test
    public void testRebuild() throws IOException, InterruptedException {
        for (int i=10; --i>=0; ) {
            String cacheToken = getLiveToken();
            logger.info("asking for refresh with token = {}", cacheToken);
            manager.refreshCache(cacheToken);
            Thread.sleep(10000);
        }
    }
}