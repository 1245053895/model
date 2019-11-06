package com.xd.zt.controller.experiment;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xd.zt.util.analyse.HttpCientPost;
import com.xd.zt.util.analyse.HttpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/experiment")
@Controller
public class ExperimentRunController {
    @ResponseBody
    @RequestMapping("/experimentRun")
    public Map<String,Object>  experimentRun(@RequestBody JSONObject jsonObject){
        Map map = new HashMap();

        String instantData = jsonObject.get("instantData").toString();
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

}
