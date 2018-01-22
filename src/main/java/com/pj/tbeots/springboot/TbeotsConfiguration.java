package com.pj.tbeots.springboot;

import com.pj.tbeots.data.DataManager;
import com.pj.tbeots.data.external.LiveFootballFeeds;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class TbeotsConfiguration {
    @Bean
    @Scope("singleton")
    public DataManager getDataManager() {
        return new DataManager(new LiveFootballFeeds());
    }

}
