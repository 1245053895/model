package com.xd.zt.controller.dataManager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dataManager")
public class DataManager {
    @RequestMapping("/index")
    public String index(){

        return "dataManager/index";
    }
    @RequestMapping("/welcome")
    public String welcome(){
        return "dataManager/welcome";
    }

    @RequestMapping("/dataManager")
    public String dataManager(){
        return "dataManager/dataManager";
    }
    @RequestMapping("/structure")
    public String structure(){
        return "dataManager/structure";
    }

    @RequestMapping("/unstructure")
    public String unstructure(){
        return "dataManager/unstructure";
    }

}
