package com.xd.zt.controller.analyse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.xd.zt.domain.analyse.*;
import com.xd.zt.service.analyse.AnalyseService;
import com.xd.zt.service.business.BusinessFlowService;
import com.xd.zt.util.analyse.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/analyse")
@Controller
public class AnalyseController {
    @Autowired
    private AnalyseService analyseService;

    @Autowired
    private BusinessFlowService flowService;

//    @RequestMapping("index")
//    public ModelAndView index(Model model){
//        List<AnalyseModelProcess> analyseModelList = analyseService.selectAnalyseModel();
//        model.addAttribute("analyseModelList",analyseModelList);
//        return new ModelAndView("index","Modelmodel",model);
//    }

    @RequestMapping("/analyzModelCreate/{analysemodelid}")
    public ModelAndView analyzModelCreate(Model model,@PathVariable("analysemodelid")Integer analysemodelid){
        List<Algorithm> algorithmList = analyseService.selectAlgorithm();
        model.addAttribute("algorithmList",algorithmList);
        model.addAttribute("analysemodelid",analysemodelid);
        return new ModelAndView("analyse/analyzModelCreate","Modelmoddel",model);
    }



    @RequestMapping("/analyzList/{analysemodelid}")
    public ModelAndView analyzList(Model model,@PathVariable("analysemodelid")Integer analysemodelid){
        List<AnalyseModelProcess> analyseModelList = analyseService.selectAnalyseModel(analysemodelid);
        model.addAttribute("analyseModelList",analyseModelList);
        return new ModelAndView("analyse/analyzList","Modelmodel",model);
    }

    @RequestMapping("/analyzModelList/{analysemodelid}")
    public ModelAndView analyzModelList(Model model,@PathVariable("analysemodelid")Integer analysemodelid){
        List<AnalyseModelProcess> analyseModelList = analyseService.selectAnalyseModel(analysemodelid);
        model.addAttribute("analyseModelList",analyseModelList);
        return new ModelAndView("analyse/analyzModelList","Modelmodel",model);
    }

    @ResponseBody
    @RequestMapping("/saveAnalyse")
    public String saveAnalyse(@RequestBody JSONObject jsonObject) throws Exception{
        String analysename = jsonObject.get("analysename").toString();
        String flowchart = jsonObject.get("flowchart").toString();
        String analysemodelid = jsonObject.get("analysemodelid").toString();
        analyseService.insertAnalyseModel(analysename,flowchart,analysemodelid);
        return analysename;
    }

    @ResponseBody
    @RequestMapping("/deleteModel")
    public String deleteModel(@RequestBody JSONObject jsonObject) throws Exception{
        String modelid = jsonObject.get("modelid").toString();
        analyseService.deleteModel(modelid);
        return modelid;
    }

    @ResponseBody
    @RequestMapping("/deletDataCollec")
    public String deletDataCollec(@RequestBody JSONObject jsonObject) throws Exception {

        String  flowprocessid = jsonObject.get("datacollecid").toString();
        Integer processid = analyseService.selectProcessid(flowprocessid);

        flowService.deleteBlocksAndConnects(processid);
            flowService.deleteProcessName(processid);
        analyseService.deleteanalyseprocess(processid);

        return flowprocessid;
    }


    @RequestMapping("/analyzModelView/{modelid}")
    public ModelAndView analyzModelView(Model model,@PathVariable("modelid") Integer modelid){
        model.addAttribute("modelid",modelid);
        String modelprocess = analyseService.selectAnalyseProcess(modelid);
        model.addAttribute("modelprocess",modelprocess);
        List<Algorithm> algorithmList = analyseService.selectAlgorithm();
        model.addAttribute("algorithmList",algorithmList);
        return new ModelAndView("analyse/analyzModelView","modelModel",model);
    }

    @ResponseBody
    @RequestMapping("/updateAnalyse")
    public String updateAnalyse(@RequestBody JSONObject jsonObject) throws Exception{
        String modelid = jsonObject.get("modelid").toString();
        String analysename = jsonObject.get("analysename").toString();
        String flowchart = jsonObject.get("flowchart").toString();
//        analyseService.insertAnalyseModel(analysename,flowchart);
        analyseService.updateAnalyse(modelid,analysename,flowchart);
        return modelid;
    }

    @RequestMapping("/exampleList/{modelid}")
    public ModelAndView exampleList(Model model,@PathVariable("modelid") Integer modelid){
        model.addAttribute("modelid",modelid);
        List<AnalyseInstance> analyseInstanceList =  analyseService.selectAnalyseExample(modelid);
        model.addAttribute("analyseInstanceList", analyseInstanceList);
        return new ModelAndView("analyse/exampleList","modelModel",model);
    }

    @RequestMapping("/analyzModelExample/{id}")
    public ModelAndView analyzModelExample(Model model, @PathVariable("id") Integer modelid){
        model.addAttribute("modelid",modelid);
        String modelprocess = analyseService.selectAnalyseProcess(modelid);
        model.addAttribute("modelprocess",modelprocess);
        //查询算法参数
        List<Algorithm> params = new ArrayList<>();
        String[] algorithm = modelprocess.split(";");
        for( int i = 1; i < algorithm.length - 1; i ++){
            Algorithm algorithm1  = analyseService.selectAlgorithmParams(algorithm[i]);
            params.add(algorithm1);
        }
        model.addAttribute("params",params);
        return new ModelAndView("analyse/analyzModelExample","Modelmodel",model);
    }

    @ResponseBody
    @RequestMapping("/saveExample")
    public Map<String,Object> saveExample(@RequestBody JSONObject jsonObject) throws Exception{
        Map<String,Object> map = new HashMap<>();
        String modelid = jsonObject.get("modelid").toString();
        String modelinstancename = jsonObject.get("examplename").toString();
        String analyzmodel = jsonObject.get("analyzmodel").toString();
        String modelinstanceid = analyseService.selectInstanceName(modelinstancename);
        if(modelinstanceid==""||modelinstanceid==null){
            analyseService.insertExample(modelid,modelinstancename,analyzmodel);
            map.put("code",1);
        }
        else {
            map.put("code",0);
        }
        return map;
    }


    @RequestMapping("/ExampleView/{id}")
    public ModelAndView ExampleView(Model model,@PathVariable("id")Integer modelinstanceid){
        String parameterss = analyseService.selectParams(modelinstanceid.toString());
        JSONArray parameters = JSONArray.parseArray(parameterss);
        String algorithmname;
        String algorithmparams;

        List<AlgorithmData> algorithmDataList = new ArrayList<>();
        for(int i = 0; i<parameters.size(); i++){
            JSONObject algorithm = new JSONObject();
            algorithm = parameters.getJSONObject(i);
            algorithmname = algorithm.get("name").toString()+"/data";
            algorithmparams = algorithm.get("params").toString();

            AlgorithmData algorithmData = new AlgorithmData();

            algorithmData.setAlgorithmname(algorithmname);
            algorithmData.setAlgorithmdata(algorithmparams);
            algorithmDataList.add(algorithmData);
        }
        model.addAttribute("algorithmDataList",algorithmDataList);
        model.addAttribute("modelinstanceid",modelinstanceid);
        Integer modelid = analyseService.selectModelid(modelinstanceid);
        model.addAttribute("modelid",modelid);
        String modelprocess = analyseService.selectAnalyseProcess(modelid);
        model.addAttribute("modelprocess",modelprocess);

        List<Algorithm> params = new ArrayList<>();
        String[] algorithm = modelprocess.split(";");
        for( int i = 1; i < algorithm.length - 1; i ++){
            Algorithm algorithm1  = analyseService.selectAlgorithmParams(algorithm[i]);
            params.add(algorithm1);
        }
        model.addAttribute("params",params);
    return new ModelAndView("analyse/analyzModelExampleView","Modelmodel",model);
    }



    @ResponseBody
    @RequestMapping("/exampleDelete")
    public String exampleDelete(@RequestBody JSONObject jsonObject) throws Exception{
        String modelinstanceid = jsonObject.get("exampleid").toString();
        analyseService.deleteExample(modelinstanceid);

        HttpUtil httpUtil = new HttpUtil();
        try {
            httpUtil.post("/tasks/"+modelinstanceid+"/cancel",null);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return modelinstanceid;
    }

    @ResponseBody
    @RequestMapping("/exampleRun")
    public String exampleRun(@RequestBody JSONObject jsonObject) throws Exception{
        String modelinstanceid = jsonObject.get("exampleid").toString();
        String instantData = jsonObject.get("instantData").toString();
        String parameterss = analyseService.selectParams(modelinstanceid);

        //生成json文件
        JSONArray parameters = JSONArray.parseArray(parameterss);
        JSONObject modelinstance = new JSONObject();
        modelinstance.put("username","name");
        modelinstance.put("modelInstanceId",modelinstanceid);
        modelinstance.put("instantData",instantData);
        modelinstance.put("analyzmodel",parameters);

        String jsonString= JSON.toJSONString(modelinstance);
//        CreateFileUtil.createJsonFile(jsonString,"C:\\Users\\ZLJ\\Desktop\\test","XGBregressor");
       HttpUtil httpUtil = new HttpUtil();
        try {
             httpUtil.post("/tasks",jsonString);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return modelinstanceid;
    }


    @RequestMapping("/taskList")
    public ModelAndView taskList(Model model){
        List<AnalyticsTask> analyticsTaskList = analyseService.selectTask();
        model.addAttribute("analyticsTaskList",analyticsTaskList);
        return new ModelAndView("analyse/taskList","modelModel",model);
    }

    
}
