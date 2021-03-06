package com.pj.tbeots.data.model;

import org.jetbrains.annotations.NotNull;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class FixtureDate implements Comparable<FixtureDate> {

    private static final DateTimeFormatter inputPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter outputPattern = DateTimeFormatter.ofPattern("EEE dd MMM");

    // yyyy-MM-dd
    private final String dateString;
    // EEE dd/MM
    private final String presentationDate;

    public FixtureDate(String dateString) {
        this.dateString = dateString;
        TemporalAccessor temporalAccessor = inputPattern.parse(dateString);
        this.presentationDate = outputPattern.format(temporalAccessor);
    }

    public String getPresentationDate() {
        return presentationDate;
    }

    public String getDateString() {
        return dateString;
    }

    @Override
    public int compareTo(@NotNull FixtureDate fixtureDate) {
        return this.dateString.compareTo(fixtureDate.dateString);
    }
}
