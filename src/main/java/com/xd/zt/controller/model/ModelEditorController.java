package com.xd.zt.controller.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xd.zt.domain.analyse.*;
import com.xd.zt.domain.data.DatamodelInfo;
import com.xd.zt.domain.experiment.ExperimentConfig;
import com.xd.zt.domain.experiment.ExperimentData;
import com.xd.zt.domain.experiment.ExperimentModel;
import com.xd.zt.domain.model.Programme;
import com.xd.zt.service.analyse.AnalyseService;
import com.xd.zt.service.experiment.ExperimentConfigService;
import com.xd.zt.service.experiment.ExperimentDataService;
import com.xd.zt.service.model.ModelEditorService;
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

/**
 * Author:ykr
 * Date:2019/11/13
 * Description:
 * 模型编辑
 */
@Controller
@RequestMapping("/model")
public class ModelEditorController {
    @Autowired
    private ModelEditorService modelEditorService;
    @Autowired
    private ExperimentConfigService experimentConfigService;
    @Autowired
    private ExperimentDataService experimentDataService;
    @Autowired
    private AnalyseService analyseService;
    @RequestMapping("/modeleditor/{programmeid}")
    public ModelAndView modeleditor(Model model, @PathVariable("programmeid") Integer programmeid){

        /*根据programmeid查询流程*/
        ModelAndView modelAndView=new ModelAndView();
        String algorithmtype = "模型验证";
        List<Algorithm> typeAlgorithms =  experimentConfigService.showAlgorithmtype(algorithmtype);
       Integer experimentid= modelEditorService.queryExperimentIdByProgrammeId(programmeid);
      ExperimentConfig experimentConfig= modelEditorService.getModelFlowByProgrammeId(programmeid);
      String configflow=experimentConfig.getConfigflow();
        modelAndView.addObject("configflow",configflow);
            String params1 = experimentConfig.getParam();
        JSONArray paramsArray = JSON.parseArray(params1);
        String algorithmname = paramsArray.getJSONObject(0).getString("name");
        JSONObject params = paramsArray.getJSONObject(0).getJSONObject("params");
        model.addAttribute("typeAlgorithms",typeAlgorithms);
        String inputPath = params.getString("inputpath");
        String content = params.getString("content");
        String outputpath = params.getString("outputpath");
        model.addAttribute("inputPath",inputPath);
        model.addAttribute("content",content);
        model.addAttribute("outputpath",outputpath);
        model.addAttribute("experimentParams",params1);
        model.addAttribute("algorithmname",algorithmname);
        model.addAttribute("programmeid",programmeid);
      modelAndView.addObject("experimentConfig",experimentConfig);


        List<ExperimentData> experimentDatas = experimentConfigService.showExperimentData(experimentid);
        List<AnalyseResult> analyseResultList = new ArrayList<>();
        List<AnalyseCsv> analyseCsvList = new ArrayList<>();
        ExperimentModel experimentModel =experimentConfigService.showExperiment(experimentid);
        Integer analysemodeid =experimentModel.getAnalysemodeid();
        List<AnalyseModelProcess> allAnalyseModelProcesses = experimentConfigService.allAnalyseModelProcess(analysemodeid);
//        从analysemodeid到modelid(一对多)
        List<String> modelpaths = new ArrayList<>();
        List<String> modelPathnames = new ArrayList<>();
        for(int i=0; i<allAnalyseModelProcesses.size(); i++){
            int modelid = allAnalyseModelProcesses.get(i).getModelid();
            List<AnalyseInstance> analyseInstances = experimentConfigService.allAnalyseInstance(modelid);
            for (int j=0;j<analyseInstances.size();j++){
                int instanceid =analyseInstances.get(j).getModelinstanceid();
                List<AnalyseResult> analyseResults =  experimentConfigService.showAnalyseResuit(instanceid);
                List<AnalyseCsv> analyseCsvList1 = analyseService.selectCsvExit(instanceid);
                for (int k=0;k<analyseResults.size();k++){
                    analyseResultList.add(analyseResults.get(k));
                }
                for (int k=0;k<analyseCsvList1.size();k++){
                    analyseCsvList.add(analyseCsvList1.get(k));
                }
            }
        }

        List<DatamodelInfo> datamodelInfoList = experimentDataService.selectDataBao(experimentid);
        model.addAttribute("experimentDatas",experimentDatas);
//           model.addAttribute("datapaths",datapaths);
        model.addAttribute("experimentid",experimentid);
        model.addAttribute("analyseResultList",analyseResultList);
        model.addAttribute("analyseCsvList",analyseCsvList);
        model.addAttribute("datamodelInfoList",datamodelInfoList);

        modelAndView.setViewName("model/modeleditor");
       return modelAndView;
    }

    @RequestMapping("/updateProgrammeResult")
    @ResponseBody
    public Map<String,Object> saveDataAreaResult(@RequestBody String jsonObject1){
        JSONObject jsonObject=JSON.parseObject(jsonObject1);
        String programmeid = jsonObject.get("programmeid").toString();
        JSONArray analyzmodelJsonArr = jsonObject.getJSONArray("analyzmodelJsonArr");
     Programme programme= modelEditorService.getAllByProgrammeId(Integer.parseInt(programmeid));
     String programmepath=programme.getProgrammepath();
     JSONObject programmePathObject=JSONObject.parseObject(programmepath);
     programmePathObject.put("analyzmodel",analyzmodelJsonArr);
     String programmePathString=JSON.toJSONString(programmePathObject);
     modelEditorService.updateProgrammeByProgrammeId(programmePathString,programmeid);
     Map<String,Object> map=new HashMap<>();
     map.put("msg",1);
     return map;






 /*       ExperimentConfig experimentConfig = new ExperimentConfig();
        experimentConfig.setExperimentid(experimentid);

        experimentConfig.setConfigflow(flowchart);
        experimentConfig.setParam(analyzmodel);
        boolean msg =experimentConfigService.insertExperimentConfig(experimentConfig);*/
     /*   if (msg){
            return "保存成功";
        }else {
            return "保存失败";
        }*/
    }






}
