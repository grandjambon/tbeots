package com.pj.tbeots.data;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pj.tbeots.data.json.*;
import com.pj.tbeots.data.model.Fixture;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FootballFeedsDataManager implements DataManager {

    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("EEE dd/MM");

    private final FootballFeeds footballFeeds;

    public FootballFeedsDataManager(FootballFeeds footballFeeds) {
        this.footballFeeds = footballFeeds;
    }

    @Override
    public void refreshCache(String cacheToken) throws IOException {
        // we do no caching here so no problems
    }

    @Override
    public Map<String, JsonTeam> getTeams() throws IOException {
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        JsonTeams jsonTeams = om.readerFor(JsonTeams.class).readValue(footballFeeds.getTeamsReader());

        Map<String, JsonTeam> map = new TreeMap<>();
        jsonTeams.getTeams().forEach(team -> map.put(team.getName(), team));

        return map;
    }

    @Override
    public List<JsonLeaguePosition> getLeaguePositions() throws IOException {
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        JsonLeagueTable jsonTeams = om.readerFor(JsonLeagueTable.class).readValue(footballFeeds.getTop6TeamsReader());

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

    public List<JsonFixture> getFixtures(int teamId) throws IOException {
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        JsonFixtureList jsonFixtureList = om.readerFor(JsonFixtureList.class).readValue(footballFeeds.getFixtureListReader(teamId));

        return jsonFixtureList.getFixtures();
    }

    @Override
    public Map<String, List<Fixture>> getFixtures() throws IOException {
        List<JsonLeaguePosition> leagueTable = getLeaguePositions();
        Map<String, JsonTeam> teamToId = getTeams();

        Map<String, Map<String, Fixture>> teamNameToFixtureMap = new TreeMap<>();

        Collection<String> validDates = new TreeSet<>();

        for (JsonLeaguePosition position : leagueTable) {
            int id = teamToId.get(position.getName()).getId();
            List<JsonFixture> jsonFixtures = getFixtures(id);
            Map<String, Fixture> fixtures = new HashMap<>();

            for (JsonFixture jsonFixture : jsonFixtures) {
                boolean homeFixture = jsonFixture.getHomeTeamId() == id;

                String opponent = homeFixture ? jsonFixture.getAwayTeam() : jsonFixture.getHomeTeam();

                Fixture.HomeOrAway homeOrAway = homeFixture ? Fixture.HomeOrAway.home : Fixture.HomeOrAway.away;
                String dateString = convertDateToCorrectFormat(jsonFixture);


                Fixture fixture = new Fixture(dateString, jsonFixture.getTime(), opponent, jsonFixture.getCompetition(), homeOrAway);

                fixtures.put(dateString, fixture);
                validDates.add(dateString);
            }

            teamNameToFixtureMap.put(position.getName(), fixtures);
        }

        Map<String, List<Fixture>> dateToFixtures = new TreeMap<>(new DateComparator());

        for (String date : validDates) {
            List<Fixture> matches = new ArrayList<>();
            for (JsonLeaguePosition position : leagueTable) {
                Map<String, Fixture> dateToFixturesMap = teamNameToFixtureMap.get(position.getName());
                Fixture fixture = dateToFixturesMap.get(date);
                matches.add(fixture);
            }
            dateToFixtures.put(date, matches);
        }
        return dateToFixtures;
    }

    private String convertDateToCorrectFormat(JsonFixture jsonFixture) {
        // Tuesday 30th January 2018
        String d = jsonFixture.getDate();
        Pattern pattern = Pattern.compile("[A-Za-z]+ ([0-9]+)[a-z]{2} ([A-Za-z]+) ([0-9]+)");
        Matcher matcher = pattern.matcher(d);
        matcher.find();
        String dayOfMonth = matcher.group(1);
        String monthAsWord = matcher.group(2);
        String year = matcher.group(3);

        String fullDate = dayOfMonth + " " + monthAsWord + " " + year;

        LocalDate date = LocalDate.parse(fullDate, inputFormatter);
        return outputFormatter.format(date);
    }

    private static class DateComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            Pattern pattern = Pattern.compile("[A-Za-z]+ ([0-9]+)/([0-9]+)");
            Matcher matcher1 = pattern.matcher(o1);
            matcher1.find();
            String day1 = matcher1.group(1);
            String month1 = matcher1.group(2);


            Matcher matcher2 = pattern.matcher(o2);
            matcher2.find();
            String day2 = matcher2.group(1);
            String month2 = matcher2.group(2);

            if (month1.equals(month2))
                return day1.compareTo(day2);

            return month1.compareTo(month2);
        }
    }
}
