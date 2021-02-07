package com.pj.tbeots.data;

import com.pj.tbeots.data.model.Fixture;
import com.pj.tbeots.data.model.LeaguePosition;
import com.pj.tbeots.data.model.Team;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface DataManager {

    void refreshCache(LocalDateTime now) throws IOException;

    Map<String, Team> getTeams() throws IOException;

    List<LeaguePosition> getLeaguePositions() throws IOException;

    Map<String, List<Fixture>> getFixtures() throws IOException;
}
