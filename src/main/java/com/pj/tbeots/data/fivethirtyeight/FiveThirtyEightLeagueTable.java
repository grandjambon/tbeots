package com.pj.tbeots.data.fivethirtyeight;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import static com.pj.tbeots.data.fivethirtyeight.FiveThirtyEightLeaguePosition.positionComparator;

public class FiveThirtyEightLeagueTable {

    private final Set<FiveThirtyEightLeaguePosition> positions;

    public FiveThirtyEightLeagueTable() {
        this(new TreeSet<>(positionComparator()));
    }

    FiveThirtyEightLeagueTable(Set<FiveThirtyEightLeaguePosition> positions) {
        this.positions = positions;
    }

    public void reset(Collection<String> teams) {
        positions.clear();
        teams.stream().map(FiveThirtyEightLeaguePosition::new).forEach(this.positions::add);
    }

    public void applyMatch(FiveThirtyEightMatch match) {
        String homeTeam = match.getHomeTeam();
        String awayTeam = match.getAwayTeam();

        ResultStatistics r = match.getResultStatistics();
        // hmm. think of a cleaner way of doing this
        getPosition(homeTeam).homeMatch(r.getHomeGoals(), r.getAwayGoals(),r.getHomeXG(), r.getAwayXG());
        getPosition(awayTeam).awayMatch(r.getAwayGoals(), r.getHomeGoals(),r.getAwayXG(), r.getHomeXG());
    }

    private FiveThirtyEightLeaguePosition getPosition(String team) {
        Optional<FiveThirtyEightLeaguePosition> oFound = positions.stream().filter(t -> t.getTeam().equals(team)).findFirst();
        return oFound.orElseThrow(() -> new IllegalStateException(String.format("Team %s was not found in list of teams", team)));
    }

    private List<FiveThirtyEightLeaguePosition> getTable() {
        return new ArrayList<>(positions);
    }
}
