package com.pj.tbeots.data.model;

public class FixtureDate {
    private final String shortDate;
    private final String longDate;

    public FixtureDate(String shortDate, String longDate) {
        this.shortDate = shortDate;
        this.longDate = longDate;
    }

    public String getShortDate() {
        return shortDate;
    }

    public String getLongDate() {
        return longDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FixtureDate that = (FixtureDate) o;

        if (shortDate != null ? !shortDate.equals(that.shortDate) : that.shortDate != null) return false;
        return longDate != null ? longDate.equals(that.longDate) : that.longDate == null;

    }

    @Override
    public int hashCode() {
        int result = shortDate != null ? shortDate.hashCode() : 0;
        result = 31 * result + (longDate != null ? longDate.hashCode() : 0);
        return result;
    }
}
