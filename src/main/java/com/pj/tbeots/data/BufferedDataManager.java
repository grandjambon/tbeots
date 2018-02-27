package com.pj.tbeots.data;

import com.pj.tbeots.data.json.JsonLeaguePosition;
import com.pj.tbeots.data.json.JsonTeam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * Quick and dirty attempt at reducing our load on the feed
 */
public class BufferedDataManager implements DataManager {

    private static final Logger logger = LoggerFactory.getLogger(BufferedDataManager.class);

    private static final DateTimeFormatter tokenFormatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmm");
    
    private final DataManager dataManager;

    private String cacheToken;
    private Map<String, JsonTeam> teams;
    private List<JsonLeaguePosition> leaguePositions;
    private Map<String, List<String>> fixtures;

    public BufferedDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public synchronized void refreshCache(String cacheToken) throws IOException {
        if (this.cacheToken == null || !this.cacheToken.equals(cacheToken)) {
            logger.info("token received = {} - our token = {} so rebuilding cache", cacheToken, this.cacheToken);
            this.teams = dataManager.getTeams();
            this.leaguePositions = dataManager.getLeaguePositions();
            this.fixtures = dataManager.getFixtures();
            this.cacheToken = cacheToken;
        }
    }

    @Override
    public synchronized Map<String, JsonTeam> getTeams() throws IOException {
        return teams;
    }

    @Override
    public synchronized List<JsonLeaguePosition> getLeaguePositions() throws IOException {
        return leaguePositions;
    }

    @Override
    public synchronized Map<String, List<String>> getFixtures() throws IOException {
        return fixtures;
    }

    public static String getLiveToken() {
        return LocalDateTime.now(ZoneId.of("UTC")).format(tokenFormatter);
    }
}
