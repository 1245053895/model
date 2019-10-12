package com.xd.zt.controller.algorithm;

import com.alibaba.fastjson.JSON;

import com.xd.zt.domain.analyse.Algorithm;
import com.xd.zt.service.algorithm.AlgorithmDebugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
@RequestMapping("/algorithm")
@Controller

public class CodemirrorController {
    @Autowired
    private AlgorithmDebugService algorithmDebugService;


    @RequestMapping("/codemirroredit")
    public String codemirroredit(){
        return "algorithm/uploadAlgorithm";
    }

    @RequestMapping("/algorithmOnline")
    public ModelAndView algorithmOnline(Model model)throws Exception{
//        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+ "static/algorithmModule/";
        BufferedReader bfr = new BufferedReader(new FileReader("src/main/resources/static/algorithmModule/module1.py"));
        String str = null;
        int lineNumber = 0;
        while ((str = bfr.readLine()) != null) {
            lineNumber++;
        }

        List<String> algorithmStringList=new ArrayList<>();
        BufferedReader bfr1 = new BufferedReader(new FileReader("src/main/resources/static/algorithmModule/module1.py"));
        String str1 = null;
        int lineNumber1 = 0;
        while ((str1 = bfr1.readLine()) != null) {
            algorithmStringList.add(str1);
            lineNumber1++;
        }
        String algorithmJsonString= JSON.toJSONString(algorithmStringList);
        model.addAttribute("algorithmJsonString",algorithmJsonString);
        return new ModelAndView( "algorithm/algorithmOnline","Modelmodel",model);
    }

    @ResponseBody
    @RequestMapping("/saveCodeController")
    public String saveCodeController(@RequestParam("name") String name, String type, String content){
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+ "static/algorithm/";
        String fileName = path+name+type;
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(fileName));
            writer.println(content);
            writer.close();
        }catch (IOException e){
            System.out.println(e.toString());
        }
        Algorithm algorithm = new Algorithm();
        algorithm.setAlgorithmname(name);
        algorithm.setAlgorithmpath(fileName);
        algorithmDebugService.insertAlgorithm(algorithm);
        return "保存成功";
    }
}
