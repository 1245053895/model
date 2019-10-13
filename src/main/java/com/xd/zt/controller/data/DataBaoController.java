package com.xd.zt.controller.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.xd.zt.domain.data.DatamodelBao;
import com.xd.zt.domain.data.DatamodelBlock;
import com.xd.zt.domain.data.DatamodelInfo;
import com.xd.zt.service.data.DataBlockService;
import com.xd.zt.service.data.FileService;
import com.xd.zt.service.data.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
@RequestMapping("/data")
@Controller
public class DataBaoController {
    @Autowired
    private DataBlockService dataBlockService;
    @Autowired
    private SourceService sourceService;
    @Resource
    private FileService fileService;

    @RequestMapping("/databaobuild/{id}")
    public ModelAndView databaobuild(Model model, @PathVariable("id") Integer modelid) {
        List<DatamodelBao> datamodelBaoList = dataBlockService.selectBaoById(modelid);
        for (DatamodelBao datamodelBao: datamodelBaoList) {
            String[] blockids = datamodelBao.getBlockid().split(",");
            String blockNames="";
            for (int i = 0; i < blockids.length; i++) {
                DatamodelBlock datamodelBlock = sourceService.selectBlockById(Integer.parseInt(blockids[i]));
                blockNames =blockNames+ datamodelBlock.getBlockname();
            }
            datamodelBao.setBlockname(blockNames);
        }
        model.addAttribute("datamodelBaoList", datamodelBaoList);
        model.addAttribute("modelid", modelid);
        return new ModelAndView("data/databaobuild", "Modelmodel", model);
    }

    @RequestMapping("/databaocreate/{id}")
    public ModelAndView databaocreate(Model model, @PathVariable("id") Integer modelid) {
        model.addAttribute("modelid", modelid);
        List<DatamodelBlock> datamodelBlockList = dataBlockService.selectBlockById(modelid);  //查询数据块表
         model.addAttribute("datamodelBlockList", datamodelBlockList);
        List<DatamodelInfo> datamodelInfoList = dataBlockService.selectDataBlockResultById(modelid);//根据modelid查出blockid不为空的数据块结果表
        model.addAttribute("datamodelInfoList", datamodelInfoList);
        return new ModelAndView("data/databaocreate", "Modelmodel", model);
    }

    @RequestMapping(value = "/dataSave")
    @ResponseBody
    public String dataSave(@RequestBody String json) {
        //存储csv文件列数据
        List<JSONArray> list = new ArrayList<JSONArray>();
        //存储csv文件行数据
        List<String[]> listContent = new ArrayList<String[]>();
        JSONObject jsonObject = JSON.parseObject(json);
        //获取数据包名
        String baoName = jsonObject.getString("fileName");
        String fileName = baoName + ".csv";
        String modelid = jsonObject.getString("modelid");
        //获取表头数组
        JSONArray head = jsonObject.getJSONArray("表头");
        String[] csvHeaders = new String[head.size()];
        for (int i = 0; i < head.size(); i++) {
            JSONArray jsonArray = jsonObject.getJSONArray(head.getString(i));
            csvHeaders[i] = head.getString(i);
            list.add(jsonArray);
        }
        for (int i = 0; i < list.get(0).size(); i++) {
            String[] csvContent = new String[head.size()];
            for (int j = 0; j < head.size(); j++) {
                csvContent[j] = list.get(j).getString(i);
            }
            listContent.add(csvContent);
        }
       /* String csvFilePath = "F:\\Projects\\ZTPT\\src\\main\\resources\\static\\" + fileName;*/
        String csvFilePath = "src/main/resources/static/data/"+fileName;
        //构建csv文件
        fileService.writeCsvFile(csvFilePath, csvHeaders, listContent);
        //获取数据块id
        String blockid = jsonObject.getString("datablocks");
        //数据库存储数据包
        dataBlockService.saveDataBaoResult(modelid, baoName, blockid); //对数据包插入数据
       Integer databao= dataBlockService.maxBaoId();
       // String baoid = dataBlockService.selectDataBaoByBaoName(baoName);
        //数据库存储csv文件路径
        dataBlockService.saveDataBaoCsvResult(fileName, databao.toString(), blockid, modelid, csvFilePath);

        return json;
    }


    @RequestMapping("/dataReview")
    @ResponseBody
    public List<String> selectCsvHead(@RequestBody String jsonData, Map<String, Object> map) throws IOException {
        JSONObject jsonObject = JSON.parseObject(jsonData);
        String blockid = jsonObject.getString("blockid");
        //int id = Integer.parseInt(datablock);
        System.out.println(blockid);
        DatamodelInfo datamodelInfo = dataBlockService.selectDataAreaResultByDatablock(blockid);

        String dataName = datamodelInfo.getDataresultname();
        String dataPath = datamodelInfo.getDataaddr();
        map.put("dataName", dataName);
        map.put("dataPath", dataPath);

        File file=new File(dataPath);
        String name=file.getName();
        String type=  name.substring(name.lastIndexOf("."));
        type= type.replace(".","");

      //  String dataType = dataName.split("\\.")[1];
        // System.out.println(dataName + "-----" + dataType + "-----" + dataPath);
        //确认要读取的是csv文件
        if (type.equals("csv")) {
            List<String> result = fileService.readCsvFile(dataPath);
            map.put("result", result);
            return result;

        }
        //不管是不是csv格式，都返回页面，如果不是在前端页面再处理
        return null;

    }


    /*数据块的csv文件的回显*/
    //数据预览
    @GetMapping("/dataReview/{dataresultid}")
    public String review(@PathVariable("dataresultid") Integer dataresultid, Map<String, Object> map) {
        DatamodelInfo datamodelInfo = sourceService.selectResultName(dataresultid);
        /*  DatamodelInfo datamodelInfo= blockRepository.findById(dataresultid).orElse(null);*/
        String fileName = datamodelInfo.getDataresultname();
        String filePath = datamodelInfo.getDataaddr();
        /* String fileTime = datamodelSource.getSourcetime();*/
        File file=new File(filePath);
        String name=file.getName();
        String type=  name.substring(name.lastIndexOf("."));
        type= type.replace(".","");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        /* System.out.println(df.format(new Date()));*/// new Date()为获取当前系统时间
        String fileTime = df.format(new Date());
        map.put("fileName", fileName);
        map.put("filePath", filePath);
        map.put("fileTime", fileTime);
        /*        String fileType = fileName.split("\\.")[1];*/
      /*  String fileType = fileName.split("/.")[1];
        map.put("fileType", fileType);*/
        map.put("fileType", type);
        //System.out.println(fileName + "-----" + fileType + "-----" + filePath);
        //确认要读取的是csv文件
        if (type.equals("csv")) {
            List<String> result = fileService.readCsvFile(filePath);
            map.put("result", result);
            //System.out.println(result.toString());
        }
        //不管是不是csv格式，都返回页面，如果不是在前端页面再处理
        return "data/dataReview";
    }




    /*数据包回显，datamodel_info表 */
    @GetMapping(value = "/baoShow/{baoid}")  //得到数据块中id
    public ModelAndView baoShow(Model model, @PathVariable("baoid") Integer baoid) {
        //得到处理后的数据包的表名和地址
        ModelAndView modelAndView = new ModelAndView();
        DatamodelInfo datamodelInfo = sourceService.selectBaoName(baoid);
        String[] blockids = sourceService.selectBaoById(baoid).getBlockid().split(",");
        String[] blockNames = new String[blockids.length];
        for (int i = 0; i < blockids.length; i++) {
            DatamodelBlock datamodelBlock = sourceService.selectBlockById(Integer.parseInt(blockids[i]));
            blockNames[i] = datamodelBlock.getBlockname();
        }
        modelAndView.setViewName("data/baoShow");
        modelAndView.addObject("datamodelInfo", datamodelInfo);
        modelAndView.addObject("baoid", baoid);
        modelAndView.addObject("blockNames", blockNames);
        return modelAndView;
    }

    @RequestMapping("/saveDataBaoResult")
    @ResponseBody
    public String saveDataBaoResult(@RequestBody String jsonData) {
        JSONObject jsonObject = JSON.parseObject(jsonData);
        String blockid = jsonObject.getString("datablocks");
        String baoname = jsonObject.getString("databaoresult");
        String modelid = jsonObject.getString("modelid");
        dataBlockService.saveDataBaoResult(modelid, baoname, blockid);
        return modelid;
    }

    @RequestMapping("/deletebao")
    @ResponseBody
    public String deletebao(@RequestBody String jsonData) {
        JSONObject jsonObject = JSON.parseObject(jsonData);
        String baoid = jsonObject.getString("baoid");
        dataBlockService.deletebao(Integer.parseInt(baoid));
        dataBlockService.deletebaoFromInfo(Integer.parseInt(baoid));
        return baoid;
    }

}
