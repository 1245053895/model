package com.xd.zt.controller.experiment;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xd.zt.domain.data.DatamodelSource;
import com.xd.zt.domain.experiment.ExperimentData;
import com.xd.zt.repository.experiment.DataFileRepository;
import com.xd.zt.repository.experiment.DataRepository;
import com.xd.zt.repository.experiment.ExperimentRepository;
import com.xd.zt.service.experiment.DataFileService;
import com.xd.zt.service.experiment.ExperimentDataService;
import com.xd.zt.util.data.ShellUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.ldap.PagedResultsControl;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/experiment")
public class ExperimentDataController {
    @Autowired
    private ExperimentRepository experimentRepository;
 @Autowired
    private DataFileService dataFileService;
    @Autowired
    private ExperimentDataService experimentDataService;
    @Autowired
    private DataFileRepository dataFileRepository;

    @RequestMapping("/datashow/{experimentid}")
    public ModelAndView dataShow(@PathVariable("experimentid") Integer experimentid){
        ModelAndView modelAndView=new ModelAndView();
      List<ExperimentData> experimentDataList= experimentDataService.selectFileByExperimetnId(experimentid);
      modelAndView.addObject("experimentid",experimentid);
      modelAndView.addObject("experimentDataList",experimentDataList);
        modelAndView.setViewName("experiment/datashow");
        return modelAndView;
    }

    //文件上传，保存数据库,void方法需要加上ResponseBody否则会报错，不加会去找url作为接收页面，如果url不是页面就报错
    @ResponseBody
    @PostMapping("/saveDataFile")
    public void saveDataFile(@RequestParam("dataname") MultipartFile dataname, @RequestParam("experimentid") Integer experimentid) throws Exception {

        String[] fileInformation = dataFileService.Upload(dataname);
        String dataname1 = fileInformation[0];
        String datapath = fileInformation[1];
        String datasize = fileInformation[2];
        ShellUtil.execCmd("mv /zt/"+datapath+" /var/data/celery/input/\n","root","/zt/IA","10.101.201.173",22);
        System.out.println(dataname1 + "----" + datapath + "----" + datasize);
       ExperimentData experimentData=new ExperimentData();
       experimentData.setDataname(dataname1);
       experimentData.setExperimentid(experimentid);
       experimentData.setDatapath(datapath);
       experimentData.setDatasize(datasize);
        String datapath1 = "/var/data/celery/input/"+dataname1;
        experimentData.setDatapath(datapath1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        experimentData.setDatatime(date);
        dataFileRepository.save(experimentData);
    }



    /*删除数据文件*/
    //删除数据
    @ResponseBody
    @RequestMapping("/dataDelete")
    public Map<String,Object> dataDelete(@RequestBody JSONObject jsonObject) {
        Map<String,Object> map=new HashMap<>();
        String dataid=jsonObject.get("dataid").toString();
        Integer id=Integer.parseInt(dataid);
      ExperimentData experimentData=  dataFileRepository.findById(id).orElse(null);
        String datapath = experimentData.getDatapath();
        System.out.println(datapath);
        dataFileRepository.deleteById(id);
        //删除服务器记录
        //删除对应文件
        File file = new File(datapath);
        if (file.exists()) {
            file.delete();
            boolean result = file.delete();
            if (!result) {
                System.gc();
                file.delete();
            }
        };
        map.put("code",1);
        return map;
    }

    /*下载文件*/
    @GetMapping("/dataFileDown/{id}")
    public String dataFileDown(HttpServletResponse response, String filePath, @PathVariable("id") Integer id) {
       ExperimentData experimentData= dataFileRepository.findById(id).orElse(null);
        String datapath = experimentData.getDatapath();
        System.out.println(datapath);

        try {
            // path是指欲下载的文件的路径。
            File file = new File(datapath);
            // 取得文件名。
            String dataname = file.getName();
            // 取得文件的后缀名。
            String ext = dataname.substring(dataname.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。

            InputStream fis = new BufferedInputStream(new FileInputStream(datapath));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;dataname=" + new String(dataname.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;

    }

    //搜索数据
    @GetMapping(value = "/dataFileSearch")
    public ModelAndView dataFileSearch(@RequestParam("dataname") String dataname) {
        //拼接模糊查询的通配符
        ModelAndView modelAndView=new ModelAndView();
        String res = "%" + dataname + "%";
        System.out.println(res);
       List<ExperimentData> experimentDataList= experimentDataService.moHuDataFile(res);
       modelAndView.addObject("experimentDataList",experimentDataList);
       modelAndView.setViewName("experiment/datashow");
       return modelAndView;
    }


    @RequestMapping("/dataFileReview")
    public String dataFileReview(){
        return "experiment/dataFileReview";
    }

    /*数据预览*/
    @GetMapping("/dataFileReview/{id}")
    public String fileReview(@PathVariable("id") Integer id, Map<String, Object> map) {
      ExperimentData experimentData=  experimentDataService.selectDataFileById(id);
      String dataname=experimentData.getDataname();
      String datapath=experimentData.getDatapath();
        File file=new File(datapath);
        String name=file.getName();
        String type=  name.substring(name.lastIndexOf("."));
        type= type.replace(".","");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        /* System.out.println(df.format(new Date()));*/// new Date()为获取当前系统时间
        String fileTime = df.format(new Date());
        map.put("dataname", dataname);
        map.put("datapath", datapath);
        map.put("fileTime", fileTime);
        /*        String fileType = fileName.split("\\.")[1];*/
      /*  String fileType = fileName.split("/.")[1];
        map.put("fileType", fileType);*/
        map.put("fileType", type);
        //System.out.println(fileName + "-----" + fileType + "-----" + filePath);
        //确认要读取的是csv文件
        if (type.equals("csv")) {
            List<String> result=  dataFileService.readCsvFile(datapath);
            map.put("result", result);
        }
        //不管是不是csv格式，都返回页面，如果不是在前端页面再处理
        return "experiment/dataFileReview";
    }





    @PostMapping(value = "/saveData")
    @ResponseBody   /*,@RequestBody(required = false) String shebei,@RequestBody(required = false) String xianlu,@RequestBody(required = false) String test10*/
    public Map<String,Object> saveData(@RequestBody(required = false) String JsonData) {
        JSONObject jsonObject=JSON.parseObject(JsonData);
       String datatype= jsonObject.get("datatype").toString();
       String shebei=jsonObject.get("shebei").toString();
       String xianlu=jsonObject.get("xianlu").toString();
       String test10=jsonObject.get("test10").toString();
       String id=jsonObject.get("experimentid").toString();
       Integer experimentid=Integer.parseInt(id);
       String params=shebei+";"+xianlu+";"+test10;
        ExperimentData experimentData=new ExperimentData();
        experimentData.setDatatype(datatype);
        experimentData.setParams(params);
        experimentData.setExperimentid(experimentid);
        experimentRepository.save(experimentData);
        Map<String,Object> map=new HashMap<>();
        map.put("code",1);
        return map;
    }

    @RequestMapping("/resultShow/{experimentid}")
    public ModelAndView experimentresultShow(Model model,@PathVariable("experimentid") Integer experimentid) {
      ExperimentData experimentData=  experimentDataService.modelDataByExperimentId(experimentid);
    /*  ExperimentData experimentData= dataRepository.findByExperimentid(id).orElse(null);*/
      String datatype=experimentData.getDatatype();
      String params=experimentData.getParams();
      String array[]=params.split(";");
      String shebei=array[0];
      String xianlu=array[1];
      String shijian=array[2];
       model.addAttribute("datatype",datatype);
        model.addAttribute("shebei",shebei);
        model.addAttribute("xianlu",xianlu);
        model.addAttribute("shijian",shijian);
        return new ModelAndView("experiment/resultShow", "modelModel", model);
    }
}
