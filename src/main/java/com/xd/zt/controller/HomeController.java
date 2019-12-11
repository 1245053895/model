package com.xd.zt.controller;

import com.xd.zt.domain.analyse.Algorithm;
import com.xd.zt.domain.dataManage.UploadData;
import com.xd.zt.domain.model.Programme;
import com.xd.zt.service.algorithm.AlgorithmDebugService;
import com.xd.zt.service.business.BusinessModelService;
import com.xd.zt.service.dataManager.OpenTsdbDataService;
import com.xd.zt.service.model.ModelService;
import com.xd.zt.util.dataManager.GetOpenTsdb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/**
 * @Author 张立军
 * @Version 1.0
 * @Project 智能分析平台
 * Date on 2019/12/6  14:17
 */
@Controller
public class HomeController {
    @Autowired
    private OpenTsdbDataService openTsdbDataService;

    @Autowired
    private AlgorithmDebugService algorithmDebugService;
   @Autowired
   private ModelService modelService;
    @Autowired
    private BusinessModelService businessModelService;
    @Autowired
    private SessionRepository sessionRepository;
//    @RequestMapping("/home")
//    public String home(@RequestParam("programmename") String programmename,@RequestParam("programmetype") String programmetype,@RequestParam("programmedescribe") String programmedescribe){
////       Programme programme = new Programme();
//        String programmetime =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString();
////        programme.setProgrammetime(editTime);
////        programme.setProgrammename(saveName);
////        programme.setProgrammedescribe(savequestion);
////        programme.setUsername(saveuser);
////        programmeRepository.save(programme);
//        String username="西电";
//        modelService.insertProgram(programmename,programmetype,programmedescribe,programmetime,username);
//        return "ZT1";
//    }
    @RequestMapping("/returnhome")
    public ModelAndView returnhome(Model model,@RequestParam(value = "sessionId")String sessionId){
        Session session = sessionRepository.findById(sessionId);
        System.out.printf("\n\n数据集成的sessionId:"+sessionId);
        try{
            String SessionId = session.getAttribute("SessionId");
            System.out.printf("\n\n检查sessionId："+SessionId);
            model.addAttribute("SessionId",SessionId);
            return new ModelAndView("home","Modelmodel",model);
        }
        catch (Exception e){
//            return "redirect:http://10.101.201.154:8080/#/home/index";
            System.out.printf("\n\n检查sessionId不存在");
            return new ModelAndView(new RedirectView("http://10.101.201.173:80/login"));
        }
    }

    @GetMapping("/newProgramme")
    public String newProgramme(){
        return "newProgramme";
    }

    @GetMapping("/wel")
    public String wel(){
        return "wel";
    }

/*    @GetMapping("/wel1")
    public String wel1(){
        return "wel1";
    }*/
//    @GetMapping("/wel1")
//    public String wel1(){
//        return "wel1";
//    }






    @RequestMapping("/ZT")
    public ModelAndView algorithmList(Model model){
        String algorithmlabel="行业通用";
        List<Algorithm> algorithmListGeneral = algorithmDebugService.selectAlgorithmCommon(algorithmlabel);
        String algorithmlabel1="行业专用";
        List<Algorithm> algorithmListSpecial = algorithmDebugService.selectAlgorithmProcess(algorithmlabel1);
        String algorithmlabe2="人工智能";
        List<Algorithm> algorithmListIntelligence = algorithmDebugService.selectAlgorithmLogical(algorithmlabe2);
        List<Algorithm> algorithmListAll= algorithmDebugService.selectAlgorithm();
        List<Programme> programmeList = modelService.selectAllModelByType("勘察设计");
        model.addAttribute("programmeList",programmeList);
        List<Programme> programmeList1= modelService.selectAllModelByType("工程施工");
        model.addAttribute("programmeList1",programmeList1);
        List<Programme> programmeList2 = modelService.selectAllModelByType("运营维护");
        model.addAttribute("programmeList2",programmeList2);
        List<Programme> programmeList3 = modelService.selectAllModelByType("智慧城市");
        model.addAttribute("programmeList3",programmeList3);
        model.addAttribute("algorithmListGeneral",algorithmListGeneral);
        model.addAttribute("algorithmListSpecial",algorithmListSpecial);
        model.addAttribute("algorithmListIntelligence",algorithmListIntelligence);
        model.addAttribute("algorithmListAll",algorithmListAll);
        model.addAttribute("businessModels", businessModelService.selectbusinessmodel());
        return new ModelAndView("zthtml/pages/ZT","Modelmodel",model);
    }




}
