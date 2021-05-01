package com.pj.tbeots.data;

import com.pj.tbeots.data.fivethirtyeight.FiveThirtyEightLeagueTable;
import com.pj.tbeots.data.fivethirtyeight.FiveThirtyEightMatch;
import com.pj.tbeots.data.model.Fixture;
import com.pj.tbeots.data.model.FixtureDate;
import com.pj.tbeots.data.model.LeaguePosition;
import com.pj.tbeots.data.model.Team;
import com.pj.tbeots.data.remote.FiveThirtyEightDataConnector;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class FiveThirtyEightDataManager implements DataManager {

    private final FiveThirtyEightDataConnector connector;

    private final FiveThirtyEightLeagueTable table = new FiveThirtyEightLeagueTable();

    private List<FiveThirtyEightMatch> matches;

    public FiveThirtyEightDataManager(FiveThirtyEightDataConnector connector) {
        this.connector = connector;
    }

    @Override
    public void refreshCache(LocalDateTime now) throws IOException {
        FiveThirtyEightCSVParser parser = new FiveThirtyEightCSVParser(connector.getInputStream());
        matches = parser.getMatches().stream().filter(m -> m.getLeague() == 2411).collect(Collectors.toList());

        Set<String> teams = new TreeSet<>();
        matches.forEach(match -> {
            teams.add(match.getHomeTeam());
            teams.add(match.getAwayTeam());
        });

        table.reset(teams);
        matches.forEach(table::applyMatch);
    }

    @Override
    public Map<String, Team> getTeams() throws IOException {
        return null;
    }

    @Override
    public List<LeaguePosition> getLeaguePositions() throws IOException {
        return null;
    }

    @Override
    public Map<FixtureDate, List<Fixture>> getFixtures() throws IOException {
        return null;
    }

    FiveThirtyEightLeagueTable getLeagueTable() {
        return table;
    }

}
