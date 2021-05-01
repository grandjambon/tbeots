package com.pj.tbeots.data;

import com.pj.tbeots.data.model.Fixture;
import com.pj.tbeots.data.model.FixtureDate;
import com.pj.tbeots.data.model.LeaguePosition;
import com.pj.tbeots.data.model.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

/**
 * Quick and dirty attempt at reducing our load on the feed
 */
public class BufferedDataManager implements DataManager {

    private static final Logger logger = LoggerFactory.getLogger(BufferedDataManager.class);

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd-HH:mm:ss");

    private static final int REFRESH_BUFFER = 15;

    private final DataManager dataManager;

    private LocalDateTime lastRefresh;
    private Map<String, Team> teams;
    private List<LeaguePosition> leaguePositions;
    private Map<FixtureDate, List<Fixture>> fixtures;

    public BufferedDataManager(DataManager dataManager) {
        this(dataManager, null);
    }

    // for testing
    BufferedDataManager(DataManager dataManager, LocalDateTime lastRefresh) {
        this.dataManager = dataManager;
        this.lastRefresh = lastRefresh;
    }

    public synchronized void refreshCache(LocalDateTime now) throws IOException {
        if (lastRefresh == null || (isRefreshPeriod(now) && now.isAfter(lastRefresh.plus(REFRESH_BUFFER, ChronoUnit.MINUTES)))) {
            logger.info("lastRefresh = {} - currently = {} so rebuilding cache", lastRefresh == null ? null : lastRefresh.format(DATE_TIME_FORMATTER), now.format(DATE_TIME_FORMATTER));

            // in case anything needed
            dataManager.refreshCache(now);

            this.teams = dataManager.getTeams();
            this.leaguePositions = dataManager.getLeaguePositions();
            this.fixtures = dataManager.getFixtures();
            this.lastRefresh = now;
        }
    }

    private boolean isRefreshPeriod(LocalDateTime now) {
        switch(now.getDayOfWeek()) {
            case MONDAY:
            case TUESDAY:
            case WEDNESDAY:
            case THURSDAY:
            case FRIDAY:
                return now.getHour() >= 18 && now.getHour() < 22;
            case SATURDAY:
            case SUNDAY:
                return now.getHour() >= 12 && now.getHour() < 22;
        }
        return false;
    }

    @Override
    public synchronized Map<String, Team> getTeams() throws IOException {
        return teams;
    }

    @Override
    public synchronized List<LeaguePosition> getLeaguePositions() throws IOException {
        return leaguePositions;
    }

    @Override
    public synchronized Map<FixtureDate, List<Fixture>> getFixtures() throws IOException {
        return fixtures;
    }

    public static LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.now(ZoneId.of("Europe/London"));
    }
}
