package com.xd.zt.controller.data.flow;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xd.zt.domain.analyse.Algorithm;
import com.xd.zt.domain.data.DatamodelInfo;
import com.xd.zt.domain.data.DatamodelSource;
import com.xd.zt.service.data.DataAreaService;
import com.xd.zt.service.data.DataBlockService;
import com.xd.zt.service.data.SourceService;
import com.xd.zt.util.analyse.FindLinuxDirectory;
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
        List<DatamodelSource> datamodelSourceList = sourceService.datamodelSourceByModeId(modelid.toString(),"0");
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
        model.addAttribute("datamodelSourceList",datamodelSourceList);
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
        Integer blockid= Integer.parseInt(jsonObject.get("blockid").toString());
//        Integer blockid=  dataBlockService.maxBlockId();
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
                map.put("resp_code",((JSONObject) resultjson).getInteger("resp_code"));
                map.put("resp_msg",((JSONObject) resultjson).getString("resp_msg"));


                System.out.printf(result);
                if(((JSONObject) resultjson).getInteger("resp_code") == 0) {
                    String taskId = ((JSONObject) resultjson).getString("taskId");

                    JSONObject outputpath = params.getJSONObject("outputpath");

                    String[] filenames = JsonKeyToStringList.translate(outputpath);

                    for (int i = 0; i < outputpath.size(); i++) {
                        if (!filenames[i].equals("modelPaths") ) {
                            DatamodelInfo datamodelInfo = new DatamodelInfo();
                            datamodelInfo.setDataresultname(filenames[i]);
                            datamodelInfo.setDataarea(areaid);
                            datamodelInfo.setModelid(Integer.parseInt(modelid));

                            datamodelInfo.setDataaddr("/var/data/celery/output/" + taskId + "/output/" + filenames[i]);

                            datamodelInfo.setBlockid(blockid);
                            dataBlockService.processBlockInfo(datamodelInfo);
                        }
                        else {
                            String Directory = FindLinuxDirectory.FindDirectory("10.101.201.174",22,"root","/zt/IA","find /var/data/celery/output/"+taskId+"/output/ -type f -name '*.csv'");
                            System.out.printf(Directory);
                            if (Directory == "" || Directory == null || Directory == "出现错误"){
                            }
                            else {
                                String[] DirectoryList = Directory.split("\n");
                                for (int j = 0; j < DirectoryList.length; j++) {
                                    String[] FileDirectory = DirectoryList[j].split("/");
                                    String fileName = FileDirectory[FileDirectory.length-1];
                                    DatamodelInfo datamodelInfo = new DatamodelInfo();
                                    datamodelInfo.setDataresultname(fileName);
                                    datamodelInfo.setDataarea(areaid);
                                    datamodelInfo.setModelid(Integer.parseInt(modelid));
                                    datamodelInfo.setDataaddr(DirectoryList[j]);
                                    datamodelInfo.setBlockid(blockid);
                                    dataBlockService.processBlockInfo(datamodelInfo);

                            }
                            }
                        }
                    }
                    return map;
                }
                else {
                    return map;
                }
                //System.out.println(analyzmodel);
            }catch (Exception e){
                System.out.println(e.getMessage());
                map.put("resp_msg",e.toString());
                return map;
            }
    }

    @RequestMapping("/databaselist/{id}")
    public ModelAndView databaselist(Model model,@PathVariable("id")Integer datamodelid){

        List<DatamodelSource> datamodelSourceList = sourceService.datamodelSourceByModeId(datamodelid.toString(),"0");
        List<DatamodelInfo> datamodelInfoList = sourceService.selectInfoList(datamodelid);

        model.addAttribute("datamodelSourceList",datamodelSourceList);
        model.addAttribute("datamodelInfoList",datamodelInfoList);

        return new ModelAndView("data/databaselist","Modelmodel",model);
    }
}
