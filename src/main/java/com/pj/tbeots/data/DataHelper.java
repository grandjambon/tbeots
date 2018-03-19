package com.pj.tbeots.data;

import com.pj.tbeots.data.json.JsonLeaguePosition;
import com.pj.tbeots.data.model.Fixture;
import org.springframework.ui.ModelMap;

import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataHelper {
    private final BufferedDataManager dataManager;

    public DataHelper(BufferedDataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void refreshCache() throws IOException {
        dataManager.refreshCache();
    }

    public List<JsonLeaguePosition> getLeaguePositions(Predicate<JsonLeaguePosition> predicate) throws IOException {
        return dataManager.getLeaguePositions(predicate);
    }

    public Map<String, Fixture> getFixtures(String teamName) {
        return dataManager.getFixtures(teamName);
    }

    public void getFixturesAndDates(ModelMap model, List<JsonLeaguePosition> leaguePositions) {
        Set<String> dates = new TreeSet<>(new DateComparator());
        for (JsonLeaguePosition pos : leaguePositions) {
            Map<String, Fixture> fixtures = getFixtures(pos.getName());
            model.addAttribute("fixtures-"+pos.getName(), fixtures);
            dates.addAll(fixtures.keySet());
        }
        model.addAttribute("dates", dates);
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
