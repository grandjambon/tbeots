package com.pj.tbeots.springboot;

import com.pj.tbeots.data.DataManager;
import com.pj.tbeots.data.FootballFeedsDataManager;
import com.pj.tbeots.data.json.JsonLeaguePosition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

import static com.pj.tbeots.data.BufferedDataManager.getLiveToken;

@SuppressWarnings("unused")
@Controller
public class TbeotsController {

    private static final Logger logger = LoggerFactory.getLogger(TbeotsController.class);

    @Autowired
    private DataManager dataManager;

    @RequestMapping("/")
    public String home(ModelMap model) throws IOException {
        String cacheToken = getLiveToken();

        logger.info("requesting with cacheToken = {} ", cacheToken);

        dataManager.refreshCache(cacheToken);
        List<JsonLeaguePosition> leaguePositions = dataManager.getLeaguePositions();
        model.addAttribute("leaguePositions", leaguePositions);
        model.addAttribute("fixtures", dataManager.getFixtures());
        return "home";
    }

}
