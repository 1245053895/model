package com.xd.zt.controller.dataManager;

import com.xd.zt.domain.analyse.AnalyseCsv;
import com.xd.zt.domain.dataManage.DataManage;
import com.xd.zt.service.data.FileService;
import com.xd.zt.service.dataManager.DataManagerService;
import com.xd.zt.service.dataManager.DataViewAndServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dataManager")
public class DataViewAndContrller {
        @Autowired
        private DataViewAndServices dataViewAndServices;
        @Autowired
         private FileService fileService;

        @GetMapping("/dataReview/{sourceid}")
        public String fileReview(@PathVariable("sourceid") Integer sourceid, Map<String, Object> map) {
            DataManage dataManage = dataViewAndServices.selectDatamanageById(sourceid);
            String fileName = dataManage.getSourcename();
            String filePath = dataManage.getSourcepath();

          File file=new File(filePath);
          String name=file.getName();
          String type=  name.substring(name.lastIndexOf("."));
          type= type.replace(".","");
          SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
          String fileTime = df.format(new Date());
          map.put("fileName", fileName);
          map.put("filePath", filePath);
          map.put("fileTime", fileTime);
//        /*        String fileType = fileName.split("\\.")[1];*/
//      /*  String fileType = fileName.split("/.")[1];
//        map.put("fileType", fileType);*/
          map.put("fileType", type);
//        //System.out.println(fileName + "-----" + fileType + "-----" + filePath);
//        //确认要读取的是csv文件
        if (type.equals("csv")) {
            List<String> result = fileService.readCsvFile(filePath);
            map.put("result", result);
        }
//        //不管是不是csv格式，都返回页面，如果不是在前端页面再处理
            return "dataManager/analysedataReview";
        }

    @GetMapping("/fileResultDown/{sourceid}")
    public String fileDown(HttpServletResponse response, @PathVariable("sourceid") Integer sourceid) {
        DataManage dataManage = dataViewAndServices.selectDatamanageById(sourceid);
        String csvpath = dataManage.getSourcepath();

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





        @GetMapping("/dataReviewx/{dataresultid}")
        public String fileReviewx(@PathVariable("dataresultid") Integer dataresultid, Map<String, Object> map) {
            DataManage dataManage = dataViewAndServices.selectDatamodelInfo(dataresultid);
            String fileName = dataManage.getDataresultname();
            String filePath = dataManage.getDataaddr();

             File file=new File(filePath);
            String name=file.getName();
           String type=  name.substring(name.lastIndexOf("."));
            type= type.replace(".","");
           SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
          String fileTime = df.format(new Date());
           map.put("fileName", fileName);
           map.put("filePath", filePath);
           map.put("fileTime", fileTime);
        map.put("fileType", type);
        //确认要读取的是csv文件
        if (type.equals("csv")) {
            List<String> result = fileService.readCsvFile(filePath);
            map.put("result", result);
            //System.out.println(result.toString());
        }
//        //不管是不是csv格式，都返回页面，如果不是在前端页面再处理
            return "dataManager/analysedataReview";
        }

    @GetMapping("/fileResultDownx/{dataresultid}")
    public String fileDownx(HttpServletResponse response, @PathVariable("dataresultid") Integer dataresultid) {
        DataManage dataManage = dataViewAndServices.selectDatamodelInfo(dataresultid);
        String csvpath = dataManage.getDataaddr();
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





        @GetMapping("/dataReviewxx/{id}")
        public String fileReviewxx(@PathVariable("id") Integer id, Map<String, Object> map) {
            DataManage dataManage = dataViewAndServices.selectAnalyseCsv(id);
            String fileName = dataManage.getCsvname();
            String filePath = dataManage.getCsvpath();
            File file=new File(filePath);
            String name=file.getName();
            String type=  name.substring(name.lastIndexOf("."));
           type= type.replace(".","");
           SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
           String fileTime = df.format(new Date());
          map.put("fileName", fileName);
          map.put("filePath", filePath);
          map.put("fileTime", fileTime);
        map.put("fileType", type);
        //确认要读取的是csv文件
        if (type.equals("csv")) {
            List<String> result = fileService.readCsvFile(filePath);
            map.put("result", result);
            //System.out.println(result.toString());
        }
//        //不管是不是csv格式，都返回页面，如果不是在前端页面再处理
            return "dataManager/analysedataReview";
        }



    @GetMapping("/fileResultDownxx/{id}")
    public String fileDownxx(HttpServletResponse response, @PathVariable("id") Integer id) {
         DataManage dataManage = dataViewAndServices.selectAnalyseCsv(id);
         String csvpath = dataManage.getCsvpath();
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





