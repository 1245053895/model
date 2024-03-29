package com.xd.zt.controller.dataManager;

import com.xd.zt.domain.data.DatamodelSource;
import com.xd.zt.domain.dataManage.DataManage;
import com.xd.zt.domain.dataManage.DataManage;
import com.xd.zt.domain.experiment.ExperimentModel;
import com.xd.zt.service.dataManager.DataManagerService;
import com.xd.zt.service.experiment.ExperimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    /*过程数据的检索*/
    //搜索数据
    @GetMapping(value = "/dataManagerSearch")
    public ModelAndView dataSearch(Model model, @RequestParam("search_text") String search_text) {
        //拼接模糊查询的通配符
        String res = "%" + search_text + "%";
        List<DataManage> selectDataList= dataManageService.moHuDataList(res);
        List<DataManage> selectDataList1=dataManageService.moHuDataList1(res);
        List<DataManage> selectDataList2=dataManageService.moHuDataList2(res);
        model.addAttribute("selectDataList", selectDataList);
        model.addAttribute("selectDataList1", selectDataList1);
        model.addAttribute("selectDataList2", selectDataList2);
        return new ModelAndView("dataManager/unstructure", "modelModel", model);
    }





}
