package com.xd.zt.controller.experiment;


import com.xd.zt.service.experiment.ExperimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@RequestMapping("/experiment")
public class ExperimentController {
    @Autowired
    private ExperimentService experimentService;


    @RequestMapping("/index")
    public ModelAndView experimentindex(Model model) {
        return new ModelAndView("experiment/index", "modelModel", model);
    }
    @RequestMapping("/firstpage")
    public ModelAndView experimentfirstpage(Model model) {
        return new ModelAndView("experiment/firstpage", "modelModel", model);
    }
    @RequestMapping("/datalead")
    public ModelAndView datalead(Model model) {
        return new ModelAndView("experiment/datalead", "modelModel", model);
    }


    /*@RequestMapping("/modelConfiguration")
    public ModelAndView modelConfiguration(Model model) {
        return new ModelAndView("experiment/modelConfiguration", "modelModel", model);
    }*/
    @RequestMapping("/modelConfiguration")
    public ModelAndView modelConfiguration(Model model){

        ArrayList modelConfiguration  = experimentService.modelConfiguration();

        model.addAttribute("modelConfiguration",modelConfiguration);

        return new ModelAndView("experiment/modelConfiguration","Modelmodel",model) ;
    }


}
