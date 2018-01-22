package com.pj.tbeots.data;

import org.junit.Test;

import static org.junit.Assert.*;

public class TeamNameMapperTest {

    @Test
    public void testNameMapper() {
        assertEquals("City", TeamNameMapper.mapTeamName("Manchester City"));
    }

}