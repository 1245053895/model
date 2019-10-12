package com.xd.zt.controller.algorithm;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.xd.zt.domain.analyse.Algorithm;
import com.xd.zt.service.algorithm.AlgorithmDebugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@RequestMapping("/algorithm")
@Controller
public class AlgorithmDebugController {
    @Autowired
    private AlgorithmDebugService algorithmDebugService;

    @RequestMapping("/index")
    public String index(){
        return "algorithm/index";
    }

    @RequestMapping("/algorithmList")
    public ModelAndView algorithmList(Model model){
        List<Algorithm> algorithmList = algorithmDebugService.selectAlgorithm();
        model.addAttribute("algorithmList",algorithmList);

        return new ModelAndView("algorithm/algorithmList","Modelmodel",model);
    }

    @GetMapping("/algorithmSearch")
    public ModelAndView dataSearch(Model model, String search_text){
        List<Algorithm> algorithmList = algorithmDebugService.searchAlgorithm(search_text);
        model.addAttribute("algorithmList",algorithmList);
        return new ModelAndView("algorithm/algorithmList","Modelmodel",model);
    }

    @RequestMapping("/algorithmDebug")
    public ModelAndView analyzModelExample(Model model){
        //查询所有算法
        List<Algorithm> algorithmList = algorithmDebugService.selectAlgorithm();
        model.addAttribute("algorithmList",algorithmList);

        String algorithmName = "";
        for (int i = 0;i < algorithmList.size(); i++){
            algorithmName = algorithmName + algorithmList.get(i).getAlgorithmname() +";";
        }
        model.addAttribute("algorithmName",algorithmName);
        return new ModelAndView("algorithm/algorithmDebug","Modelmodel",model);
    }

    @RequestMapping("/algorithmView/{id}")
    public ModelAndView algorithmView(Model model, @PathVariable("id") Integer algorithmid) throws IOException{
        Algorithm algorithm = algorithmDebugService.selectAlgorithmById(algorithmid);
        model.addAttribute("algorithm",algorithm);
        String algorithmpath = algorithm.getAlgorithmpath();
        BufferedReader bfr = new BufferedReader(new FileReader(algorithmpath));
        String str = null;
        int lineNumber = 0;
        while ((str = bfr.readLine()) != null) {
            lineNumber++;
        }

        List<String> algorithmStringList=new ArrayList<>();
        BufferedReader bfr1 = new BufferedReader(new FileReader(algorithmpath));
        String str1 = null;
        int lineNumber1 = 0;
        while ((str1 = bfr1.readLine()) != null) {
            algorithmStringList.add(String.valueOf(lineNumber1) + " " + str1);
            lineNumber1++;
        }
        String algorithmJsonString= JSON.toJSONString(algorithmStringList);
        model.addAttribute("algorithmJsonString",algorithmJsonString);
        return new ModelAndView("algorithm/algorithmView","Modelmodel",model);
    }


    @RequestMapping("/deleteAlgorithm")
    public String deleteAlgorithm(@RequestBody String jsonData){
        JSONObject jsonObject = JSON.parseObject(jsonData);
        String algorithmid = jsonObject.getString("algorithmid");
        algorithmDebugService.deleteAlgorithm(algorithmid);
        return jsonData;
    }

////模型
//@RequestMapping("/algorithmModelDebug")
//public ModelAndView algorithmModelDebug(Model model){
//    //查询所有算法
//    List<AlgorithmModel> algorithmModelList = algorithmDebugService.selectAlgorithmModel();
//    model.addAttribute("algorithmModelList",algorithmModelList);
//
//    String algorithmModelName = "";
//    for (int i = 0;i < algorithmModelList.size(); i++){
//        algorithmModelName = algorithmModelName + algorithmModelList.get(i).getAlgorithmmodelname() +";";
//    }
//    model.addAttribute("algorithmModelName",algorithmModelName);
//    return new ModelAndView("algorithmModelDebug","Modelmodel",model);
//}
//
//    @RequestMapping("/deleteAlgorithmModel")
//    public String deleteAlgorithmModel(@RequestBody String jsonData){
//        JSONObject jsonObject = JSON.parseObject(jsonData);
//        String algorithmmodelid = jsonObject.getString("algorithmmodelid");
//        algorithmDebugService.deleteAlgorithmModel(algorithmmodelid);
//        return jsonData;
//    }
//
//    @RequestMapping("/algorithmModelView/{id}")
//    public ModelAndView algorithmModelView(Model model, @PathVariable("id") Integer algorithmmodelid) throws IOException{
//        AlgorithmModel algorithmModel = algorithmDebugService.selectAlgorithmModelById(algorithmmodelid);
//        model.addAttribute("algorithm",algorithmModel);
//        String algorithmpath = algorithmModel.getAlgorithmmodelpath();
//        BufferedReader bfr = new BufferedReader(new FileReader(algorithmpath));
//        String str = null;
//        int lineNumber = 0;
//        while ((str = bfr.readLine()) != null) {
//            lineNumber++;
//        }
//
//        List<String> algorithmStringList=new ArrayList<>();
//        BufferedReader bfr1 = new BufferedReader(new FileReader(algorithmpath));
//        String str1 = null;
//        int lineNumber1 = 0;
//        while ((str1 = bfr1.readLine()) != null) {
//            algorithmStringList.add(String.valueOf(lineNumber1) + " " + str1);
//            lineNumber1++;
//        }
//        String algorithmJsonString= JSON.toJSONString(algorithmStringList);
//        model.addAttribute("algorithmJsonString",algorithmJsonString);
//        return new ModelAndView("algorithmModelView","Modelmodel",model);
//    }
}
