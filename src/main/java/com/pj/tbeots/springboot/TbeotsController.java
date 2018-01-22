package com.pj.tbeots.springboot;

import com.pj.tbeots.data.DataManager;
import com.pj.tbeots.data.json.JsonLeaguePosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@SuppressWarnings("unused")
@Controller
public class TbeotsController {

    @Autowired
    private DataManager dataManager;

    @RequestMapping("/")
    public String home(ModelMap model) throws IOException {
        List<JsonLeaguePosition> leaguePositions = dataManager.getLeagueTable();
        model.addAttribute("leaguePositions", leaguePositions);
        model.addAttribute("fixtures", dataManager.getFixtures());
        return "home";
    }

}
