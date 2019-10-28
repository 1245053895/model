package com.xd.zt.controller;

import com.xd.zt.service.model.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class HomeController {
   @Autowired
   private ModelService modelService;
    @RequestMapping("/home")
    public String home(@RequestParam("programmename") String programmename,@RequestParam("programmetype") String programmetype,@RequestParam("programmedescribe") String programmedescribe){
//       Programme programme = new Programme();
        String programmetime =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString();
//        programme.setProgrammetime(editTime);
//        programme.setProgrammename(saveName);
//        programme.setProgrammedescribe(savequestion);
//        programme.setUsername(saveuser);
//        programmeRepository.save(programme);
        String username="西电";
        modelService.insertProgram(programmename,programmetype,programmedescribe,programmetime,username);
        return "ZT";
    }
    @RequestMapping("/returnhome")
    public String returnhome(){
        return "home";
    }
    @GetMapping("/model")
    public String zt(){
        return "ZT";
    }
    @GetMapping("/ZT")
    public String newProgramme(){
        return "newProgramme";
    }

    @GetMapping("/wel")
    public String wel(){
        return "wel";
    }

    @GetMapping("/wel1")
    public String wel1(){
        return "wel1";
    }

}
