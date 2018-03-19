package com.pj.tbeots.springboot;

import com.pj.tbeots.data.DataHelper;
import com.pj.tbeots.data.json.JsonLeaguePosition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;


@SuppressWarnings("unused")
@Controller
public class TbeotsController {

    private static final Logger logger = LoggerFactory.getLogger(TbeotsController.class);

    @Autowired
    private DataHelper dataHelper;

    @RequestMapping("/")
    public String home(ModelMap model) throws IOException {
        List<JsonLeaguePosition> leaguePositions = dataHelper.getLeaguePositions(position -> position.getCanWinLeague().equals("YES"));
        model.addAttribute("leaguePositions", leaguePositions);
        // ugh - fix this somehow

        dataHelper.getFixturesAndDates(model, leaguePositions);

        return "home";
    }

}
