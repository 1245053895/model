package com.xd.zt.controller.analyse;

import com.alibaba.fastjson.JSONObject;
import com.xd.zt.domain.analyse.*;
import com.xd.zt.domain.data.DatamodelSource;
import com.xd.zt.repository.analyse.AnalyseResultRepository;
import com.xd.zt.repository.data.FileRepository;
import com.xd.zt.service.analyse.AnalyseResultService;
import com.xd.zt.service.analyse.AnalyseService;
import com.xd.zt.service.data.FileService;
import com.xd.zt.util.data.ShellUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("/analyse")
@Controller
public class AnalyseResultController {
    @Autowired
    private AnalyseService analyseService;
    @Autowired
    private AnalyseResultService analyseResultService;
    @Autowired
    private FileService fileService;
    @Autowired
    private AnalyseResultRepository analyseResultRepository;

    @RequestMapping("/analyseResult/{id}")
    public ModelAndView analyseResult(Model model, @PathVariable("id") Integer analysemodelid){
        List<AnalyseModelProcess> analyseModelProcessList = analyseService.selectAnalyseModel(analysemodelid);
        List<AnalyseCsv> analyseCsvList = new ArrayList<>();
        for (int i = 0 ; i < analyseModelProcessList.size() ; i++){
            Integer modelid = analyseModelProcessList.get(i).getModelid();
            List<AnalyseInstance> analyseInstanceList = analyseService.selectAnalyseExample(modelid);
            for (int j = 0 ; j < analyseInstanceList.size() ; j++){
                List<AnalyseCsv> analyseCsvList1 = analyseResultService.selectAnalyseCsv(analyseInstanceList.get(j).getModelinstanceid());
                for (int k = 0 ; k < analyseCsvList1.size(); k++){
                    analyseCsvList.add(analyseCsvList1.get(k));
                }
            }
        }
        model.addAttribute("analyseCsvList",analyseCsvList);

        return new ModelAndView("analyse/ResultList","Modelmodel",model);
    }

    @GetMapping("/dataReview/{csvid}")
    public String fileReview(@PathVariable("csvid") Integer csvid, Map<String, Object> map) {
        AnalyseCsv analyseCsv = analyseResultService.selectCsvByid(csvid);
        /*  DatamodelInfo datamodelInfo= blockRepository.findById(dataresultid).orElse(null);*/
        String fileName = analyseCsv.getCsvname();
        String filePath = analyseCsv.getCsvpath();
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
        return "analyse/analysedataReview";
    }

    @RequestMapping("/analyseFileManage/{modelid}")
    public ModelAndView analyseFileManage(Model model,@PathVariable("modelid") Integer modelid){
        List<AnalyseSource> analyseSourceList = analyseResultService.selectAnalyseSource(modelid);

        model.addAttribute("analyseSourceList",analyseSourceList);
        model.addAttribute("modelid",modelid);
        return new ModelAndView("analyse/fileManage","Modelmodel",model);
    }

    //文件上传，保存数据库,void方法需要加上ResponseBody否则会报错，不加会去找url作为接收页面，如果url不是页面就报错
    @ResponseBody
    @PostMapping("/saveFile")
    public void upFile(@RequestParam("filename") MultipartFile filename, @RequestParam("modelid") Integer modelid) throws Exception {

        String[] fileInformation = fileService.Upload(filename);
        String sourcename = fileInformation[0];
        String sourcepath = fileInformation[1];
        String sourcesize = fileInformation[2];
        ShellUtil.execCmd("mv /zt/"+sourcepath+" /var/data/celery/input/analyse/\n","root","/zt/IA","10.101.201.173",22);
        System.out.println(filename + "----" + sourcepath + "----" + sourcesize);
        AnalyseSource analyseSource = new AnalyseSource();
        analyseSource.setAnalysemodelid(modelid);
        analyseSource.setAnalysesourcename(sourcename);

        String sourcepath1 = "/var/data/celery/input/analyse/"+sourcename;
        analyseSource.setAnalysesourcepath(sourcepath1);
        analyseSource.setAnalysesourcesize(sourcesize);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        analyseSource.setAnalysesourcetime(date);

        analyseResultService.saveAnalyseSource(analyseSource);
    }

    //删除数据
    @ResponseBody
    @RequestMapping("/fileDelete")
    public Map<String,Object> fileDelete(@RequestBody JSONObject jsonObject) {
        Map<String,Object> map=new HashMap<>();
        String analysesourceid1=jsonObject.get("analysesourceid").toString();
        Integer analysesourceid=Integer.parseInt(analysesourceid1);
        AnalyseSource analyseSource = analyseResultService.selectAnalyseSourceById(analysesourceid);

        String filepath = analyseSource.getAnalysesourcepath();
        System.out.println(filepath);

        //删除服务器记录
        analyseResultService.deleteAnalyseSource(analysesourceid);
        //删除对应文件
        fileService.deleteFile(filepath);
        ModelAndView modelAndView=new ModelAndView();

        map.put("code",1);
        return map;
    }

    @GetMapping("/fileResultDown/{id}")
    public String fileDown(HttpServletResponse response, @PathVariable("id") Integer id) {
       AnalyseCsv analyseCsv= analyseResultRepository.findById(id).orElse(null);
      String csvpath=analyseCsv.getCsvpath();
        System.out.println(csvpath);
        try {
            // path是指欲下载的文件的路径。
            File file = new File(csvpath);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(csvpath));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
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
}
