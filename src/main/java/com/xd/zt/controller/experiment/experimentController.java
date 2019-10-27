package com.xd.zt.controller.experiment;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/experiment")
public class experimentController {
    @RequestMapping("/index")
    public ModelAndView selectAnalyz(Model model) {
        return new ModelAndView("experiment/index", "modelModel", model);
    }
}
