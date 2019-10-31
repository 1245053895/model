package com.xd.zt.controller.experiment;

import com.alibaba.fastjson.JSONArray;
import com.xd.zt.domain.analyse.Algorithm;
import com.xd.zt.domain.analyse.AlgorithmData;
import com.xd.zt.domain.analyse.AnalyseModelProcess;
import com.xd.zt.domain.business.BusinessQuestion;
import com.xd.zt.domain.experiment.ExperimentModel;
import com.xd.zt.domain.experiment.ExperimentTraintest;
import com.xd.zt.service.experiment.ExperimentConfigService;
import com.xd.zt.service.experiment.ExperimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.Console;
import java.util.*;

@Controller
@RequestMapping("/experiment")
public class ExperimentConfigController {

    @Autowired
    private ExperimentConfigService experimentConfigService;

    @RequestMapping("/modelConfiguration/{id}")
    public ModelAndView modelConfiguration(Model model,@PathVariable("id") Integer id){
//        通過id去查analysemodelid
          ExperimentModel experimentModel =experimentConfigService.showExperiment(id);
          int analysemodeid = experimentModel.getAnalysemodeid();
//         通過analysemodelid去analyse_model_process查整條記錄
          AnalyseModelProcess analyseModelProcess =experimentConfigService.showAnalyseModelProcess(analysemodeid);
          String name =analyseModelProcess.getModelname();
          Integer modelid = analyseModelProcess.getModelid();
           model.addAttribute("name",name);
           model.addAttribute("modelid",modelid);
        return new ModelAndView("experiment/modelConfiguration","Modelmodel",model) ;
    }

    @ResponseBody
    @RequestMapping("/selectQuestion")
    public Map<String, Object> selectQuestion(@RequestParam("modelid") Integer modelid){
        AnalyseModelProcess analyseModelProcess = experimentConfigService.showAnalyseModelProcessx(modelid);
        String modelProcess = analyseModelProcess.getModelprocess();
        String[] aa = modelProcess.split(";");
        String first = aa[0];
        String trainname =  aa[1];
        String last = aa[2];
        String algorithmname = aa[1];
        Algorithm algorithm = experimentConfigService.showAlgorithm(algorithmname);
        String algorithmparams =  algorithm.getAlgorithmparams();

        ExperimentTraintest experimentTraintest = experimentConfigService.showExperimentTaintest(trainname);
        String testname = experimentTraintest.getTestname();
        String newModelProcess = first+";"+testname+";"+last;
        System.out.println(newModelProcess);
        Map<String,Object> map = new HashMap<>();
        map.put("newModelProcess",newModelProcess);
        map.put("algorithmparams",algorithmparams);
        return map;
    }

}