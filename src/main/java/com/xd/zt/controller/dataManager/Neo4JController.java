package com.xd.zt.controller.dataManager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/dataManager")
public class Neo4JController {


    @RequestMapping("/Neo4JRun")
    public ModelAndView Neo4JRun(Model model){

        return new ModelAndView("dataManager/TzgsNeo4j","Modelmodel",model);
    }
}
