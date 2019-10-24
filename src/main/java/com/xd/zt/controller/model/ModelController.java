package com.xd.zt.controller.model;

import com.alibaba.fastjson.JSONObject;
import com.xd.zt.domain.analyse.AnalyseModel;
import com.xd.zt.domain.business.BusinessModel;
import com.xd.zt.domain.data.DatamodelName;
import com.xd.zt.domain.model.Programme;
import com.xd.zt.service.model.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/model")
@Controller
public class ModelController {
    @Autowired
    private ModelService modelService;
    @RequestMapping("/index")
    public String index(){
        return "model/index";
    }

    @RequestMapping("/modelWelcome")
    public String welcome(){
        return "model/modelWelcome";
    }

    @RequestMapping("/modelList")
    public ModelAndView welcome(Model model){
        List<Programme> programmeList = modelService.selectAllModel();
        model.addAttribute("programmeList",programmeList);
        return new ModelAndView("model/modelList","Modelmodel",model);
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
        modelService.deleteModel(programmeid);

        return programmeid.toString();
    }
}
