package com.xd.zt.controller.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.xd.zt.domain.analyse.Algorithm;
import com.xd.zt.domain.data.DatamodelBlock;
import com.xd.zt.domain.data.DatamodelInfo;
import com.xd.zt.service.data.DataAreaService;
import com.xd.zt.service.data.DataBlockService;
import com.xd.zt.service.data.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/data")
@Controller
public class DataBlockController {
    @Autowired
    private DataBlockService dataBlockService;
    @Autowired
    private SourceService sourceService;

    @Autowired
    private DataAreaService dataAreaService;

    @RequestMapping("/datablockbuild/{id}")
    public ModelAndView datamodelblock(Model model, @PathVariable("id") Integer modelid) {
        List<DatamodelBlock> datamodelBlockList = dataBlockService.selectBlockById(modelid);
        model.addAttribute("datamodelBlockList", datamodelBlockList);
        for (DatamodelBlock datamodelBlock:datamodelBlockList){
            String areaName = dataBlockService.selectAreaName(datamodelBlock.getBlockid());
            datamodelBlock.setAreaname(areaName);
        }
        model.addAttribute("modelid", modelid);
        return new ModelAndView("data/datablockbuild", "Modelmodel", model);
    }

    @RequestMapping("/datablockcreate/{id}")
    public ModelAndView datablockcreate(Model model, @PathVariable("id") Integer modelid) {
        model.addAttribute("modelid", modelid);
        List<DatamodelInfo> datamodelInfoList = dataBlockService.selectDataAreaResultById(modelid);
        model.addAttribute("datamodelInfoList", datamodelInfoList);
        List<Algorithm> algorithmList = sourceService.selectAlgorithm();
        model.addAttribute("algorithmList", algorithmList);
        return new ModelAndView("data/datablockcreate", "Modelmodel", model);
    }

    @RequestMapping("/saveDataBlockResult")
    @ResponseBody
    public String saveDataBlockResult(@RequestBody JSONObject jsonObject) {
        String blockname = jsonObject.get("datablockresult").toString();
        String modelid = jsonObject.get("modelid").toString();
        String flowchart = jsonObject.get("flowchart").toString();
        String dataresultid = jsonObject.get("dataresultid").toString();
        String dataarea = dataBlockService.selectAreaId(dataresultid);
        dataBlockService.saveDataBlockResult(modelid, blockname, flowchart,dataarea);
        return modelid;
    }

    @RequestMapping("/deleteblock")
    @ResponseBody
    public String deleteblock(@RequestBody String jsonData) {
        JSONObject jsonObject = JSON.parseObject(jsonData);
        String blockid = jsonObject.getString("blockid");
        dataBlockService.deleteblock(blockid);
        return blockid;
    }

    /*数据块回显，datamodel_info表 */
    @GetMapping(value = "/blockShow/{blockid}")  //得到数据块中id
    public ModelAndView blockShow(Model model, @PathVariable("blockid") Integer blockid) {
        /*Iterable<DatamodelSource> datamodelSourceList= fileRepository.findAll();*/
        /*得到处理后的数据块的表名和地址*/
        ModelAndView modelAndView = new ModelAndView();
        List<DatamodelInfo> datamodelInfoList = sourceService.selectBlockName(blockid);
        DatamodelBlock datamodelBlockList = dataBlockService.selectCreateProcess(blockid);
        modelAndView.setViewName("data/blockShow");
        modelAndView.addObject("datamodelInfo", datamodelInfoList);
        modelAndView.addObject("datamodelBlockList", datamodelBlockList);
        modelAndView.addObject("blockid", blockid);
        return modelAndView;
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
        String dataaddr = params.getString("path");
      Integer blockid=  dataBlockService.maxBlockId();
        Integer areaid= dataBlockService.getAreaIdByBlockId(blockid);
        DatamodelInfo datamodelInfo=new DatamodelInfo();
        datamodelInfo.setDataresultname(modelinstancename);
        datamodelInfo.setDataarea(areaid);
        datamodelInfo.setModelid(Integer.parseInt(modelid));
        datamodelInfo.setDataaddr(dataaddr);
        datamodelInfo.setBlockid(blockid);
        dataBlockService.processBlockInfo(datamodelInfo);
        //System.out.println(analyzmodel);
        String modelinstanceid = dataAreaService.selectInstanceName(modelinstancename);
        if(modelinstanceid==""||modelinstanceid==null){
            dataBlockService.BlockInstance(modelid,modelinstancename,analyzmodel,blockid.toString());


            map.put("code",1);
        }
        else {
            map.put("code",0);
        }
        return map;
    }










}
