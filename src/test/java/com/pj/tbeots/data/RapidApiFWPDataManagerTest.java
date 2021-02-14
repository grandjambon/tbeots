package com.pj.tbeots.data;

import com.pj.tbeots.data.json.rapidapifwp.JsonFixtures;
import com.pj.tbeots.data.json.rapidapifwp.JsonMatch;
import com.pj.tbeots.data.model.Team;
import com.pj.tbeots.data.remote.RapidApiDataConnector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collection;
import java.util.Map;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RapidApiFWPDataManagerTest {

    @Mock private RapidApiDataConnector dataConnector;

    private RapidApiFWPDataManager dataManager;

    @BeforeEach
    public void setup() throws IOException {
        dataManager = new RapidApiFWPDataManager(dataConnector, getNeutralRounds());
        when(dataConnector.getLeagueTable()).thenReturn(getReader("leaguetable.json"));
        for (int i : new int[]{9, 11, 12, 13, 30, 42}) {
            lenient().when(dataConnector.getFixturesForTeam(i)).thenReturn(getReader("team-" + i + ".json"));
        }
    }

    @Test
    public void testTeams() throws IOException {
        Map<String, Team> teams = dataManager.getTeams();
        assertThat(teams).hasSize(20);
    }

    @Test
    public void testFixturesPerTeam() throws IOException {
        Map<String, Team> teams = dataManager.getTeams();
        Team city = teams.get("City");
        JsonFixtures fixtures = dataManager.getFixtures(city.getId());
        assertThat(fixtures.getTeamName()).isEqualTo("City");
        fixtures.getMatches().forEach(this::validateMatchAttributes);
    }

    private void validateMatchAttributes(JsonMatch match) {
        assertThat(match.getDateString()).isNotNull();
        assertThat(match.getVenue()).isNotNull();
        assertThat(match.getTime()).isNotNull();
    }

    @Test
    public void testHandlePostponed() throws IOException {
        // City -> Fixture 36
        // Status shouldn't be "LIVE"
        // We should ignore the "Postponed" flag
        Map<String, Team> teams = dataManager.getTeams();
        Team city = teams.get("City");
        JsonFixtures fixtures = dataManager.getFixtures(city.getId());
        JsonMatch jsonMatch = fixtures.getMatches().get(36);
        assertThat(jsonMatch.getDayOfWeek()).isEqualTo("Wed");
        assertThat(jsonMatch.getStatus()).isEqualTo(JsonMatch.Status.FIXTURE);
    }

    private static Collection<String>  getNeutralRounds() {
        Collection<String> neutralRounds = new TreeSet<>();
        neutralRounds.add("Carabao Cup"+"-"+"Final");
        neutralRounds.add("Emirates FA Cup"+"-"+"Semi Final");
        neutralRounds.add("Emirates FA Cup"+"-"+"Final");
        neutralRounds.add("UEFA Champions League"+"-"+"Final");
        neutralRounds.add("Europa League"+"-"+"Final");
        return neutralRounds;
    }

    private Reader getReader(String filename) {
        InputStream stream = RapidApiFWPDataManagerTest.class.getResourceAsStream("/rapidapi/" + filename);
        return new InputStreamReader(stream);
    }
}