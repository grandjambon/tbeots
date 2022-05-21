package com.pj.tbeots.data;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pj.tbeots.data.json.rapidapifwp.JsonFixtures;
import com.pj.tbeots.data.json.rapidapifwp.JsonLeagueTable;
import com.pj.tbeots.data.json.rapidapifwp.JsonMatch;
import com.pj.tbeots.data.json.rapidapifwp.JsonTeam;
import com.pj.tbeots.data.model.Fixture;
import com.pj.tbeots.data.model.FixtureDate;
import com.pj.tbeots.data.model.LeaguePosition;
import com.pj.tbeots.data.model.Team;
import com.pj.tbeots.data.remote.RapidApiDataConnector;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class RapidApiFWPDataManager implements DataManager {

    public static final int HOW_MANY_TEAMS = 6;

    private final Collection<String> neutralRounds;
    private final RapidApiDataConnector rapidApiDataConnector;

    public RapidApiFWPDataManager(RapidApiDataConnector rapidApiDataConnector, Collection<String> neutralRounds) {
        this.neutralRounds = neutralRounds;
        this.rapidApiDataConnector = rapidApiDataConnector;
    }

    public void refreshCache(LocalDateTime now) {
    }

    public Map<String, Team> getTeams() throws IOException {
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        JsonLeagueTable jsonLeagueTable = om.readerFor(JsonLeagueTable.class).readValue(rapidApiDataConnector.getLeagueTable());
        return jsonLeagueTable.getTeams().stream().map(this::jsonTeamToTeam).collect(Collectors.toMap(Team::getName, t->t));
    }

    private Team jsonTeamToTeam(JsonTeam j) {
        return new Team(j.getName(), j.getId(), j.getPosition(), j.getPoints(), j.getPlayed(), j.getGoalDifference());
    }


    public List<LeaguePosition> getLeaguePositions() throws IOException {
        List<LeaguePosition> leaguePositions = getTeams().values().stream().sorted(Comparator.comparingInt(Team::getPosition)).map(this::teamToLeaguePosition).collect(Collectors.toList());
        leaguePositions.stream().mapToInt(LeaguePosition::getPoints).max().ifPresent(
          highestPoints -> leaguePositions.forEach(p -> p.setCanWinLeague(p.getMaxPoints() >= highestPoints ? "YES" : "NO"))
        );

        return leaguePositions.stream().filter(RapidApiFWPDataManager::includeLeaguePositions).collect(Collectors.toList());
    }

    private static boolean includeLeaguePositions(LeaguePosition t) {
        // maybe change this to also be "maxPoints within 15 of leading maxPoints"
        return t.getPosition() <= HOW_MANY_TEAMS;
    }

    private LeaguePosition teamToLeaguePosition(Team team) {
        return new LeaguePosition(team.getPosition(), team.getPoints(), team.getPlayed(), team.getGoalDifference(), team);
    }

    public Map<FixtureDate, List<Fixture>> getFixtures() throws IOException {
        List<LeaguePosition> leagueTable = getLeaguePositions();
        Map<String, Map<String, Fixture>> teamNameToFixtureMap = new TreeMap<>();

        Collection<String> validDates = new TreeSet<>();

        for (LeaguePosition position : leagueTable) {
            int id = position.getTeam().getId();
            JsonFixtures jsonFixtures = getFixtures(id);
            Map<String, Fixture> fixtures = new HashMap<>();

            for (JsonMatch match: jsonFixtures.getMatches()) {
                boolean homeFixture = match.getHomeTeamId() == id;

                String opponent = homeFixture ? match.getAwayTeam() : match.getHomeTeam();

                Fixture.HomeOrAway homeOrAway = getHomeOrAwayOrNeutral(id, match);

                if (match.getStatus() == JsonMatch.Status.FIXTURE) {
                    Fixture fixture = new Fixture(match.getDateString(), match.getTime(), opponent, match.getCompetitionName(), homeOrAway);
                    if (fixtures.values().stream().noneMatch(f ->
                            f.getCompetition().equals(fixture.getCompetition()) &&
                            f.getOpponent().equals(fixture.getOpponent()) &&
                            f.getHomeOrAway().equals(fixture.getHomeOrAway()))) {
                        validDates.add(match.getDateString());
                        fixtures.put(match.getDateString(), fixture);
                    }
                }
            }

            teamNameToFixtureMap.put(position.getTeam().getName(), fixtures);
        }

        Map<FixtureDate, List<Fixture>> dateToFixtures = new TreeMap<>();

        for (String dateString : validDates) {
            List<Fixture> matches = new ArrayList<>();
            for (LeaguePosition position : leagueTable) {
                Map<String, Fixture> dateToFixturesMap = teamNameToFixtureMap.computeIfAbsent(position.getTeam().getName(), name -> new HashMap<>());
                Fixture fixture = dateToFixturesMap.get(dateString);
                matches.add(fixture);
            }
            dateToFixtures.put(new FixtureDate(dateString), matches);
        }
        return dateToFixtures;
    }

    private Fixture.HomeOrAway getHomeOrAwayOrNeutral(int id, JsonMatch match) {
        String competition = match.getCompetitionName();
        String round = match.getRound();

        if (neutralRounds.contains(competition+"-"+round)) {
            return Fixture.HomeOrAway.neutral;
        }

        return match.getHomeTeamId() == id ? Fixture.HomeOrAway.home : Fixture.HomeOrAway.away;
    }


    public JsonFixtures getFixtures(int teamId) throws IOException {
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        return om.readerFor(JsonFixtures.class).readValue(rapidApiDataConnector.getFixturesForTeam(teamId));
    }
}
