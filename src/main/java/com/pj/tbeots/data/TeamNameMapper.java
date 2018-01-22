package com.pj.tbeots.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TeamNameMapper {

    private static Properties props = new Properties();

    static {
        InputStream input = TeamNameMapper.class.getResourceAsStream("/pl-team-names.properties");
        try {
            props.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String mapTeamName(String fullName) {
        String name = props.getProperty(fullName);
        return name == null ? fullName : name;
    }
}
