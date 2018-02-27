package com.pj.tbeots.data;

import com.pj.tbeots.data.json.JsonLeaguePosition;
import com.pj.tbeots.data.json.JsonTeam;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface DataManager {

    void refreshCache(String cacheToken) throws IOException;

    Map<String, JsonTeam> getTeams() throws IOException;

    List<JsonLeaguePosition> getLeaguePositions() throws IOException;

    Map<String, List<String>> getFixtures() throws IOException;
}
