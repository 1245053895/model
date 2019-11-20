package com.xd.zt.controller.analyse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.xd.zt.domain.analyse.*;
import com.xd.zt.domain.data.DatamodelInfo;
import com.xd.zt.service.analyse.AnalyseResultService;
import com.xd.zt.service.analyse.AnalyseService;
import com.xd.zt.service.business.BusinessFlowService;
import com.xd.zt.service.experiment.ExperimentConfigService;
import com.xd.zt.util.analyse.FindLinuxDirectory;
import com.xd.zt.util.analyse.HttpCientPost;
import com.xd.zt.util.analyse.HttpClientGet;
import com.xd.zt.util.analyse.HttpUtil;
import com.xd.zt.util.data.JsonKeyToStringList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import javax.persistence.criteria.CriteriaBuilder;
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

    @Autowired
    private AnalyseResultService analyseResultService;

    @Autowired
    private ExperimentConfigService experimentConfigService;
//    @RequestMapping("index")
//    public ModelAndView index(Model model){
//        List<AnalyseModelProcess> analyseModelList = analyseService.selectAnalyseModel();
//        model.addAttribute("analyseModelList",analyseModelList);
//        return new ModelAndView("index","Modelmodel",model);
//    }

    @RequestMapping("/analyzModelCreate/{analysemodelid}")
    public ModelAndView analyzModelCreate(Model model,@PathVariable("analysemodelid")Integer analysemodelid){
        List<Algorithm> algorithmList = analyseService.selectAlgorithm();
        List<Algorithm> algorithmList1 = new ArrayList<>();
        for (int i = 0 ; i < algorithmList.size(); i++){
            String type = algorithmList.get(i).getAlgorithmtype();
            if ("模型训练".equals(type)){
                int j = 0;
                algorithmList1.add(j,algorithmList.get(i));
                j++;
            }
        }
        model.addAttribute("algorithmList",algorithmList1);
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
        List<Algorithm> algorithmList1 = new ArrayList<>();
        for (int i = 0 ; i < algorithmList.size(); i++){
            String type = algorithmList.get(i).getAlgorithmtype();
            if ("模型训练".equals(type)){
                int j = 0;
                algorithmList1.add(j,algorithmList.get(i));
                j++;
            }
        }

        model.addAttribute("algorithmList",algorithmList1);
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

        List<DatamodelInfo> datamodelInfoList1 = analyseService.selectDataResult(modelid);
        List<DatamodelInfo> datamodelInfoList = new ArrayList<>();
        for (int i = 0 ; i < datamodelInfoList1.size(); i++){
            if (datamodelInfoList1.get(i).getDatalink() != null || datamodelInfoList1.get(i).getDatablock() != null){
                datamodelInfoList.add(datamodelInfoList1.get(i));
            }
            else {
            }
        }

        Integer analysemodelid = analyseService.selectIdInModelAnalyseProcess(modelid);
        List<AnalyseModelProcess> allAnalyseModelProcesses = experimentConfigService.allAnalyseModelProcess(analysemodelid);
        List<AnalyseCsv> analyseCsvList = new ArrayList<>();
        for(int i=0; i<allAnalyseModelProcesses.size(); i++) {
            Integer modelid1 = allAnalyseModelProcesses.get(i).getModelid();
            List<AnalyseInstance> analyseInstances = experimentConfigService.allAnalyseInstance(modelid1);
            for (int j = 0; j < analyseInstances.size(); j++) {
                int instanceid = analyseInstances.get(j).getModelinstanceid();
                List<AnalyseCsv> analyseCsvList1 = analyseService.selectCsvExit(instanceid);
                for (int k = 0; k < analyseCsvList1.size(); k++) {
                    analyseCsvList.add(analyseCsvList1.get(k));
                }
            }
        }
        List<AnalyseSource> analyseSourceList = analyseResultService.selectAnalyseSource(analysemodelid);
        model.addAttribute("datamodelInfoList",datamodelInfoList);
        model.addAttribute("analyseCsvList",analyseCsvList);
        model.addAttribute("analyseSourceList",analyseSourceList);
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
            algorithmname = algorithm.get("name").toString();
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
            httpUtil.post("http://10.101.201.174:8000/tasks/"+modelinstanceid+"/delete/",null);
//            httpUtil.post("http://120.24.157.214:8000/tasks/"+modelinstanceid+"/delete/",null);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return modelinstanceid;
    }

    @ResponseBody
    @RequestMapping("/exampleRun")
    public Map<String,Object> exampleRun(@RequestBody JSONObject jsonObject) throws Exception{
        Map map = new HashMap();
        String modelinstanceid = jsonObject.get("exampleid").toString();
        String instantData = jsonObject.get("instantData").toString();
        String parameterss = analyseService.selectParams(modelinstanceid);
        Boolean status = false;
        if (instantData == "false"){status = false;}
        else {status = true;}
        //生成json文件
        JSONArray parameters = JSONArray.parseArray(parameterss);
        JSONObject modelinstance = new JSONObject();
        modelinstance.put("username","name");
        modelinstance.put("modelInstanceId",modelinstanceid);
        modelinstance.put("instantData",status);
        modelinstance.put("analyzmodel",parameters);

        String jsonString= JSON.toJSONString(modelinstance);
        try {
//              String result =  HttpCientPost.restPost("http://120.24.157.214:8000/tasks/",jsonString);
            String result =  HttpCientPost.restPost("http://10.101.201.174:8000/tasks/",jsonString);
             System.out.printf(result);
            JSON resultjson = JSON.parseObject(result);
            map.put("resp_code",((JSONObject) resultjson).getInteger("resp_code"));
            map.put("resp_msg",((JSONObject) resultjson).getString("resp_msg"));


            if (((JSONObject) resultjson).getInteger("resp_code") == 0) {
                String taskId = ((JSONObject) resultjson).getString("taskId");
                JSONObject params = parameters.getJSONObject(0).getJSONObject("params");
                JSONObject outputpath = params.getJSONObject("outputpath");
                String modelPathname = new String();
                String modelPath = new String();
                if (outputpath.containsKey("modelPath")) {
                     modelPathname = outputpath.getString("modelPath");
                     modelPath = "/var/data/celery/output/"+taskId+"/output/"+modelPathname;
                }
                else if (outputpath.containsKey("modelPaths")){
                     modelPathname = "models_for_"+parameters.getJSONObject(0).getString("name");
                     modelPath = "/var/data/celery/output/"+taskId+"/output/";
                }
                AnalyseResult analyseResult = new AnalyseResult();
                analyseResult.setInstanceid(Integer.parseInt(modelinstanceid));
                analyseResult.setModelPathname(modelPathname);
                analyseResult.setModelPath(modelPath);

               AnalyseResult analyseResult1 = analyseResultService.selectInstanceid(Integer.parseInt(modelinstanceid));
                if (analyseResult1 == null){
                    analyseResultService.saveAnalyseResult(analyseResult);
                }
                else{
                    analyseResultService.updateAnalyseResult(analyseResult);
                }

                //遍历获得所有结果的csv路径
                String Directory = FindLinuxDirectory.FindDirectory("10.101.201.174",22,"root","/zt/IA","find /var/data/celery/output/"+taskId+"/output/ -type f -name '*.csv'");
                System.out.printf(Directory);
                if (Directory == "" || Directory == null || Directory == "出现错误"){
                }
                else {
                    String[] DirectoryList = Directory.split("\n");

                    List<AnalyseCsv> analyseCsvList = analyseService.selectCsvExit(Integer.parseInt(modelinstanceid));
                    if (analyseCsvList.size() == 0) {
                        for (int i = 0; i < DirectoryList.length; i++) {
                            String[] FileDirectory = DirectoryList[i].split("/");
                            String fileName = FileDirectory[FileDirectory.length - 1];
                            AnalyseCsv analyseCsv = new AnalyseCsv();
                            analyseCsv.setCsvname(fileName);
                            analyseCsv.setCsvpath(DirectoryList[i]);
                            analyseCsv.setModelinstanceid(Integer.parseInt(modelinstanceid));
                            analyseService.saveAnalyseCsv(analyseCsv);
                        }
                    } else {
                        analyseService.deleteAnalyseCsv(Integer.parseInt(modelinstanceid));
                        for (int i = 0; i < DirectoryList.length; i++) {
                            String[] FileDirectory = DirectoryList[i].split("/");
                            String fileName = FileDirectory[FileDirectory.length - 1];
                            AnalyseCsv analyseCsv = new AnalyseCsv();
                            analyseCsv.setCsvname(fileName);
                            analyseCsv.setCsvpath(DirectoryList[i]);
                            analyseCsv.setModelinstanceid(Integer.parseInt(modelinstanceid));
                            analyseService.saveAnalyseCsv(analyseCsv);
                        }
                    }
                }
                map.put("datas",((JSONObject) resultjson).getString("datas"));
                    return map;
            }
            else {
                return map;
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            map.put("resp_msg",e.toString());
            return map;
        }

    }


    @RequestMapping("/taskList")
    public ModelAndView taskList(Model model){
//        List<AnalyticsTask> analyticsTaskList = analyseService.selectTask();
         List<AnalyticsTask> analyticsTaskList = new ArrayList<>();
        String taskString = new String();
        try {
//        taskString =  HttpClientGet.restGet("http://120.24.157.214:8000/tasks/"+"name"+"/",null);
        taskString =  HttpClientGet.restGet("http://10.101.201.174:8000/tasks/"+"name"+"/",null);
        System.out.printf(taskString);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        JSONObject taskJson = JSON.parseObject(taskString);
        JSONArray taskListArray = taskJson.getJSONArray("datas");
        for (int i = 0 ; i < taskListArray.size(); i++){
            AnalyticsTask analyticsTask = new AnalyticsTask();
            analyticsTask.setTaskId(taskListArray.getJSONObject(i).getString("taskId"));
            analyticsTask.setModelInstanceId(taskListArray.getJSONObject(i).getString("modelInstanceId"));
            analyticsTask.setTimestamp(taskListArray.getJSONObject(i).getString("timestamp"));
            analyticsTask.setStatus(taskListArray.getJSONObject(i).getString("status"));
            analyticsTask.setCompleteTime(taskListArray.getJSONObject(i).getString("completeTime"));
            analyticsTask.setResult(taskListArray.getJSONObject(i).getString("result"));
            analyticsTaskList.add(i,analyticsTask);
        }
        model.addAttribute("analyticsTaskList",analyticsTaskList);
        return new ModelAndView("analyse/taskList","modelModel",model);
    }

    
}
