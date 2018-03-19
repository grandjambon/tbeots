package com.pj.tbeots.data.json;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// common methods for within Jackson classes
public class JsonUtils {

    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("EEE dd/MM");

    public static String formatDate(String inputDate) {
        Pattern pattern = Pattern.compile("[A-Za-z]+ ([0-9]+)[a-z]{2} ([A-Za-z]+) ([0-9]+)");
        Matcher matcher = pattern.matcher(inputDate);
        matcher.find();
        String dayOfMonth = matcher.group(1);
        String monthAsWord = matcher.group(2);
        String year = matcher.group(3);

        String fullDate = dayOfMonth + " " + monthAsWord + " " + year;

        LocalDate date = LocalDate.parse(fullDate, inputFormatter);
        return outputFormatter.format(date);
    }
}
