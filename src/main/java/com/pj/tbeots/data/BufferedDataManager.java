package com.pj.tbeots.data;

import com.pj.tbeots.data.json.JsonFixture;
import com.pj.tbeots.data.json.JsonLeaguePosition;
import com.pj.tbeots.data.json.JsonTeam;
import com.pj.tbeots.data.model.Fixture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Quick and dirty attempt at reducing our load on the feed
 */
public class BufferedDataManager {

    private static final Logger logger = LoggerFactory.getLogger(BufferedDataManager.class);

    private static final DateTimeFormatter tokenFormatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmm");
    
    private final FootballFeedsDataManager dataManager;

    private String cacheToken;
    private Map<String, JsonTeam> teams;
    private List<JsonLeaguePosition> leaguePositions;

    private List<JsonFixture> allFixtures;

    public BufferedDataManager(FootballFeedsDataManager dataManager) {
        this.dataManager = dataManager;
    }

    public synchronized void refreshCache() throws IOException {
        String liveToken = getLiveToken();

        if (this.cacheToken == null || !this.cacheToken.equals(liveToken)) {
            logger.info("token received = {} - our token = {} so rebuilding cache", liveToken, this.cacheToken);
            this.teams = dataManager.getTeams();
            this.leaguePositions = dataManager.getLeaguePositions();
            this.allFixtures = dataManager.getAllFixtures(1);
            this.cacheToken = liveToken;
        }
    }

    public synchronized Map<String, JsonTeam> getTeams() {
        return teams;
    }

    public synchronized List<JsonLeaguePosition> getLeaguePositions(Predicate<JsonLeaguePosition> predicate) {
        return leaguePositions.stream().filter(predicate).collect(Collectors.toList());
    }

    public synchronized Map<String, Fixture> getFixtures(String name) {
        return allFixtures.stream().filter(jsonFixture -> jsonFixture.involved(name)).collect(Collectors.toMap(t -> t.getDate(), t-> t.toFixture(name)));
    }

    private static String getLiveToken() {
        return LocalDateTime.now(ZoneId.of("UTC")).format(tokenFormatter);
    }
}
