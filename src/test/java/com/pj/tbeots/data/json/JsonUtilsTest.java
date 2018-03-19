package com.pj.tbeots.data.json;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JsonUtilsTest {

    @Test
    public void testFormatDate() {
        String formattedDate = JsonUtils.formatDate("Tuesday 30th January 2018");
        assertEquals("Tue 30/01", formattedDate);
    }
}