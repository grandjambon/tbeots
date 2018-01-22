package com.pj.tbeots.data.model;

import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;

public class Contenders {
    private Collection<LeaguePosition> positions = new TreeSet<>(Comparator.comparing(LeaguePosition::getPosition));

    public void addTeam(Team team, int position, int played, int points, String form) {
        positions.add(new LeaguePosition(position, points, played, team, form));
    }
}
