package com.xd.zt.controller.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xd.zt.domain.analyse.Algorithm;
import com.xd.zt.domain.analyse.AnalyseModel;
import com.xd.zt.domain.business.BusinessModel;
import com.xd.zt.domain.data.DatamodelName;
import com.xd.zt.domain.model.Programme;
import com.xd.zt.service.model.ModelService;
import com.xd.zt.service.model.ProgrammeUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping("/model")
@Controller
public class ModelController {
    @Autowired
    private ModelService modelService;
    @Autowired
    private ProgrammeUpdateService programmeUpdateService;
    @RequestMapping("/index")
    public String index(){
        return "model/index";
    }

    @RequestMapping("/modelWelcome")
    public String welcome(){
        return "model/modelWelcome";
    }

    @RequestMapping("/designList")
    public ModelAndView designList(Model model){
        List<Programme> programmeList = modelService.selectAllModelByType("勘察设计");
        model.addAttribute("programmeList",programmeList);
        return new ModelAndView("model/modelList","Modelmodel",model);
    }

    @RequestMapping("/constructList")
    public ModelAndView constructList(Model model){
        List<Programme> programmeList = modelService.selectAllModelByType("工程施工");
        model.addAttribute("programmeList",programmeList);
        return new ModelAndView("model/modelList","Modelmodel",model);
    }

    @RequestMapping("/maintainList")
    public ModelAndView maintainList(Model model){
        List<Programme> programmeList = modelService.selectAllModelByType("运营维护");
        model.addAttribute("programmeList",programmeList);
        return new ModelAndView("model/maintainList","Modelmodel",model);
    }

    @RequestMapping("/cityList")
    public ModelAndView cityList(Model model){
        List<Programme> programmeList = modelService.selectAllModelByType("智慧城市");
        model.addAttribute("programmeList",programmeList);
        return new ModelAndView("model/cityList","Modelmodel",model);
    }

    @RequestMapping("/modelView/{programmeid}")
    public ModelAndView modelView(Model model, @PathVariable("programmeid") Integer programmeid){
        List<BusinessModel> businessModelList = modelService.selectBusinessModelByProgramme(programmeid);
        List<DatamodelName> datamodelNameList = modelService.selectDataModelByProgramme(programmeid);
        List<AnalyseModel> analyseModelList = modelService.selectAnalyseModelByProgramme(programmeid);
        model.addAttribute("businessModelList",businessModelList);
        model.addAttribute("datamodelNameList",datamodelNameList);
        model.addAttribute("analyseModelList",analyseModelList);
        return new ModelAndView("model/modelView","Modelmodel",model);
    }

    @ResponseBody
    @RequestMapping("/deleteModel")
    public String deleteModel(@RequestBody JSONObject jsonObject){
        Integer programmeid = jsonObject.getInteger("programmeid");
        Programme programme = modelService.selectProgrammeById(programmeid);
        if (programme.getProgrammepath() != null) {
            File file = new File(programme.getProgrammepath());
            file.delete();
            //清除进程
            boolean result = file.delete();
            if (!result) {
                System.gc();
                file.delete();
            }
        }
        modelService.deleteModel(programmeid);

        return programmeid.toString();
    }

    @ResponseBody
    @PostMapping("/updateCityProgramme")
    public void upFile(@RequestParam("filename") MultipartFile multipartFile) throws Exception {
        /*      String sqlPath=null;*/
        String[] fileInformation = programmeUpdateService.Upload(multipartFile);
        String filename = fileInformation[0];


        String[] fileName = filename.split("\\.");
        String algorithmName = fileName[0];


        String filepath = fileInformation[1];
        String filesize = fileInformation[2];
//        System.out.println(filename+"----"+ filepath+"----"+filesize);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        Programme programme = new Programme();
        programme.setProgrammename(algorithmName);
        programme.setProgrammepath(filepath);
        programme.setProgrammetime(date);
        programme.setUsername("name");
        programme.setProgrammetype("智慧城市");
        modelService.saveProgramme(programme);
    }
    @ResponseBody
    @PostMapping("/updateMaintainProgramme")
    public void upFile1(@RequestParam("filename") MultipartFile multipartFile) throws Exception {
        /*      String sqlPath=null;*/
        String[] fileInformation = programmeUpdateService.Upload(multipartFile);
        String filename = fileInformation[0];


        String[] fileName = filename.split("\\.");
        String algorithmName = fileName[0];


        String filepath = fileInformation[1];
        String filesize = fileInformation[2];
//        System.out.println(filename+"----"+ filepath+"----"+filesize);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        Programme programme = new Programme();
        programme.setProgrammename(algorithmName);
        programme.setProgrammepath(filepath);
        programme.setProgrammetime(date);
        programme.setUsername("name");
        programme.setProgrammetype("运营维护");
        modelService.saveProgramme(programme);
    }

    @RequestMapping("/codeView/{id}")
    public ModelAndView codeView(Model model, @PathVariable("id") Integer programmeid) throws IOException {
         Programme programme = modelService.selectProgrammeById(programmeid);
        model.addAttribute("programme",programme);
        String programmepath = programme.getProgrammepath();
        BufferedReader bfr = new BufferedReader(new FileReader(programmepath));
        String str = null;
        int lineNumber = 0;
        while ((str = bfr.readLine()) != null) {
            lineNumber++;
        }

        List<String> algorithmStringList=new ArrayList<>();
        BufferedReader bfr1 = new BufferedReader(new FileReader(programmepath));
        String str1 = null;
        int lineNumber1 = 0;
        while ((str1 = bfr1.readLine()) != null) {
            algorithmStringList.add(String.valueOf(lineNumber1) + " " + str1);
            lineNumber1++;
        }
        String algorithmJsonString= JSON.toJSONString(algorithmStringList);
        model.addAttribute("algorithmJsonString",algorithmJsonString);
        bfr.close();
        bfr1.close();
        return new ModelAndView("model/codeView","Modelmodel",model);
    }
}
