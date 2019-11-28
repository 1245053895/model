package com.xd.zt.controller.dataManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xd.zt.domain.dataManage.UploadData;
import com.xd.zt.service.dataManager.OpenTsdbDataService;
import com.xd.zt.util.dataManager.GetOpenTsdb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/openTsdb")
public class OpenTsdbController {
    @Autowired
    private OpenTsdbDataService openTsdbDataService;

    @ResponseBody
    @RequestMapping("/getTsdbData")
    public Map<String,Object> getTsdbData(@RequestBody String jsonString){
        JSONObject jsonObject = JSON.parseObject(jsonString);
        Map map = new HashMap();
        String PointName = jsonObject.getString("PointName");
        String startTime = jsonObject.getString("startTime");
        String endTime = jsonObject.getString("endTime");
        List<UploadData> uploadDataList = GetOpenTsdb.getOpenTsdbDate(PointName,Integer.parseInt(startTime),Integer.parseInt(endTime));
        if (uploadDataList.size() != 0) {
            for (int i = 0; i < uploadDataList.size(); i++) {
                openTsdbDataService.insertOpenTsdbData(uploadDataList.get(i));
            }
            map.put("resp_msg", "接入成功");
        }
        else {
            map.put("resp_msg", "接入失败,请检查输入是否正确");
        }
        return map;
    }

}
