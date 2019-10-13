package com.xd.zt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/ZT")
    public String zt(){
        return "ZT";
    }
    @GetMapping("/newProgramme")
    public String newProgramme(){
        return "newProgramme";
    }
}
