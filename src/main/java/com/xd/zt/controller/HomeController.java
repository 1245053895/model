package com.xd.zt.controller;

import com.xd.zt.domain.analyse.Algorithm;
import com.xd.zt.service.algorithm.AlgorithmDebugService;
import com.xd.zt.service.business.BusinessModelService;
import com.xd.zt.service.model.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private AlgorithmDebugService algorithmDebugService;
   @Autowired
   private ModelService modelService;
    @Autowired
    private BusinessModelService businessModelService;
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
        return "ZT1";
    }
    @RequestMapping("/returnhome")
    public String returnhome(){
        return "home";
    }
   /* @GetMapping("/ZT")
    public String zt(){
        return "/zthtml/pages/ZT";
    }*/



   /* @RequestMapping("/Datacages_Homeindex")
    public String Datacages_Homeindex(){
        return "/zthtml/pages/Datacages_Homeindex";
    }*/


    @GetMapping("/newProgramme")
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






    @RequestMapping("/ZT")
    public ModelAndView algorithmList(Model model){
        String algorithmlabel="行业通用";
        List<Algorithm> algorithmListGeneral = algorithmDebugService.selectAlgorithmCommon(algorithmlabel);


        String algorithmlabel1="行业专用";
        List<Algorithm> algorithmListSpecial = algorithmDebugService.selectAlgorithmProcess(algorithmlabel1);

        String algorithmlabe2="人工智能";
        List<Algorithm> algorithmListIntelligence = algorithmDebugService.selectAlgorithmLogical(algorithmlabe2);


        List<Algorithm> algorithmListAll= algorithmDebugService.selectAlgorithm();


        model.addAttribute("algorithmListGeneral",algorithmListGeneral);
        model.addAttribute("algorithmListSpecial",algorithmListSpecial);
        model.addAttribute("algorithmListIntelligence",algorithmListIntelligence);
        model.addAttribute("algorithmListAll",algorithmListAll);
        model.addAttribute("businessModels", businessModelService.selectbusinessmodel());

        return new ModelAndView("zthtml/pages/ZT","Modelmodel",model);
    }




}
