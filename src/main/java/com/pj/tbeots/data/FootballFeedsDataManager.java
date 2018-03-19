package com.pj.tbeots.data;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pj.tbeots.data.json.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FootballFeedsDataManager {

    private final FootballFeeds footballFeeds;

    public FootballFeedsDataManager(FootballFeeds footballFeeds) {
        this.footballFeeds = footballFeeds;
    }

    public Map<String, JsonTeam> getTeams() throws IOException {
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        JsonTeams jsonTeams = om.readerFor(JsonTeams.class).readValue(footballFeeds.getTeamsReader());

        Map<String, JsonTeam> map = new TreeMap<>();
        jsonTeams.getTeams().forEach(team -> map.put(team.getName(), team));

        return map;
    }

    public List<JsonLeaguePosition> getLeaguePositions() throws IOException {
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        JsonLeagueTable jsonTeams = om.readerFor(JsonLeagueTable.class).readValue(footballFeeds.getLeagueTableReader());

        List<JsonLeaguePosition> leaguePositions = jsonTeams.getLeaguePositions();

        int leaderPoints = leaguePositions.stream().mapToInt(JsonLeaguePosition::getPoints).max().getAsInt();

        leaguePositions.forEach(pos -> {
            if (pos.getMaxPoints() < leaderPoints) {
                pos.setCanWinLeague("NO");
            } else {
                pos.setCanWinLeague("YES");
            }
        });

        return leaguePositions;
    }

    public List<JsonMultiCompFixture> getTeamFixtures(int teamId) throws IOException {
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        JsonMultiCompFixtureList jsonMultiCompFixtureList = om.readerFor(JsonMultiCompFixtureList.class).readValue(footballFeeds.getFixtureListReader(teamId));

        return jsonMultiCompFixtureList.getFixtures();
    }

    /**
     * Premier League = competition=1
     */
    public List<JsonFixture> getAllFixtures(int competition) throws IOException {
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        JsonFixtureList jsonMultiCompFixtureList = om.readerFor(JsonFixtureList.class).readValue(footballFeeds.getAllFixturesReader(competition));

        return jsonMultiCompFixtureList.getFixtures();
    }

//    public Map<String, List<Fixture>> getFixtures() throws IOException {
//        List<JsonLeaguePosition> leagueTable = getLeaguePositions();
//        Map<String, JsonTeam> teamToId = getTeams();
//
//        Map<String, Map<String, Fixture>> teamNameToFixtureMap = new TreeMap<>();
//
//        Collection<String> validDates = new TreeSet<>();
//
//        for (JsonLeaguePosition position : leagueTable) {
//            int id = teamToId.get(position.getName()).getId();
//            List<JsonMultiCompFixture> jsonMultiCompFixtures = getTeamFixtures(id);
//            Map<String, Fixture> fixtures = new HashMap<>();
//
//            for (JsonMultiCompFixture jsonMultiCompFixture : jsonMultiCompFixtures) {
//                boolean homeFixture = jsonMultiCompFixture.getHomeTeamId() == id;
//
//                String opponent = homeFixture ? jsonMultiCompFixture.getAwayTeam() : jsonMultiCompFixture.getHomeTeam();
//
//                Fixture.HomeOrAway homeOrAway = homeFixture ? Fixture.HomeOrAway.home : Fixture.HomeOrAway.away;
//                String dateString = jsonMultiCompFixture.getDate();
//
//                Fixture fixture = new Fixture(dateString, jsonMultiCompFixture.getTime(), opponent, jsonMultiCompFixture.getCompetition(), homeOrAway);
//
//                fixtures.put(dateString, fixture);
//                validDates.add(dateString);
//            }
//
//            teamNameToFixtureMap.put(position.getName(), fixtures);
//        }
//
//        Map<String, List<Fixture>> dateToFixtures = new TreeMap<>(new DateComparator());
//
//        for (String date : validDates) {
//            List<Fixture> matches = new ArrayList<>();
//            for (JsonLeaguePosition position : leagueTable) {
//                Map<String, Fixture> dateToFixturesMap = teamNameToFixtureMap.get(position.getName());
//                Fixture fixture = dateToFixturesMap.get(date);
//                matches.add(fixture);
//            }
//            dateToFixtures.put(date, matches);
//        }
//        return dateToFixtures;
//    }

}
