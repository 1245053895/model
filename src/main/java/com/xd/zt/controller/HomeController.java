package com.xd.zt.controller;

import com.xd.zt.domain.model.Programme;
import com.xd.zt.service.model.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class HomeController {
   @Autowired
   private ModelService modelService;
    @RequestMapping("/home")
    public String home(@RequestParam("saveName") String saveName,@RequestParam("savequestion") String savequestion,@RequestParam("saveuser") String saveuser){
        Programme programme = new Programme();
        String editTime =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString();
        programme.setProgrammetime(editTime);
        programme.setProgrammename(saveName);
        programme.setProgrammedescribe(savequestion);
        programme.setUsername(saveuser);
        modelService.insertProgram(programme);
        return "home";
    }
    @RequestMapping("/returnhome")
    public String returnhome(){
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

    @GetMapping("/wel")
    public String wel(){
        return "wel";
    }

}
