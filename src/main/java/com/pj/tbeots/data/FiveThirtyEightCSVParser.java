package com.pj.tbeots.data;

import com.pj.tbeots.data.fivethirtyeight.FiveThirtyEightMatch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class FiveThirtyEightCSVParser {

    private final InputStream stream;

    public FiveThirtyEightCSVParser(InputStream stream) {
        this.stream = stream;
    }

    public List<FiveThirtyEightMatch> getMatches() throws IOException {
        try (InputStreamReader isr = new InputStreamReader(stream)) {
            try (BufferedReader reader = new BufferedReader(isr)) {
                return reader.lines().map(this::toMatch).collect(Collectors.toList());
            }
        }
    }

    private FiveThirtyEightMatch toMatch(String line) {
        return FiveThirtyEightLineParser.parseLine(line);
    }

}
