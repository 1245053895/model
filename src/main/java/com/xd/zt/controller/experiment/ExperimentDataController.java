package com.xd.zt.controller.experiment;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xd.zt.domain.experiment.ExperimentData;
import com.xd.zt.repository.experiment.DataRepository;
import com.xd.zt.repository.experiment.ExperimentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/experiment")
public class ExperimentDataController {
    @Autowired
    private ExperimentRepository experimentRepository;
    @Autowired
    private DataRepository dataRepository;

    @RequestMapping("/datalead/{id}")
    public ModelAndView datalead(Model model,@PathVariable("id") Integer id) {
        return new ModelAndView("experiment/datalead", "modelModel", model);
    }

    @PostMapping(value = "/saveData")
    @ResponseBody   /*,@RequestBody(required = false) String shebei,@RequestBody(required = false) String xianlu,@RequestBody(required = false) String test10*/
    public Map<String,Object> saveData(@RequestBody(required = false) String JsonData) {
        JSONObject jsonObject=JSON.parseObject(JsonData);
       String datatype= jsonObject.get("datatype").toString();
       String shebei=jsonObject.get("shebei").toString();
       String xianlu=jsonObject.get("xianlu").toString();
       String test10=jsonObject.get("test10").toString();
       String params=shebei+";"+xianlu+";"+test10;
        ExperimentData experimentData=new ExperimentData();
        experimentData.setDatatype(datatype);
        experimentData.setParams(params);
        experimentRepository.save(experimentData);
        Map<String,Object> map=new HashMap<>();
        map.put("code",1);
        return map;
    }

    @RequestMapping("/resultShow/{id}")
    public ModelAndView experimentresultShow(Model model,@PathVariable("id") Integer id) {
      ExperimentData experimentData= dataRepository.findById(id).orElse(null);
      String datatype=experimentData.getDatatype();
      String params=experimentData.getParams();
      String array[]=params.split(";");
      String shebei=array[0];
      String xianlu=array[1];
      String shijian=array[2];
       model.addAttribute("datatype",datatype);
        model.addAttribute("shebei",shebei);
        model.addAttribute("xianlu",xianlu);
        model.addAttribute("shijian",shijian);
        return new ModelAndView("experiment/resultShow", "modelModel", model);
    }
}
