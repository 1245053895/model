package com.xd.zt.controller.analyse;

import com.alibaba.fastjson.JSONObject;
import com.xd.zt.util.analyse.DownloadFileUtil;
import com.xd.zt.util.analyse.FindLinuxDirectory;
import com.xd.zt.util.analyse.HttpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;

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

            httpUtil.post("http://127.0.0.1:8000/tasks/"+modelInstanceId+"/"+taskId+"/delete/",null);
//            httpUtil.post("http://120.24.157.214:8000/tasks/"+modelInstanceId+"/"+taskId+"/delete/",null);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return taskId;
    }

    @ResponseBody
    @RequestMapping("/downloadLinuxFile")
    public String downloadLinuxFile(@RequestBody JSONObject jsonObject){
        String taskId = jsonObject.get("taskId").toString();
     String Directory = FindLinuxDirectory.FindDirectory("120.24.157.214",22,"root","Slw123456","find /var/data/celery/output/"+taskId+" -type f");
     String[] DirectoryList = Directory.split("\n");
     for (int i = 0; i < DirectoryList.length; i++ ){
         String[] FileDirectory = DirectoryList[i].split("/");
         String fileName = FileDirectory[FileDirectory.length-1];
         String fileLastDirectory = FileDirectory[FileDirectory.length-2];

         String targetDirectory = "D:\\分析处理任务结果\\"+taskId+"\\"+fileLastDirectory;
         File file=new File(targetDirectory);
         if(!file.exists()){//如果文件夹不存在
             boolean result = file.mkdirs();//创建文件夹
             System.out.println("Status = " + result);
         }

         DownloadFileUtil.downloadFromLinux("120.24.157.214",22,"root","Slw123456",DirectoryList[i],targetDirectory+"\\");

     }
        return taskId;
    }
}
