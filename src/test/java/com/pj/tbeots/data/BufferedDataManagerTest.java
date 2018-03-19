package com.pj.tbeots.data;

import com.pj.tbeots.data.model.Fixture;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BufferedDataManagerTest {

    private BufferedDataManager bufferedDataManager;

    @Before
    public void setup() throws IOException {
        bufferedDataManager = new BufferedDataManager(new FootballFeedsDataManager(new StaticFootballFeeds()));
        bufferedDataManager.refreshCache();
    }

    @Test
    public void testGetFixtures() throws IOException {
        Map<String, Fixture> cityFixtures = bufferedDataManager.getFixtures("City");
        assertNotNull(cityFixtures);
        assertEquals(7, cityFixtures.size());
        Map<String, Fixture> brightonFixtures = bufferedDataManager.getFixtures("Brighton");
        assertNotNull(brightonFixtures);
        assertEquals(7, brightonFixtures.size());
    }

}