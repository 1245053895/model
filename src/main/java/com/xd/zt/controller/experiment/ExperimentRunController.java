package com.xd.zt.controller.experiment;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xd.zt.domain.analyse.AnalyseCsv;
import com.xd.zt.domain.experiment.ExperimentConfig;
import com.xd.zt.domain.experiment.ExperimentResult;
import com.xd.zt.service.experiment.ExperimentRunService;
import com.xd.zt.util.analyse.FindLinuxDirectory;
import com.xd.zt.util.analyse.HttpCientPost;
import com.xd.zt.util.analyse.HttpUtil;
import org.apache.tomcat.jni.Directory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/experiment")
@Controller
public class ExperimentRunController {
    @Autowired
    private ExperimentRunService experimentRunService;

    @ResponseBody
    @RequestMapping("/experimentRun")
    public Map<String,Object>  experimentRun(@RequestBody JSONObject jsonObject){
        Map map = new HashMap();

        String instantData = jsonObject.get("instantData").toString();
        String experimentconfigid = jsonObject.get("modelInstanceId").toString();

        boolean status = false;
        if (instantData == "false"){status = false;}
        else {status = true;}
        //生成json文件
        String analyzmodelString = jsonObject.get("analyzmodel").toString();
        JSONArray analyzmodel = JSONArray.parseArray(analyzmodelString);
        jsonObject.put("analyzmodel",analyzmodel);
        jsonObject.put("instantData",status);
        String jsonString= JSON.toJSONString(jsonObject);
        try {
            String result = HttpCientPost.restPost("http://10.101.201.174:8000/tasks/", jsonString);
            System.out.printf(result);
            JSON resultjson = JSON.parseObject(result);
            if (((JSONObject) resultjson).getBoolean("success")) {
                ExperimentConfig experimentConfig = experimentRunService.selectConfig(experimentconfigid);
                Integer experimentid = experimentConfig.getExperimentid();
                String taskId = ((JSONObject) resultjson).getString("taskId");

                //遍历获得所有结果的csv路径
                String Directory = FindLinuxDirectory.FindDirectory("10.101.201.174",22,"root","/zt/IA","find /var/data/celery/output/"+taskId+"/output/ -type f -name '*.csv'");
                System.out.printf(Directory);
                if (Directory == "" || Directory == null || Directory == "出现错误"){
                }
                else {
                    String[] DirectoryList = Directory.split("\n");
                    List<ExperimentResult> experimentResultList = experimentRunService.selectResult(Integer.parseInt(experimentconfigid));
                    if (experimentResultList == null) {
                        for (int i = 0; i < DirectoryList.length; i++) {
                            String[] FileDirectory = DirectoryList[i].split("/");
                            String fileName = FileDirectory[FileDirectory.length - 1];
                            ExperimentResult experimentResult = new ExperimentResult();
                            experimentResult.setResultname(fileName);
                            experimentResult.setResultpath(DirectoryList[i]);
                            experimentResult.setExperimentcongfigid(Integer.parseInt(experimentconfigid));
                            experimentResult.setExperimentid(experimentid);
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            experimentResult.setDatetime(df.format(new Date()));

                            experimentRunService.saveResult(experimentResult);
                            System.out.printf("保存成功");
                        }
                    } else {
                        experimentRunService.deleteResult(Integer.parseInt(experimentconfigid));
                        for (int i = 0; i < DirectoryList.length; i++) {
                            String[] FileDirectory = DirectoryList[i].split("/");
                            String fileName = FileDirectory[FileDirectory.length - 1];
                            ExperimentResult experimentResult = new ExperimentResult();
                            experimentResult.setResultname(fileName);
                            experimentResult.setResultpath(DirectoryList[i]);
                            experimentResult.setExperimentcongfigid(Integer.parseInt(experimentconfigid));
                            experimentResult.setExperimentid(experimentid);
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            experimentResult.setDatetime(df.format(new Date()));
                            experimentRunService.saveResult(experimentResult);
                            System.out.printf("保存成功");
                        }
                    }
                }

                map.put("data",((JSONObject) resultjson).getString("datas"));
               return  map;
            }else {
                map.put("data","运行失败");
                return map;
            }
        }
        catch (Exception e){
            map.put("data","运行失败");
            return map;
        }
    }

    @ResponseBody
    @RequestMapping("/configDelete")
    public Map<String,Object> configDelete(@RequestBody String jsonObject){
        Map map = new HashMap();
        JSONObject jsonObject1 = JSON.parseObject(jsonObject);
        Integer instantid = jsonObject1.getInteger("instantid");
        experimentRunService.deleteConfig(instantid);
        map.put("code",1);
        return map;
    }
}
