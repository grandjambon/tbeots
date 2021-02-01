package com.pj.tbeots.springboot;

import com.pj.tbeots.data.BufferedDataManager;
import com.pj.tbeots.data.DataManager;
import com.pj.tbeots.data.RapidApiFWPDataManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.TreeSet;

@SuppressWarnings("unused")
@Configuration
public class TbeotsConfiguration {

    @Bean
    public DataManager getDataFWPDataManager(Collection<String> neutralRounds) {
        return new BufferedDataManager(new RapidApiFWPDataManager(neutralRounds));
    }

    @Bean
    public Collection<String> neutralRounds() {
        Collection<String> neutralRounds = new TreeSet<>();
        neutralRounds.add("Carabao Cup"+"-"+"Final");
        neutralRounds.add("Emirates FA Cup"+"-"+"Semi Final");
        neutralRounds.add("Emirates FA Cup"+"-"+"Final");
        neutralRounds.add("UEFA Champions League"+"-"+"Final");
        neutralRounds.add("Europa League"+"-"+"Final");
        return neutralRounds;
    }
}
