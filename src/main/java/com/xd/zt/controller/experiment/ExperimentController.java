package com.xd.zt.controller.experiment;


import com.xd.zt.domain.analyse.AnalyseModel;
import com.xd.zt.domain.experiment.ExperimentModel;
import com.xd.zt.service.experiment.ExperimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/experiment")
public class ExperimentController {
    @Autowired
    private ExperimentService experimentService;


    /*  @RequestMapping("/index")
      public ModelAndView experimentindex(Model model) {
          return new ModelAndView("experiment/index", "modelModel", model);
      }*/
    @RequestMapping("/firstpage")
    public ModelAndView experimentfirstpage(Model model) {
        return new ModelAndView("experiment/firstpage", "modelModel", model);
    }


    @RequestMapping("/time")
    public ModelAndView time(Model model) {
        return new ModelAndView("experiment/time", "modelModel", model);
    }


    /*@RequestMapping("/modelConfiguration")
    public ModelAndView modelConfiguration(Model model) {
        return new ModelAndView("experiment/modelConfiguration", "modelModel", model);
    }*/


/*

    @RequestMapping("/welcome")
    public ModelAndView welcome(Model model) {
        return new ModelAndView("experiment/welcome", "modelModel", model);
    }
*/


    @RequestMapping("/welcome")
    public ModelAndView selectAnalyse(Model model) {
        model.addAttribute("analyseModelList", experimentService.selectAnalyse());
        return new ModelAndView("experiment/welcome", "modelModel", model);
    }

    @RequestMapping("/index")
    public ModelAndView selectAnalyse1(Model model) {
        model.addAttribute("analyseModelList", experimentService.selectAnalyse());
        model.addAttribute("TestnameModelList", experimentService.selectTestname());
        return new ModelAndView("experiment/index", "modelModel", model);
    }

    @RequestMapping("/analyseListshow")
    @ResponseBody
    public ExperimentModel experimentModel(@RequestBody ExperimentModel experimentModel) {
//        int questionid = experimentModel.getAnalysemodeid();
//        String name = experimentModel.getTestname();
//        String questioname=  experimentService.selectQuestionName(questionid);
        Integer analysemodeid = experimentModel.getAnalysemodeid();
        String testname = experimentModel.getTestname();
        experimentService.insertExperimentModel(testname, analysemodeid);

        return experimentModel;
    }


    @RequestMapping("/experimentManage")
    public ModelAndView experimentManage(Model model) {
        List<ExperimentModel> experimentModelList = experimentService.selectExperimentModelList();
        model.addAttribute("experimentModelList", experimentModelList);
        return new ModelAndView("experiment/experimentManage", "modelModel", model);
    }


    @ResponseBody
    @RequestMapping("/experimentDelete")
    public String experimentDelete(@RequestParam("modelid")  String modelid){
        Map<String,Object> map=new HashMap<>();
        Integer id= Integer.valueOf(modelid);  //将string类型转为Integer类型
        experimentService.deleteExperiment(id);
        return modelid;
    }
}