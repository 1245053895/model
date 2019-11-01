package com.xd.zt.controller.analyse;

import com.alibaba.fastjson.JSONObject;
import com.jcraft.jsch.SftpException;
import com.xd.zt.util.analyse.DownloadFileUtil;
import com.xd.zt.util.analyse.FindLinuxDirectory;
import com.xd.zt.util.analyse.HttpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RequestMapping("/analyse")
@Controller
public class TaskController {
    @ResponseBody
    @RequestMapping("/taskDelete")
    public String taskDelete(@RequestBody JSONObject jsonObject){
        String taskId = jsonObject.get("taskId").toString();
        String modelInstanceId = jsonObject.get("modelInstanceId").toString();
        HttpUtil httpUtil = new HttpUtil();
        try {

            httpUtil.post("http://10.101.201.174::8000/tasks/"+modelInstanceId+"/"+taskId+"/delete/",null);
//            httpUtil.post("http://120.24.157.214:8000/tasks/"+modelInstanceId+"/"+taskId+"/delete/",null);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return taskId;
    }

    @ResponseBody
    @RequestMapping("/downloadLinuxFile")
    public String downloadLinuxFile(@RequestBody JSONObject jsonObject,HttpServletResponse response){
        String taskId = jsonObject.get("taskId").toString();
     String Directory = FindLinuxDirectory.FindDirectory("10.101.201.174",22,"root","/zt/IA","find /var/data/celery/output/"+taskId+" -type f");
     System.out.printf(Directory);
     String[] DirectoryList = Directory.split("\n");
     for (int i = 0; i < DirectoryList.length; i++ ){
         String[] FileDirectory = DirectoryList[i].split("/");
         String fileName = FileDirectory[FileDirectory.length-1];
         String fileLastDirectory = FileDirectory[FileDirectory.length-2];

//         String targetDirectory = "D:\\分析处理任务结果\\"+taskId+"\\"+fileLastDirectory;
//         File file=new File(targetDirectory);
//         if(!file.exists()){//如果文件夹不存在
//             boolean result = file.mkdirs();//创建文件夹
//             System.out.println("Status = " + result);
//         }

//         DownloadFileUtil.downloadFromLinux("10.101.201.174",22,"root","/zt/IA",DirectoryList[i],targetDirectory+"//");
         try {
             String filepath = DirectoryList[i];
             // path是指欲下载的文件的路径。
             File file = new File(filepath);
             // 取得文件名。
             String filename = file.getName();
             // 取得文件的后缀名。
             String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

             // 以流的形式下载文件。

             InputStream fis = new BufferedInputStream(new FileInputStream(filepath));
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
         }
        return taskId;
    }
}
