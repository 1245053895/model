package com.xd.zt.controller;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xd.zt.domain.ApiResult;
import com.xd.zt.domain.analyse.AnalyseInstance;
import com.xd.zt.domain.model.Programme;
import com.xd.zt.service.ApiService;
import com.xd.zt.service.model.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
@RequestMapping("/restApi")
public class Api {

    @Autowired
    private ModelService modelService;
    @Autowired
    private ApiService apiService;

    @RequestMapping(value = "/getAllProgramme", method = GET, produces = "application/json")
    public ApiResult saveArticle() {
        List<Programme> programmeList = modelService.selectAllModel();
        JSONArray programmeJsonArray = new JSONArray();
        for (int i = 0 ; i < programmeList.size(); i++){
        JSONObject programmeJson = new JSONObject();
        programmeJson.put("programmeId",programmeList.get(i).getProgrammeid());
        programmeJson.put("programmeName",programmeList.get(i).getProgrammename());
        programmeJson.put("username",programmeList.get(i).getUsername());
        programmeJsonArray.add(i,programmeJson);
        }
        ApiResult apiResult = new ApiResult();
        apiResult.setData(programmeJsonArray);
        apiResult.setCode(0);
        apiResult.setMessange("返回成功");
        return apiResult;

    }

    @RequestMapping(value = "/getProgrammeById/{programmeId}", method = GET, produces = "application/json")
    public ApiResult getProgrammeById(@PathVariable("programmeId") Integer programmeId) {
        ApiResult apiResult = new ApiResult();
        try {
        List<AnalyseInstance> analyseInstanceList = apiService.findInstanceByProgrammeId(programmeId);
        JSONArray instanceArray = new JSONArray();
        for (int i = 0 ; i < analyseInstanceList.size(); i++){
            JSONObject instanceJson = new JSONObject();
            instanceJson.put("instanceId",analyseInstanceList.get(i).getModelinstanceid());
            instanceJson.put("instanceName",analyseInstanceList.get(i).getModelinstancename());
            instanceArray.add(i,instanceJson);
        }
        apiResult.setData(instanceArray);
        apiResult.setCode(0);
        apiResult.setMessange("返回成功");
    }
        catch (Exception e){
        apiResult.setCode(1);
        apiResult.setMessange("工程不存在");
        apiResult.setData(null);
    }
        return apiResult;
    }

    @RequestMapping(value = "/getInstanceById/{instanceId}", method = GET, produces = "application/json")
    public ApiResult getInstanceById(@PathVariable("instanceId") Integer instanceId) {
        ApiResult apiResult = new ApiResult();
        try {
            AnalyseInstance analyseInstance = apiService.selectInstanceById(instanceId);
            JSONArray parameters = JSONArray.parseArray(analyseInstance.getParameters());
            JSONObject modelinstance = new JSONObject();
            modelinstance.put("username", "name");
            modelinstance.put("modelInstanceId", instanceId);
            modelinstance.put("instantData", false);
            modelinstance.put("analyzmodel", parameters);

            apiResult.setData(modelinstance);
            apiResult.setCode(0);
            apiResult.setMessange("返回成功");
        }
        catch (Exception e){
            apiResult.setCode(1);
            apiResult.setMessange("模型不存在");
            apiResult.setData(null);
        }
        return apiResult;
    }

}
