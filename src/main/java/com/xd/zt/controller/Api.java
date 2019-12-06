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
    public ApiResult getAllProgramme() {
        List<Programme> programmeList = modelService.selectAllModel();
        JSONArray programmeJsonArray = new JSONArray();
        for (int i = 0 ; i < programmeList.size(); i++){
            JSONObject programmeJson = new JSONObject();
            programmeJson.put("programmeId",programmeList.get(i).getProgrammeid());
            programmeJson.put("programmeName",programmeList.get(i).getProgrammename());
//            programmeJson.put("username",programmeList.get(i).getUsername());
            programmeJsonArray.add(i,programmeJson);
        }
        ApiResult apiResult = new ApiResult();
        apiResult.setDatas(programmeJsonArray);
        apiResult.setResp_code(0);
        apiResult.setResp_msg("返回成功");
        return apiResult;

    }

    @RequestMapping(value = "/getAllProgramme/{programmetype}", method = GET, produces = "application/json")
    public ApiResult getAllProgrammeByType(@PathVariable("programmetype")String programmetype) {
        List<Programme> programmeList = modelService.selectAllModelByType(programmetype);
        JSONArray programmeJsonArray = new JSONArray();
        for (int i = 0 ; i < programmeList.size(); i++){
        JSONObject programmeJson = new JSONObject();
        programmeJson.put("programmeId",programmeList.get(i).getProgrammeid());
        programmeJson.put("programmeName",programmeList.get(i).getProgrammename());
//        programmeJson.put("username",programmeList.get(i).getUsername());
        programmeJsonArray.add(i,programmeJson);
        }
        ApiResult apiResult = new ApiResult();
        apiResult.setDatas(programmeJsonArray);
        apiResult.setResp_code(0);
        apiResult.setResp_msg("返回成功");
        return apiResult;

    }

    @RequestMapping(value = "/getProgrammeById/{programmeId}", method = GET, produces = "application/json")
    public ApiResult getProgrammeById(@PathVariable("programmeId") Integer programmeId) {
        ApiResult apiResult = new ApiResult();
        try {
        Programme programme = modelService.selectProgrammeById(programmeId);
        JSONArray instanceArray = new JSONArray();
            JSONObject instanceJson = new JSONObject();
            instanceJson.put("programmeId",programme.getProgrammeid());
//            instanceJson.put("username",programme.getUsername());
            instanceJson.put("programmeName",programme.getProgrammename());
            instanceJson.put("programmePath",programme.getProgrammepath());
            instanceArray.add(instanceJson);

        apiResult.setDatas(instanceArray);
        apiResult.setResp_code(0);
        apiResult.setResp_msg("返回成功");
    }
        catch (Exception e){
        apiResult.setResp_code(1);
        apiResult.setResp_msg("工程不存在");
        apiResult.setDatas(null);
    }
        return apiResult;
    }



}
