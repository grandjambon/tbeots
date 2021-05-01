package com.pj.tbeots.data;

import com.pj.tbeots.data.fivethirtyeight.FiveThirtyEightLeagueTable;
import com.pj.tbeots.data.fivethirtyeight.FiveThirtyEightLeagueTableTest;
import com.pj.tbeots.data.remote.FiveThirtyEightDataConnector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FiveThirtyEightDataManagerTest {

    @Mock
    private FiveThirtyEightDataConnector mockConnector;

    @BeforeEach
    public void setup() throws IOException {
        when(mockConnector.getInputStream()).thenReturn(getInputStream());
    }

    @Test
    public void test() throws IOException {
        FiveThirtyEightDataManager manager = new FiveThirtyEightDataManager(mockConnector);
        manager.refreshCache(LocalDateTime.now());
        FiveThirtyEightLeagueTable leagueTable = manager.getLeagueTable();
        assertNotNull(leagueTable);

    }

    private InputStream getInputStream() {
        return FiveThirtyEightLeagueTableTest.class.getResourceAsStream("/fivethirtyeight/spi_matches_latest.csv");
    }

}