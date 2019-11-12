package com.xd.zt.controller.dataManager;

import com.xd.zt.domain.dataManage.DataManage;
import com.xd.zt.domain.dataManage.DataManage;
import com.xd.zt.domain.experiment.ExperimentModel;
import com.xd.zt.service.dataManager.DataManagerService;
import com.xd.zt.service.experiment.ExperimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/dataManager")
public class DataManager {

    @Autowired
    private DataManagerService dataManageService;

    @RequestMapping("/index")
    public String index(){

        return "dataManager/index";
    }
    @RequestMapping("/dataManagerWelcome")
    public String welcome(){
        return "dataManager/dataManagerWelcome";
    }

    @RequestMapping("/dataManager")
    public String dataManager(){
        return "dataManager/dataManager";
    }


    @RequestMapping("/structure")
    public String structure(){
        return "dataManager/structure";
    }
/*

    @RequestMapping("/unstructure")
    public String unstructure(){
        return "dataManager/unstructure";
    }
*/



    @RequestMapping("/unstructure")
    public ModelAndView unstructure(Model model) {
        List<DataManage> selectDataList = dataManageService.selectDataList();
        List<DataManage> selectDataList1 = dataManageService.selectDataList1();
        List<DataManage> selectDataList2= dataManageService.selectDataList2();
        model.addAttribute("selectDataList", selectDataList);
        model.addAttribute("selectDataList1", selectDataList1);
        model.addAttribute("selectDataList2", selectDataList2);
        return new ModelAndView("dataManager/unstructure", "modelModel", model);
    }



}
