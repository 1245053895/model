
package com.xd.zt.controller.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.xd.zt.domain.analyse.Algorithm;
import com.xd.zt.domain.business.flow.JsPlumbBlock;
import com.xd.zt.domain.business.flow.JsPlumbConnect;
import com.xd.zt.domain.data.DatamodelArea;
import com.xd.zt.domain.data.DatamodelInfo;
import com.xd.zt.domain.data.DatamodelSource;
import com.xd.zt.repository.data.FileRepository;
import com.xd.zt.service.data.DataAreaService;
import com.xd.zt.service.data.FileService;
import com.xd.zt.service.data.FlowService;
import com.xd.zt.service.data.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by KAIRUN on 2019/3/6.
 */
@RequestMapping("/data")
@Controller
public class DataAreaController {

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
    /*数据区*/
    @RequestMapping("/datalareashow/{modelid}")
    public ModelAndView datalareashow(Model model, @PathVariable("modelid") Integer modelid) {
        List<DatamodelArea> datamodelAreaList = sourceService.selectModelArea(modelid);
        for (DatamodelArea datamodelArea:datamodelAreaList){
            String linkName = dataAreaService.selectLinkName(datamodelArea.getAreaid()+"");
            datamodelArea.setLinkname(linkName);
        }
        model.addAttribute("datamodelAreaList", datamodelAreaList);
        model.addAttribute("modelid", modelid);
        return new ModelAndView("data/datalareashow", "modelModel", model);
    }

//    @RequestMapping("/dataarea/{modelid}")
//    public ModelAndView dataarea(Model model, @PathVariable("modelid") Integer modelid) {
////        /* DatamodelArea datamodelArea= sourceService.modelIdByAreaid(areaid);*/
////        ModelAndView modelAndView = new ModelAndView();
////        /*   Integer modelid=datamodelArea.getModeid();*/
////        modelAndView.addObject("modelid", modelid);
////        modelAndView.setViewName("dataarea");
////        return modelAndView;
//        model.addAttribute("modelid",modelid);
//        model.addAttribute("datalinkInfo",sourceService.dataModelLink(modelid));
//        return new ModelAndView("data/dataarea","model",model);
//    }


//    @RequestMapping("/areaView/{areaid}")
//    public ModelAndView areaView(Model model, @PathVariable("areaid") Integer areaid) {
//        /*  DatamodelLink datamodelLink = sourceService.getLinkId(linkid);*/
//        DatamodelArea datamodelArea = sourceService.getAreaId(areaid);
//        Integer processid = datamodelArea.getProcessid();
//        model.addAttribute("processid", processid);
//        DatamodelInfo datamodelInfo = sourceService.areaAllExcel(areaid);
//        ModelAndView modelAndView1 = new ModelAndView("Areaview");
//        List<JsPlumbBlock> blocks = flowService.getBlocks(processid);
//        List<JsPlumbConnect> connects = flowService.getConnects(processid);
//        modelAndView1.addObject("myblocks", blocks);
//        modelAndView1.addObject("myconnects", connects);
//        modelAndView1.addObject("datamodelInfo", datamodelInfo);
//        return modelAndView1;
//    }


    /*删除数据区*/
//    @ResponseBody
//    @RequestMapping("/deleteArea")
//    public Map<String, Object> deleteArea(@RequestBody String jsonData) {
//        JSONObject jsonObject = JSON.parseObject(jsonData);
//        String areaid = jsonObject.getString("areaid");
//        sourceService.deleteArea(Integer.valueOf(areaid));
//        Map<String, Object> map = new HashMap<>();
//        map.put("code", 1);
//        return map;
//    }
    @RequestMapping("/saveDataAreaResult")
    @ResponseBody
    public String saveDataAreaResult(@RequestBody JSONObject jsonObject){
        String blockname = jsonObject.get("dataarearesult").toString();
        String modelid = jsonObject.get("modelid").toString();
        String areaid = String.valueOf(dataAreaService.selectAreaid());
        String flowchart = jsonObject.get("flowchart").toString();
        dataAreaService.saveDataAreaResult(blockname,flowchart,areaid);
        return modelid;
    }
    @RequestMapping("/dataareacreate/{modelid}")
    public ModelAndView datablockcreate(Model model, @PathVariable("modelid") String modelid) {
        model.addAttribute("modelid", modelid);
        List<DatamodelSource> datamodelSourceList = sourceService.datamodelSourceByModeId(modelid,"1");
        model.addAttribute("datamodelSourceList", datamodelSourceList);
        List<Algorithm> algorithmList = sourceService.selectAlgorithm();
        model.addAttribute("algorithmList",algorithmList);
        return new ModelAndView("data/dataareacreate", "Modelmodel", model);
    }

    @RequestMapping("/datahanding/{modelid}")
    public ModelAndView datahanding(Model model, @PathVariable("modelid") String modelid) {
        model.addAttribute("modelid", modelid);
        List<DatamodelSource> datamodelSourceList = sourceService.datamodelSourceByModeId(modelid,"0");
        model.addAttribute("datamodelSourceList", datamodelSourceList);
        return new ModelAndView("data/datahanding", "Modelmodel", model);
    }
    @RequestMapping("/datahandingReview")
    @ResponseBody
    public List<String> selectCsvHead(@RequestBody String jsonData, Map<String, Object> map)  {
        JSONObject jsonObject = JSON.parseObject(jsonData);
        String sourceid = jsonObject.getString("sourceid");
        //int id = Integer.parseInt(datablock);
        DatamodelSource datamodelSource = dataAreaService.selectSourceById(Integer.parseInt(sourceid));
        String sourcename = datamodelSource.getSourcename();
        String sourcepath = datamodelSource.getSourcepath();
        map.put("sourcename", sourcename);
        map.put("sourcepath", sourcepath);
        String dataType = sourcename.split("\\.")[1];
        // System.out.println(dataName + "-----" + dataType + "-----" + dataPath);
        //确认要读取的是csv文件
        if (dataType.equals("csv")) {
            List<String> result = fileService.readCsvFile(sourcepath);
            map.put("result", result);
            return result;

        }
        //不管是不是csv格式，都返回页面，如果不是在前端页面再处理
        return null;

    }
    @RequestMapping(value = "/datahandingSave")
    @ResponseBody
    public String dataSave(@RequestBody String json) {
        //存储csv文件列数据
        List<JSONArray> list =new ArrayList<JSONArray>();
        //存储csv文件行数据
        List<String[]> listContent =new ArrayList<String[]>();
        JSONObject jsonObject = JSON.parseObject(json);
        //获取数据包名
        String sourceName=jsonObject.getString("fileName");
        String fileName=sourceName+".csv";
        String modelid=jsonObject.getString("modelid");
        //获取表头数组
        JSONArray head = jsonObject.getJSONArray("表头");
        String[] csvHeaders = new String[head.size()];
        for (int i = 0; i <head.size() ; i++) {
            JSONArray jsonArray=jsonObject.getJSONArray(head.getString(i));
            csvHeaders[i]=head.getString(i);
            list.add(jsonArray);
        }
        for (int i = 0; i <list.get(0).size(); i++) {
            String[] csvContent = new String[head.size()];
            for (int j = 0; j <head.size() ; j++) {
                csvContent[j]=list.get(j).getString(i);
            }
            listContent.add(csvContent);
        }
        String csvFilePath = "F:\\Projects\\ZTPT\\src\\main\\resources\\static\\"+fileName;
        //构建csv文件
        fileService.writeCsvFile(csvFilePath,csvHeaders,listContent);
        //获取数据源id
        String linksource=jsonObject.getString("sourceid");
        File file = new File(csvFilePath);
        String filesize = String.format("%1.2f",(float)file.length()/1024 )+"KB";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        //数据库存储合并数据
        //数据库存储csv文件路径
        DatamodelSource datamodelSource = new DatamodelSource();
        datamodelSource.setModeid(Integer.parseInt(modelid));
        datamodelSource.setSourcename(fileName);
        datamodelSource.setSourcesize(filesize);
        datamodelSource.setSourcetime(date);
        datamodelSource.setSourcepath(csvFilePath);
        datamodelSource.setStatus("1");
        datamodelSource.setLinksource(linksource);
        fileRepository.save(datamodelSource);
        return json;
    }

    @ResponseBody
    @RequestMapping("/saveAreaExample")
    public Map<String,Object> saveAreaExample(@RequestBody JSONObject jsonObject){
        Map<String,Object> map = new HashMap<>();
        String modelid = jsonObject.get("modelid").toString();
        String modelinstancename = jsonObject.get("submitName").toString();
        String analyzmodel = jsonObject.get("analyzmodel").toString();
        //System.out.println(analyzmodel);
        String modelinstanceid = dataAreaService.selectInstanceName(modelinstancename);
        if(modelinstanceid==""||modelinstanceid==null){
            dataAreaService.insertExample(modelid,modelinstancename,analyzmodel);
            map.put("code",1);
        }
        else {
            map.put("code",0);
        }
        return map;
    }
}
