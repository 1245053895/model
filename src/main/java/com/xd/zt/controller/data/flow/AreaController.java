package com.xd.zt.controller.data.flow;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xd.zt.domain.data.DatamodelArea;
import com.xd.zt.domain.data.DatamodelInfo;
import com.xd.zt.repository.data.FileRepository;
import com.xd.zt.service.data.DataAreaService;
import com.xd.zt.service.data.FileService;
import com.xd.zt.service.data.FlowService;
import com.xd.zt.service.data.SourceService;
import com.xd.zt.util.analyse.HttpCientPost;
import com.xd.zt.util.analyse.HttpUtil;
import com.xd.zt.util.data.JsonKeyToStringList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@RequestMapping("/data")
@Controller
public class AreaController {
    @Autowired
    private SourceService sourceService;
    @Autowired
    private FlowService flowService;
    @Autowired
    private DataAreaService dataAreaService;
    @Autowired
    private FileService fileService;
    @Autowired
    private FileRepository fileRepository;


    @RequestMapping("/saveDataAreaResult")
    @ResponseBody
    public String saveDataAreaResult(@RequestBody JSONObject jsonObject){
        String blockname = jsonObject.get("dataarearesult").toString();
        String modelid = jsonObject.get("modelid").toString();
        String areaid = String.valueOf(dataAreaService.selectAreaid());
        String flowchart = jsonObject.get("flowchart").toString();
        String areaname=blockname;
        dataAreaService.saveDataAreaResult(areaname,blockname,flowchart,areaid);
        return modelid;
    }

    @ResponseBody
    @RequestMapping("/saveAreaExample")
    public Map<String,Object> saveAreaExample(@RequestBody JSONObject jsonObject){
        Map<String,Object> map = new HashMap<>();
        String modelid = jsonObject.get("modelid").toString();
        String modelinstancename = jsonObject.get("submitName").toString();
        String analyzmodel = jsonObject.get("analyzmodel").toString();
        JSONArray jsonArray = JSON.parseArray(analyzmodel); //字符串转换为json数组
        JSONObject params = jsonArray.getJSONObject(0).getJSONObject("params"); //取json数组对象
//        String dataaddr = params.getString("input");

        Integer areaid= dataAreaService.maxAreaId();
        Integer datalink=  dataAreaService.getLinkIdByAreaid(areaid);

        //System.out.println(analyzmodel);
//        String modelinstanceid = dataAreaService.selectInstanceName(modelinstancename);
//        if(modelinstanceid==""||modelinstanceid==null){
            dataAreaService.insertExample(modelid,modelinstancename,analyzmodel,areaid.toString());
            map.put("code",1);
//            String modelinstanceid1 = dataAreaService.selectInstanceName(modelinstancename);
            JSONObject modelinstance = new JSONObject();
            modelinstance.put("username","data");
            modelinstance.put("modelInstanceId",modelid);
            modelinstance.put("instantData",false);
            modelinstance.put("analyzmodel",jsonArray);

            String jsonString= JSON.toJSONString(modelinstance);
            HttpUtil httpUtil = new HttpUtil();
            try {
//              String result =  HttpCientPost.restPost("http://120.24.157.214:8000/tasks/",jsonString);
                String result =  HttpCientPost.restPost("http://10.101.201.174:8000/tasks/",jsonString);
                JSON resultjson = JSON.parseObject(result);

                String taskId = ((JSONObject) resultjson).getString("taskId");

                JSONObject outputpath = params.getJSONObject("outputpath");

                String[] filenames = JsonKeyToStringList.translate(outputpath);

                for (int i = 0 ; i < outputpath.size(); i++ ) {

                    DatamodelInfo datamodelInfo = new DatamodelInfo();
                    datamodelInfo.setDataresultname(filenames[i]);
                    datamodelInfo.setDatalink(datalink);
                    datamodelInfo.setDataarea(areaid);
                    datamodelInfo.setModelid(Integer.parseInt(modelid));

                    datamodelInfo.setDataaddr("/var/data/celery/output/"+taskId+"/output/"+filenames[i]);

                    dataAreaService.processAreaInfo(datamodelInfo);
                    String areaname = modelinstancename;
                    dataAreaService.updateAreaByAreaId(areaname, areaid.toString());
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
                return null;
            }

        return map;
    }

    @RequestMapping("/deleteArea")
    @ResponseBody
    public String deleteArea(@RequestBody String jsonData) {
        JSONObject jsonObject = JSON.parseObject(jsonData);
        String areaid1 = jsonObject.getString("areaid");
        Integer areaid = Integer.parseInt(areaid1);

        DatamodelArea datamodelArea = sourceService.modelIdByAreaid(areaid);
        Integer processid = datamodelArea.getProcessid();
        flowService.dardeleteProcessName(processid);
    /*
        DatamodelArea xin = sourceService.darprocessidTomodeid(processid);*/
        /*  int modelid =xin.getModeid();*/
        Integer modelid = datamodelArea.getModelid();
        sourceService.deleteArea(areaid);
        return areaid1;
    }

    /*删除数据链*/
    @ResponseBody
    @RequestMapping("/deletelink")
    public Map<String, Object> deletelink(@RequestBody String jsonData) {
        JSONObject jsonObject = JSON.parseObject(jsonData);
        String linkid = jsonObject.getString("linkid");
        sourceService.deleteLink(Integer.valueOf(linkid));
        Map<String, Object> map = new HashMap<>();
        map.put("code", 1);
        return map;
    }
}
