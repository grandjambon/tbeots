package com.pj.tbeots.data.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FixtureDateTest {
    @Test
    public void testPresentationDate() {
        FixtureDate f = new FixtureDate("2021-03-06");
        assertThat(f.getPresentationDate()).isEqualTo("Sat 06 Mar");
    }
}