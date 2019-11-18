package com.xd.zt.controller.echarts;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/echarts")
@Controller
public class TzgsEchartsController {

    @RequestMapping("/Tzgs")
    public String Tzgs(){
        return "echarts/Tzgs";
    }

}
