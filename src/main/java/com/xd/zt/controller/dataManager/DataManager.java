package com.xd.zt.controller.dataManager;

import com.xd.zt.domain.analyse.AnalyseModel;
import com.xd.zt.domain.data.DatamodelName;
import com.xd.zt.domain.data.DatamodelSource;
import com.xd.zt.domain.dataManage.DataManage;
import com.xd.zt.domain.dataManage.DataManage;
import com.xd.zt.domain.dataManage.MysqlData;
import com.xd.zt.domain.experiment.ExperimentModel;
import com.xd.zt.service.analyse.AnalyseModelService;
import com.xd.zt.service.data.DataModelService;
import com.xd.zt.service.dataManager.DataManagerService;
import com.xd.zt.service.experiment.ExperimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dataManager")
public class DataManager {

    @Autowired
    private DataManagerService dataManageService;
    @Autowired
    private DataModelService dataModelService;
    @Autowired
    private AnalyseModelService analyseModelService;
    @Autowired
    private ExperimentService experimentService;

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


    @RequestMapping("/structure")
    public ModelAndView structure(Model model) {
        List<MysqlData> MysqlData = dataManageService.selectMysqlDataList();
        model.addAttribute("MysqlData", MysqlData);
        List<DatamodelName> dataModelList = dataModelService.selectdatamodel();
        model.addAttribute("dataModelList", dataModelList);
        List<AnalyseModel> analyseModelList= analyseModelService.selectqueslist();
        model.addAttribute("analyseModelList",analyseModelList);
        List<ExperimentModel> experimentModelList = experimentService.selectExperimentModelList();
        model.addAttribute("experimentModelList", experimentModelList);
        return new ModelAndView("dataManager/structure", "modelModel", model);
    }
    @ResponseBody
    @RequestMapping("/MysqlDataDelete")
    public String MysqlDataDelete(@RequestParam("modelid")  String modelid){
        Map<String,Object> map=new HashMap<>();
        Integer id= Integer.valueOf(modelid);  //将string类型转为Integer类型
        dataManageService.MysqlDataDelete(id);
        return modelid;
    }


}
