package com.xd.zt.controller.experiment;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xd.zt.domain.analyse.*;
import com.xd.zt.domain.business.BusinessModel;
import com.xd.zt.domain.business.BusinessQuestion;
import com.xd.zt.domain.data.DatamodelInfo;
import com.xd.zt.domain.experiment.ExperimentConfig;
import com.xd.zt.domain.experiment.ExperimentData;
import com.xd.zt.domain.experiment.ExperimentModel;
import com.xd.zt.domain.experiment.ExperimentTraintest;
import com.xd.zt.service.analyse.AnalyseService;
import com.xd.zt.service.experiment.ExperimentConfigService;
import com.xd.zt.service.experiment.ExperimentDataService;
import com.xd.zt.service.experiment.ExperimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/experiment")
public class ExperimentConfigController {

    @Autowired
    private ExperimentConfigService experimentConfigService;
    @Autowired
    private ExperimentDataService experimentDataService;
    @Autowired
    private AnalyseService analyseService;


    @RequestMapping("/reviewModelConfiguration/{experimentid}")
    public ModelAndView datalead(Model model,@PathVariable("experimentid") Integer experimentid) {
        List<ExperimentConfig> experimentConfigList= experimentConfigService.findAllByExperimentId(experimentid);
        model.addAttribute("experimentid",experimentid);
        model.addAttribute("experimentConfigList",experimentConfigList);
        return new ModelAndView("experiment/reviewModelConfiguration", "modelModel", model);
    }

    @RequestMapping("/modelConfigurationView/{experimentid}")
    public ModelAndView dataReview(Model model,@PathVariable("experimentid") Integer id) {
        String algorithmtype = "模型验证";
        List<Algorithm> typeAlgorithms =  experimentConfigService.showAlgorithmtype(algorithmtype);
        ExperimentConfig experimentConfig =experimentConfigService.showExperimentConfig(id);
        String configflow = experimentConfig.getConfigflow();
        int experimentid = experimentConfig.getExperimentid();
        String params1 = experimentConfig.getParam();
        JSONArray paramsArray = JSON.parseArray(params1);
        String algorithmname = paramsArray.getJSONObject(0).getString("name");
        JSONObject params = paramsArray.getJSONObject(0).getJSONObject("params");

        String inputPath = params.getString("inputpath");
        String content = params.getString("content");
        String outputpath = params.getString("outputpath");
        model.addAttribute("id",id);
        model.addAttribute("typeAlgorithms",typeAlgorithms);
        model.addAttribute("configflow",configflow);
        model.addAttribute("experimentid",experimentid);
        model.addAttribute("inputPath",inputPath);
        model.addAttribute("content",content);
        model.addAttribute("outputpath",outputpath);
        model.addAttribute("experimentParams",params1);
        return new ModelAndView("experiment/modelConfigurationView", "modelModel", model);
    }



    @ResponseBody
    @RequestMapping("/saveprogramme")
    @Transactional
    public String saveBlockExample(@RequestBody JSONObject jsonObject){
        String programmename = jsonObject.get("programmename").toString();
        String programmetype = jsonObject.get("programmetype").toString();
        String programmedescribe = jsonObject.get("programmedescribe").toString();
        String programmepath = jsonObject.get("programmepath").toString();
        String xxx = jsonObject.get("id").toString();
        Integer id = Integer.parseInt(xxx);
        String username ="username";
        String editTime =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString();
        Programme programme = new Programme();
            programme.setProgrammetime(editTime);
            programme.setProgrammename(programmename);
            programme.setProgrammedescribe(programmedescribe);
            programme.setProgrammetype(programmetype);
            programme.setProgrammepath(programmepath);
            programme.setUsername(username);
            experimentConfigService.insertProgrammence(programme);
            Integer programmeid = programme.getProgrammeid();
            experimentConfigService.updataExperimentmodel(programmeid,id);
//            ExperimentModel experimentModel = experimentConfigService.showExperiment(id);
//            Integer modeid = experimentModel.getAnalysemodeid();
//            experimentConfigService.updataAnalysemodel(programmeid,modeid);
//            AnalyseModel analyseModel = experimentConfigService.showAnalyseModel(modeid);
//            Integer questionid = analyseModel.getQuestionid();
//            BusinessQuestion businessQuestion =experimentConfigService.showBusinessQuestion(questionid);
//            Integer businessid = businessQuestion.getBusinessid();
//            experimentConfigService.updataBusinessmodel(programmeid,businessid);
//            BusinessModel businessModel = experimentConfigService.showBusinessModel(businessid);
//            Integer processid =businessModel.getProcessid();
//            System.out.println(processid);
               return "xxx";
    }







    @RequestMapping("/modelConfiguration/{experimentid}")
    public ModelAndView modelConfiguration(Model model,@PathVariable("experimentid") Integer experimentid){
//          查算法表中的算法
        String algorithmtype = "模型验证";
        List<Algorithm> typeAlgorithms =  experimentConfigService.showAlgorithmtype(algorithmtype);
        List<ExperimentData> experimentDatas = experimentConfigService.showExperimentData(experimentid);
        List<AnalyseResult> analyseResultList = new ArrayList<>();
        List<AnalyseCsv> analyseCsvList = new ArrayList<>();
//        List<String> datanames =new ArrayList<>();
//        List<String> datapaths =new ArrayList<>();
//        for(int m =0;m<experimentDatas.size();m++){
//            datanames.add(experimentDatas.get(m).getDataname());
//            datapaths.add(experimentDatas.get(m).getDatapath());
//        }
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
           model.addAttribute("typeAlgorithms",typeAlgorithms);
           model.addAttribute("analyseResultList",analyseResultList);
            model.addAttribute("analyseCsvList",analyseCsvList);
           model.addAttribute("datamodelInfoList",datamodelInfoList);

        return new ModelAndView("experiment/modelConfiguration","Modelmodel",model) ;
    }

    @ResponseBody
    @RequestMapping("/selectQuestion")
    public Map<String, Object> selectQuestion(@RequestParam("modelid") Integer modelid){
        AnalyseModelProcess analyseModelProcess = experimentConfigService.showAnalyseModelProcessx(modelid);
        String modelProcess = analyseModelProcess.getModelprocess();
        String[] aa = modelProcess.split(";");
        String first = aa[0];
        String trainname =  aa[1];
        String last = aa[2];
        String algorithmname = aa[1];
        Algorithm algorithm = experimentConfigService.showAlgorithm(algorithmname);

        String algorithminput =  algorithm.getAlgorithmparamsinput();
        String algorithmcontent =  algorithm.getAlgorithmparamscontent();
        String algorithmoutput =  algorithm.getAlgorithmparamsoutput();

        ExperimentTraintest experimentTraintest = experimentConfigService.showExperimentTaintest(trainname);
        String testname = experimentTraintest.getTestname();
        String newModelProcess = first+";"+testname+";"+last;
        System.out.println(newModelProcess);
        Map<String,Object> map = new HashMap<>();
        map.put("newModelProcess",newModelProcess);

        map.put("algorithminput",algorithminput);
        map.put("algorithmcontent",algorithmcontent);
        map.put("algorithmoutput",algorithmoutput);
        return map;
    }

    @ResponseBody
    @PostMapping(value = "/savedata/",consumes = "application/json;charset=utf-8")
    public Map<String,Object> saveExperimentConfig(@RequestBody JSONObject jsonParam){
        String sceneid = jsonParam.get("formdata").toString();
        String modelid = jsonParam.get("modelid").toString();
        Map<String,Object> map = new HashMap<>();
        return map;
    }

    @RequestMapping("/saveBlockResult")
    @ResponseBody
    public String saveDataAreaResult(@RequestBody JSONObject jsonObject){
        String analyzmodel = jsonObject.get("analyzmodel").toString();
        String datablockname = jsonObject.get("datablockname").toString();
        String id = jsonObject.get("experimentid").toString();
        int experimentid = Integer.parseInt(id);
        String flowchart = jsonObject.get("experiment").toString();
       ExperimentConfig experimentConfig = new ExperimentConfig();
       experimentConfig.setExperimentid(experimentid);
       experimentConfig.setConfigname(datablockname);
       experimentConfig.setConfigflow(flowchart);
       experimentConfig.setParam(analyzmodel);
       boolean msg =experimentConfigService.insertExperimentConfig(experimentConfig);
      if (msg){
          return "保存成功";
      }else {
          return "保存失败";
      }
    }


}
