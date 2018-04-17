package com.pj.tbeots.springboot;

import com.pj.tbeots.data.BufferedDataManager;
import com.pj.tbeots.data.DataHelper;
import com.pj.tbeots.data.FootballFeedsDataManager;
import com.pj.tbeots.data.external.LiveFootballFeeds;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@SuppressWarnings("unused")
@Configuration
public class TbeotsConfiguration {
    @Bean
    @Scope("singleton")
    public BufferedDataManager getDataManager() {
        return new BufferedDataManager(new FootballFeedsDataManager(new LiveFootballFeeds()));
    }

    // this is going to become the interface for getting the data for each controller
    @Bean
    @Scope
    public DataHelper getDataHelper() {
        return new DataHelper(getDataManager());
    }

}
