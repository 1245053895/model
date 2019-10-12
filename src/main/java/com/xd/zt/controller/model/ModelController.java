package com.xd.zt.controller.model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping("/model")
@Controller
public class ModelController {
    @RequestMapping("/index")
    public String index(){
        return "model/index";
    }
    @RequestMapping("/modelList")
    public String welcome(){
        return "model/modelList";
    }
}
