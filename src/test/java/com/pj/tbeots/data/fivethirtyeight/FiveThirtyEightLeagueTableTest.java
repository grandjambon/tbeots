package com.pj.tbeots.data.fivethirtyeight;

import com.pj.tbeots.data.RapidApiFWPDataManagerTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class FiveThirtyEightLeagueTableTest {

    @Test
    public void test() {
    }

    private Reader getMatchesReader() {
        InputStream stream = FiveThirtyEightLeagueTableTest.class.getResourceAsStream("/fivethirtyeight/spi_matches_latest.csv");
        return new InputStreamReader(stream);
    }

}