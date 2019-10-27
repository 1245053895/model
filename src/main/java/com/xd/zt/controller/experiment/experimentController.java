package com.xd.zt.controller.experiment;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/experiment")
public class experimentController {
    @RequestMapping("/index")
    public ModelAndView experimentindex(Model model) {
        return new ModelAndView("experiment/index", "modelModel", model);
    }
    @RequestMapping("/firstpage")
    public ModelAndView experimentfirstpage(Model model) {
        return new ModelAndView("experiment/firstpage", "modelModel", model);
    }
}
