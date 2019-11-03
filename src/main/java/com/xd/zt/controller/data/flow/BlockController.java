package com.xd.zt.controller.data.flow;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xd.zt.domain.analyse.Algorithm;
import com.xd.zt.domain.data.DatamodelInfo;
import com.xd.zt.service.data.DataAreaService;
import com.xd.zt.service.data.DataBlockService;
import com.xd.zt.service.data.SourceService;
import com.xd.zt.util.analyse.HttpCientPost;
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

import java.util.*;

@RequestMapping("/data")
@Controller
public class BlockController {
    @Autowired
    private DataBlockService dataBlockService;
    @Autowired
    private SourceService sourceService;

    @Autowired
    private DataAreaService dataAreaService;
    @RequestMapping("/datablockcreate/{id}")
    public ModelAndView datablockcreate(Model model, @PathVariable("id") Integer modelid) {
        model.addAttribute("modelid", modelid);
        List<DatamodelInfo> datamodelInfoList = dataBlockService.selectDataAreaResultById(modelid);
        model.addAttribute("datamodelInfoList", datamodelInfoList);
        List<Algorithm> algorithmList = sourceService.selectAlgorithm();
        List<Algorithm> algorithmList1 = new ArrayList<>();
        for (int i = 0 ; i < algorithmList.size(); i++){
            String type = algorithmList.get(i).getAlgorithmtype();
            if ("特征工程".equals(type)){
                int j = 0;
                algorithmList1.add(j,algorithmList.get(i));
                j++;
            }
        }
        model.addAttribute("algorithmList", algorithmList1);
        return new ModelAndView("data/datablockcreate", "Modelmodel", model);
    }

    @ResponseBody
    @RequestMapping("/saveBlockExample")
    public Map<String,Object> saveBlockExample(@RequestBody JSONObject jsonObject){
        Map<String,Object> map = new HashMap<>();
        String modelid = jsonObject.get("modelid").toString();
        String modelinstancename = jsonObject.get("submitName").toString();
        String analyzmodel = jsonObject.get("analyzmodel").toString();
        JSONArray jsonArray = JSON.parseArray(analyzmodel);
        JSONObject params = jsonArray.getJSONObject(0).getJSONObject("params");
//        String dataaddr = params.getString("path");

        Integer blockid=  dataBlockService.maxBlockId();
        Integer areaid= dataBlockService.getAreaIdByBlockId(blockid);


//        String modelinstanceid = dataAreaService.selectInstanceName(modelinstancename);
//        if(modelinstanceid==""||modelinstanceid==null){
            dataBlockService.BlockInstance(modelid,modelinstancename,analyzmodel,blockid.toString());

//            String modelinstanceid1 = dataAreaService.selectInstanceName(modelinstancename);
            JSONObject modelinstance = new JSONObject();
            modelinstance.put("username","data");
            modelinstance.put("modelInstanceId",modelid);
            modelinstance.put("instantData",true);
            modelinstance.put("analyzmodel",jsonArray);

            String jsonString= JSON.toJSONString(modelinstance);
            HttpUtil httpUtil = new HttpUtil();
            try {
//              String result =  HttpCientPost.restPost("http://120.24.157.214:8000/tasks/",jsonString);
                String result =  HttpCientPost.restPost("http://10.101.201.174:8000/tasks/",jsonString);
                JSON resultjson = JSON.parseObject(result);
                System.out.printf(result);
                if(((JSONObject) resultjson).getBoolean("success")) {
                    String taskId = ((JSONObject) resultjson).getString("taskId");

                    JSONObject outputpath = params.getJSONObject("outputpath");

                    String[] filenames = JsonKeyToStringList.translate(outputpath);

                    for (int i = 0; i < outputpath.size(); i++) {

                        DatamodelInfo datamodelInfo = new DatamodelInfo();
                        datamodelInfo.setDataresultname(filenames[i]);
                        datamodelInfo.setDataarea(areaid);
                        datamodelInfo.setModelid(Integer.parseInt(modelid));

                        datamodelInfo.setDataaddr("/var/data/celery/output/" + taskId + "/output/" + filenames[i]);

                        datamodelInfo.setBlockid(blockid);
                        dataBlockService.processBlockInfo(datamodelInfo);
                        map.put("code",1);
                    }
                }
                else {
                    map.put("code",2);
                }
                //System.out.println(analyzmodel);
            }catch (Exception e){
                System.out.println(e.getMessage());
                map.put("code",2);
                return map;
            }
//        }
//        else {
//            map.put("code",1);
//        }
        return map;
    }
}
