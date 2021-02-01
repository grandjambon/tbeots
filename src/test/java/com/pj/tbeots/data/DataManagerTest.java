//package com.pj.tbeots.data;
//
//import com.pj.tbeots.data.external.LiveFootballFeeds;
//import com.pj.tbeots.data.external.RapidApiFWPFeeds;
//import com.pj.tbeots.data.json.JsonFixture;
//import com.pj.tbeots.data.json.JsonLeaguePosition;
//import com.pj.tbeots.data.json.JsonTeam;
//import com.pj.tbeots.data.model.Fixture;
//import com.pj.tbeots.data.model.FixtureDate;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.IOException;
//import java.util.Collection;
//import java.util.List;
//import java.util.Map;
//import java.util.TreeSet;
//
//import static com.pj.tbeots.data.BufferedDataManager.getLiveToken;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//public class DataManagerTest {
//
//    private static final Logger logger = LoggerFactory.getLogger(DataManagerTest.class);
//
//    private DataManager manager;
//    private Collection<String> neutralRounds;
//
//    @BeforeEach
//    public void setup() throws IOException {
//        neutralRounds = new TreeSet<>();
//        neutralRounds.add("Carabao Cup"+"-"+"Final");
//        neutralRounds.add("Emirates FA Cup"+"-"+"Semi Final");
//        neutralRounds.add("Emirates FA Cup"+"-"+"Final");
//        neutralRounds.add("UEFA Champions League"+"-"+"Final");
//
//        manager = new BufferedDataManager(new FootballFeedsDataManager(new RapidApiFWPFeeds(), neutralRounds));
//        String token = getLiveToken();
//        manager.refreshCache(token);
//    }
//
//    @Test
//    public void testTeams() throws IOException {
//        Map<String, JsonTeam> teams = manager.getTeams();
//        assertNotNull(teams);
//        assertEquals(20, teams.size());
//    }
//
//    @Test
//    public void testLeagueTable() throws IOException {
//        List<JsonLeaguePosition> leaguePositions = manager.getLeaguePositions();
//        assertNotNull(leaguePositions);
//        assertEquals(6, leaguePositions.size());
//    }
//
//    @Disabled
//    @Test
//    public void testFixtures() throws IOException {
//        FootballFeedsDataManager dataManager = new FootballFeedsDataManager(new LiveFootballFeeds(), neutralRounds);
//
//        int arsenalId = dataManager.getTeams().get("Arsenal").getId();
//
//        List<JsonFixture> fixtures = dataManager.getFixtures(arsenalId);
//        assertNotNull(fixtures);
//        assertThat(fixtures).isNotEmpty();
//    }
//
//    @Test
//    public void testAllFixtures() throws IOException {
//        Map<FixtureDate, List<Fixture>> fixtures = manager.getFixtures();
//        assertNotNull(fixtures);
//    }
//
//    @Disabled
//    @Test
//    public void testRebuild() throws IOException, InterruptedException {
//        for (int i=10; --i>=0; ) {
//            String cacheToken = getLiveToken();
//            logger.info("asking for refresh with token = {}", cacheToken);
//            manager.refreshCache(cacheToken);
//            Thread.sleep(10000);
//        }
//    }
//}