package com.xd.zt.controller.experiment;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/experiment")
public class ExperimentController {
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

    @RequestMapping("/time")
    public ModelAndView time(Model model) {
        return new ModelAndView("experiment/time", "modelModel", model);
    }
}
