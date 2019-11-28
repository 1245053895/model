package com.xd.zt.controller.dataManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xd.zt.util.analyse.HttpClientGet;
import com.xd.zt.util.data.JsonKeyToStringList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/dataManager")
public class Neo4JController {


    @RequestMapping("/Neo4JRun")
    public ModelAndView Neo4JRun(Model model){
        return new ModelAndView("dataManager/TzgsNeo4j","Modelmodel",model);
    }

    @ResponseBody
    @RequestMapping("/RunNeo4J")
    public Map<String,Object> RunNeo4J(@RequestBody String jsonString){
        Map map = new HashMap();
        JSONObject jsonObject = JSON.parseObject(jsonString);
        String[] Keys = JsonKeyToStringList.translateKey(jsonObject);
        String[] Values = JsonKeyToStringList.translate(jsonObject);
        String params = new String();
        for (int i = 0 ; i < Keys.length ; i++){
            params = params+"&"+Keys[i]+"="+Values[i];
        }
        String url = "http://10.101.201.174:7002/api/v1/algo/tzgs_zstp/graphyAssess?"+params;

        try{
            String result = HttpClientGet.restGet(url,null);
            JSONObject resultJson = JSON.parseObject(result);

            map.put("resp_code",resultJson.getInteger("resp_code"));
            map.put("resp_msg",resultJson.getString("resp_msg"));
            map.put("datas",resultJson.getString("datas"));
        }
        catch (Exception e){
            map.put("resp_code",30);
            map.put("resp_msg",e.toString());
        }
        return map;
    }
}
