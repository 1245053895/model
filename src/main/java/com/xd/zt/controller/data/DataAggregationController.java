package com.xd.zt.controller.data;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.xd.zt.domain.data.DatamodelBao;
import com.xd.zt.domain.data.DatamodelInfo;
import com.xd.zt.domain.data.DatamodelJi;
import com.xd.zt.domain.data.DatamodelName;
import com.xd.zt.service.data.DataAggregationService;
import com.xd.zt.service.data.FileService;
import com.xd.zt.service.data.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RequestMapping("/data")
@Controller

public class DataAggregationController {

    @Autowired
   private DataAggregationService dataAggregationService;
    @Resource
    private SourceService sourceService;
    @Resource
    private FileService fileService;

    @RequestMapping("/saveDataPack")
    @ResponseBody
    public String saveDataPack(@RequestBody String jsonData) {
        JSONObject jsonObject = JSON.parseObject(jsonData);
        String modelid = jsonObject.getString("modelid");
        String jitype = jsonObject.getString("selectCollection");
        String jiname = jsonObject.getString("divideName");
        String baoid=jsonObject.getString("baoid");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        /// new Date()为获取当前系统时间
        String jitime=df.format(new Date());
        System.out.println(baoid);
        dataAggregationService.saveDataPack(jiname,jitype,jitime,baoid,modelid);
        return jsonData;
    }


    /*完成保存场景名*/

    @ResponseBody
    @RequestMapping("/saveModelName")
    public String saveModelName(@RequestBody JSONObject jsonParam){
        String questionId = jsonParam.get("questionid").toString();
        int questionid = Integer.parseInt(questionId);
        String modelname = jsonParam.get("modelname").toString();
        String modeltime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString();
        DatamodelName datamodelName =new DatamodelName();
        datamodelName.setModelname(modelname);
        datamodelName.setModeltime(modeltime);
        datamodelName.setQuestionid(questionid);
        System.out.println(modeltime);
        String info=dataAggregationService.addDatamoddelName(datamodelName);
        return info;
    }

    @GetMapping(value = "/aggreShow/{jiid}")  //得到数据块中id
    public ModelAndView baoShow(Model model, @PathVariable("jiid") Integer jiid){
        /*得到处理后的数据包的表名和地址*/
        ModelAndView modelAndView=new ModelAndView();
        /*  DatamodelInfo datamodelInfo= sourceService.selectBlockName(blockid);*/
        DatamodelJi datamodelJi = dataAggregationService.selectDataCollect(jiid);
        int baoid=datamodelJi.getBaoid();
        DatamodelBao datamodelBao = dataAggregationService.selectDataBaoByBaoId(baoid);
        modelAndView.setViewName("data/aggreShow");
        modelAndView.addObject("datamodelJi",datamodelJi);
        modelAndView.addObject("datamodelBao",datamodelBao);
        return modelAndView;
    }

    @GetMapping("/dataJiReview/{baoid}")
    public String review(@PathVariable("baoid") Integer baoid, Map<String, Object> map) {
        DatamodelInfo datamodelInfo = sourceService.selectBaoName(baoid);
        /*  DatamodelInfo datamodelInfo= blockRepository.findById(dataresultid).orElse(null);*/
        String fileName = datamodelInfo.getDataresultname();
        String filePath = datamodelInfo.getDataaddr();
        /* String fileTime = datamodelSource.getSourcetime();*/
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        /* System.out.println(df.format(new Date()));*/// new Date()为获取当前系统时间
        String fileTime = df.format(new Date());
        map.put("fileName", fileName);
        map.put("filePath", filePath);
        map.put("fileTime", fileTime);
        String fileType = fileName.split("\\.")[1];
        map.put("fileType", fileType);
        //System.out.println(fileName + "-----" + fileType + "-----" + filePath);
        //确认要读取的是csv文件
        if (fileType.equals("csv")) {
            List<String> result = fileService.readCsvFile(filePath);
            map.put("result", result);
            System.out.println(result.toString());
        }
        //不管是不是csv格式，都返回页面，如果不是在前端页面再处理
        return "data/dataReview";
    }

    @RequestMapping("/dataAggrebuild/{modelid}")
    public ModelAndView selectDataCollec(Model model, @PathVariable("modelid") Integer modelid){
        //System.out.println(modelid);
        List<DatamodelJi> dataAggreList = dataAggregationService.selectDataCollec(modelid);
        for (DatamodelJi datamodelJi:dataAggreList) {
            DatamodelBao datamodelBao = dataAggregationService.selectDataBaoByBaoId(datamodelJi.getBaoid());
            datamodelJi.setBaoname(datamodelBao.getBaoname());
        }
        model.addAttribute("dataAggreList",dataAggreList);
        model.addAttribute("modelid",modelid);
        return new ModelAndView("data/dataAggrebuild","model",model);
    }
    @RequestMapping("/dataAggregation/{modelid}")
    public ModelAndView dataAggregation(Model model, @PathVariable("modelid") Integer modelid){
        List<DatamodelBao> dataPackList = dataAggregationService.selectDataPack(modelid);
        model.addAttribute("dataPackList",dataPackList);
        model.addAttribute("modelid",modelid);
        return new ModelAndView("data/dataAggregation","model",model);
    }

    @GetMapping(value = "/dataPackSearch/{modelid}")
    public ModelAndView dataPackSearch(Model model, @PathVariable("modelid") Integer modelid,@RequestParam("search_text") String search_text){
        //拼接模糊查询的通配符
        String res = "%" +search_text+"%";
        System.out.println(res);
        //返回查到的list
        Iterable<DatamodelBao> dataPackList= dataAggregationService.findByPacknameLike(modelid.toString(),res);
        System.out.println(((List<DatamodelBao>) dataPackList).size());
        model.addAttribute("dataPackList",dataPackList);
        return new ModelAndView("data/dataAggregation","model",model);
    }

    @PostMapping("/deletDataCollect")
    @ResponseBody
    public String deletDataCollect(@RequestBody String jsonData) {
        JSONObject jsonObject = JSON.parseObject(jsonData);
        String datacollecid = jsonObject.getString("datacollecid");
        dataAggregationService.deletDataCollect(Integer.parseInt(datacollecid));
        return datacollecid;
    }
}


