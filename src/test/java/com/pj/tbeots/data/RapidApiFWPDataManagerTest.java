//package com.pj.tbeots.data;
//
//import com.pj.tbeots.data.model.Fixture;
//import com.pj.tbeots.data.model.Team;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.util.Collection;
//import java.util.List;
//import java.util.Map;
//import java.util.TreeSet;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//public class RapidApiFWPDataManagerTest {
//
//    private static boolean isInited = false;
//
//    private RapidApiFWPDataManager dataManager;
//    private static Map<String, Team> teams;
//    private static Map<String, List<Fixture>> fixtures;
//
//    // BeforeAll because we have a limit to how many times we can invoke the API
//    @BeforeEach
//    public void setup() throws IOException {
//        if (!isInited) {
//            Collection<String> neutralRounds = getNeutralRounds();
//            dataManager = new RapidApiFWPDataManager(neutralRounds);
//            teams = dataManager.getTeams();
//            fixtures = dataManager.getFixtures();
//            isInited = true;
//        }
//    }
//
//    @Test
//    public void testTeams() throws IOException {
//        assertThat(teams).hasSize(20);
//        assertThat(teams).allSatisfy((s, team) -> assertThat(team.getName()).isNotNull());
//        assertThat(teams).allSatisfy((s, team) -> assertThat(team.getName()).isEqualTo(s));
//    }
//
//    @Test
//    public void testFixturesPerTeam() throws IOException {
////        RapidApiFWPDataManager dataManager = new RapidApiFWPDataManager(new ArrayList<>());
////        List<JsonFixture> fixtures = dataManager.getFixtures(30);
//    }
//
//    @Test
//    public void testFixtures() throws IOException {
//        assertThat(fixtures).isNotNull();
//        assertThat(fixtures).isNotEmpty();
//        assertThat(fixtures).containsKey("2021-04-24");
//    }
//
//    private static Collection<String>  getNeutralRounds() {
//        Collection<String> neutralRounds = new TreeSet<>();
//        neutralRounds.add("Carabao Cup"+"-"+"Final");
//        neutralRounds.add("Emirates FA Cup"+"-"+"Semi Final");
//        neutralRounds.add("Emirates FA Cup"+"-"+"Final");
//        neutralRounds.add("UEFA Champions League"+"-"+"Final");
//        neutralRounds.add("Europa League"+"-"+"Final");
//        return neutralRounds;
//    }
//}