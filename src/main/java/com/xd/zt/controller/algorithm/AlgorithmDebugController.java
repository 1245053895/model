package com.xd.zt.controller.algorithm;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.xd.zt.domain.analyse.Algorithm;
import com.xd.zt.domain.business.BusinessFile;
import com.xd.zt.service.algorithm.AlgorithmDebugService;
import com.xd.zt.service.algorithm.AlgorithmUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


@RequestMapping("/algorithm")
@Controller
public class AlgorithmDebugController {
    @Autowired
    private AlgorithmDebugService algorithmDebugService;
    @Autowired
    private AlgorithmUpdateService algorithmUpdateService;
    @Autowired
    private SessionRepository sessionRepository;
    @RequestMapping("/index")
    public ModelAndView index(Model model,@RequestParam(value = "sessionId")String sessionId) {
        Session session = sessionRepository.findById(sessionId);
        System.out.printf("\n\n算法库的sessionId:"+sessionId);
        try{
            String SessionId = session.getAttribute("SessionId");
            System.out.printf("\n\n检查sessionId："+SessionId);
            model.addAttribute("SessionId",SessionId);
            return new ModelAndView("algorithm/index","Modelmodel",model);
        }
        catch (Exception e){
//            return "redirect:http://10.101.201.154:8080/#/home/index";
            System.out.printf("\n\n检查sessionId不存在");
            return new ModelAndView(new RedirectView("http://10.101.201.173:80/login"));
        }
    }


    @RequestMapping("/algorithmWelcome")
    public String algorithmWelcome() {
        return "algorithm/algorithmWelcome";
    }

    @RequestMapping("/select")
    public String select() {
        return "algorithm/select";
    }

    /*行业通用算法*/
    @RequestMapping("/algorithmList")
    public ModelAndView algorithmList(Model model) {
        String algorithmlabel = "行业通用";
        List<Algorithm> algorithmList = algorithmDebugService.selectAlgorithmCommon(algorithmlabel);
        model.addAttribute("algorithmList", algorithmList);
        return new ModelAndView("algorithm/algorithmList", "Modelmodel", model);
    }

    /*行业专用算法*/
    @RequestMapping("/algorithmListProcess")
    public ModelAndView algorithmListProcess(Model model) {
        String algorithmlabel = "行业专用";
        List<Algorithm> algorithmList = algorithmDebugService.selectAlgorithmProcess(algorithmlabel);
        model.addAttribute("algorithmList", algorithmList);
        return new ModelAndView("algorithm/algorithmList", "Modelmodel", model);
    }


    /*人工智能算法*/
    @RequestMapping("/algorithmListLogical")
    public ModelAndView algorithmListLogical(Model model) {
        String algorithmlabel = "人工智能";
        List<Algorithm> algorithmList = algorithmDebugService.selectAlgorithmLogical(algorithmlabel);
        model.addAttribute("algorithmList", algorithmList);
        return new ModelAndView("algorithm/algorithmList", "Modelmodel", model);
    }


  /*  @RequestMapping("/algorithmList1")
    public ModelAndView algorithmList1(Model model){
        String algorithmlabel="行业专用";
        List<Algorithm> algorithmList = algorithmDebugService.selectAlgorithmProcess(algorithmlabel);
        List<Algorithm> arr1 = new ArrayList<Algorithm>();
        for (Algorithm algorithm : algorithmList) {
            String sss =algorithm.getAlgorithmlabel();
            if(sss.equals("行业专用")){
                arr1.add(algorithm);
            }
        }
        model.addAttribute("algorithmList",arr1);

        return new ModelAndView("algorithm/algorithmList","Modelmodel",model);
    }


    @RequestMapping("/algorithmList2")
    public ModelAndView algorithmList2(Model model){
        String algorithmlabel="人工智能";
        List<Algorithm> algorithmList = algorithmDebugService.selectAlgorithmLogical(algorithmlabel);
        List<Algorithm> arr1 = new ArrayList<Algorithm>();
        for (Algorithm algorithm : algorithmList) {
            String sss =algorithm.getAlgorithmlabel();
            if(sss.equals("人工智能")){
                arr1.add(algorithm);
            }
        }

        model.addAttribute("algorithmList",arr1);

        return new ModelAndView("algorithm/algorithmList","Modelmodel",model);
    }

    @RequestMapping("/algorithmList3")
    public ModelAndView algorithmList3(Model model){
        List<Algorithm> algorithmList = algorithmDebugService.selectAlgorithm();
        List<Algorithm> arr1 = new ArrayList<Algorithm>();
        for (Algorithm algorithm : algorithmList) {
            String sss =algorithm.getAlgorithmlabel();
            if(sss.equals("人工智能")){
                arr1.add(algorithm);
            }
        }

        model.addAttribute("algorithmList",arr1);

        return new ModelAndView("algorithm/algorithmList","Modelmodel",model);
    }*/

    @GetMapping("/algorithmSearch")
    public ModelAndView dataSearch(Model model, String search_text) {
        List<Algorithm> algorithmList = algorithmDebugService.searchAlgorithm(search_text);
        model.addAttribute("algorithmList", algorithmList);
        return new ModelAndView("algorithm/algorithmList", "Modelmodel", model);
    }

    @RequestMapping("/algorithmDebug")
    public ModelAndView analyzModelExample(Model model) {
        //查询所有算法
        List<Algorithm> algorithmList = algorithmDebugService.selectAlgorithm();

        model.addAttribute("algorithmList", algorithmList);

        String algorithmName = "";
        for (int i = 0; i < algorithmList.size(); i++) {
            algorithmName = algorithmName + algorithmList.get(i).getAlgorithmname() + ";";
        }
        model.addAttribute("algorithmName", algorithmName);
        return new ModelAndView("algorithm/algorithmDebug", "Modelmodel", model);
    }

    @RequestMapping("/algorithmView/{id}")
    public ModelAndView algorithmView(Model model, @PathVariable("id") Integer algorithmid) throws IOException {
        Algorithm algorithm = algorithmDebugService.selectAlgorithmById(algorithmid);
        model.addAttribute("algorithm", algorithm);
        String algorithmpath = algorithm.getAlgorithmpath();
        BufferedReader bfr = new BufferedReader(new FileReader(algorithmpath));
        String str = null;
        int lineNumber = 0;
        while ((str = bfr.readLine()) != null) {
            lineNumber++;
        }

        List<String> algorithmStringList = new ArrayList<>();
        BufferedReader bfr1 = new BufferedReader(new FileReader(algorithmpath));
        String str1 = null;
        int lineNumber1 = 0;
        while ((str1 = bfr1.readLine()) != null) {
            algorithmStringList.add(String.valueOf(lineNumber1) + " " + str1);
            lineNumber1++;
        }
        bfr.close();
        bfr1.close();
        String algorithmJsonString = JSON.toJSONString(algorithmStringList);
        model.addAttribute("algorithmJsonString", algorithmJsonString);
        return new ModelAndView("algorithm/algorithmView", "Modelmodel", model);
    }




    @RequestMapping("/algorithmEdit/{id}")
    public ModelAndView algorithmEdit(Model model, @PathVariable("id") Integer algorithmid) throws IOException {
        List<String> algorithmStringList=new ArrayList<>();
        Algorithm algorithm = algorithmDebugService.selectAlgorithmById(algorithmid);
        String algorithmpath = algorithm.getAlgorithmpath();
        BufferedReader bfr = new BufferedReader(new FileReader(algorithmpath));
        String str1 = null;
        int lineNumber1 = 0;
        while ((str1 = bfr.readLine()) != null) {
            algorithmStringList.add(str1);
            lineNumber1++;
        }
        String algorithmJsonString= JSON.toJSONString(algorithmStringList);
        model.addAttribute("algorithmJsonString",algorithmJsonString);
        return new ModelAndView( "algorithm/algorithmOnlineEdit","Modelmodel",model);
    }














    @RequestMapping("/deleteAlgorithm")
    public String deleteAlgorithm(@RequestBody String jsonData) {
        JSONObject jsonObject = JSON.parseObject(jsonData);
        String algorithmid = jsonObject.getString("algorithmid");
        Algorithm algorithm = algorithmDebugService.selectAlgorithmById(Integer.parseInt(algorithmid));
        if (algorithm.getAlgorithmpath() != null) {
            File file = new File(algorithm.getAlgorithmpath());
            file.delete();
            //清除进程
            boolean result = file.delete();
            if (!result) {
                System.gc();
                file.delete();
            }
        }

        algorithmDebugService.deleteAlgorithm(algorithmid);
        return jsonData;
    }


    @ResponseBody
    @PostMapping("/updateAlgorithm")
    public Map<String, Object> upFile(@RequestParam(value = "filename", required = false) MultipartFile multipartFile, Algorithm algorithm) throws Exception {
        /*      String sqlPath=null;*/

        String miaoshu =algorithm.getAlgorithmmiaoshu();
        System.out.printf(miaoshu);
        String[] fileInformation = algorithmUpdateService.Upload(multipartFile);
        Map map = new HashMap();
        if (fileInformation != null) {
            String filename = fileInformation[0];
            String[] fileName = filename.split("\\.");
//        String algorithmName = fileName[0];
            // String filepath = fileInformation[1];
            String filesize = fileInformation[2];
//        System.out.println(filename+"----"+ filepath+"----"+filesize);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String date = simpleDateFormat.format(new Date());
            Algorithm algorithm1 = new Algorithm();
//        String algorithmname=fileName[0];
//        String algorithmtype=algorithm.getAlgorithmtype();
//        String algorithmdescribe=algorithm.getAlgorithmdescribe();
//        String algorithmlabel=algorithm.getAlgorithmlabel();
//        String algorithmtime=simpleDateFormat.format(new Date());
//        String algorithmversion=algorithm.getAlgorithmversion();
//        String algorithmparams=algorithm.getAlgorithmparams();
//        String algorithmpath=fileInformation[1];
//        String algorithmman="xidian";
            algorithm1.setAlgorithmname(fileName[0]);
            algorithm1.setAlgorithmtype(algorithm.getAlgorithmtype());
            algorithm1.setAlgorithmdescribe(algorithm.getAlgorithmdescribe());
            algorithm1.setAlgorithmlabel(algorithm.getAlgorithmlabel());
            algorithm1.setAlgorithmtime(simpleDateFormat.format(new Date()));
            algorithm1.setAlgorithmversion(algorithm.getAlgorithmversion());
            algorithm1.setAlgorithmparamsinput(algorithm.getAlgorithmparamsinput());
            algorithm1.setAlgorithmparamscontent(algorithm.getAlgorithmparamscontent());
            algorithm1.setAlgorithmparamsoutput(algorithm.getAlgorithmparamsoutput());
            algorithm1.setAlgorithmpath(fileInformation[1]);
            algorithm1.setAlgorithmman("xidian");
            algorithm1.setAlgorithmmiaoshu(algorithm.getAlgorithmmiaoshu());

            algorithmDebugService.saveAlgorithm(algorithm1);
//        algorithmDebugService.saveAlgorithm(algorithmname,algorithmtype,algorithmdescribe,algorithmlabel,algorithmtime,algorithmversion,algorithmparams,algorithmpath,algorithmman);
            map.put("code", 0);
        } else {
            map.put("code", 1);
        }
        return map;
    }





}



