package com.pj.tbeots.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeamNameMapperTest {

    @Test
    public void testNameMapper() {
        assertEquals("City", TeamNameMapper.mapTeamName("Manchester City"));
        assertEquals("Borussia M", TeamNameMapper.mapTeamName("Borussia Mönchengladbach"));
        assertEquals("Leeds", TeamNameMapper.mapTeamName("Leeds United"));
    }
}