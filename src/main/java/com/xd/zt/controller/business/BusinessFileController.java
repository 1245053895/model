
package com.xd.zt.controller.business;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.xd.zt.domain.business.BusinessFile;
import com.xd.zt.domain.data.DatamodelSource;
import com.xd.zt.repository.business.BusinessFileRepository;
import com.xd.zt.service.business.BusinessFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by KAIRUN on 2019/3/6.
 */
@RequestMapping("/business")
@Controller
public class BusinessFileController {
//    @RequestMapping("/upFile/{id}")
//    public String upFile(  Model model,@PathVariable("id") Integer businessid){
//        model.addAttribute("businessid",businessid);
//        return  "upFile";
//    }
    @Autowired
    private BusinessFileRepository fileRepository;
    @Autowired
    private BusinessFileService fileService;
    @Autowired
    private BusinessFileRepository businessFileRepository;


  //预览数据
  @GetMapping(value = "/fileManage/{id}")
  public ModelAndView lookFile(Model model, @PathVariable("id") Integer businessid){
     Iterable<BusinessFile> businessFileList= fileRepository.findAllByBusinessid(businessid);
     model.addAttribute("businessFileList",businessFileList);
      model.addAttribute("businessid",businessid);
     return new ModelAndView("business/fileManage","upFileList",model);
    }

    //文件上传，保存数据库,void方法需要加上ResponseBody否则会报错，不加会去找url作为接收页面，如果url不是页面就报错
    @ResponseBody
    @PostMapping("/saveFile/{businessid}")
    public void upFile(@RequestParam("filename") MultipartFile multipartFile, @PathVariable("businessid") Integer businessid) throws Exception {
 /*      String sqlPath=null;*/
        String[] fileInformation = fileService.Upload(multipartFile);
        String filename = fileInformation[0];
       String filepath = fileInformation[1];
       String filesize = fileInformation[2];
//        System.out.println(filename+"----"+ filepath+"----"+filesize);

        BusinessFile businessFile = new BusinessFile();
        businessFile.setFilename(filename);
  /*      filepath = "/file/"+filename;*/

        businessFile.setFilepath(filepath);
        businessFile.setFilesize(filesize);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       String date = simpleDateFormat.format(new Date());
        businessFile.setFiletime(date);
        businessFile.setBusinessid(businessid);
        fileRepository.save( businessFile);
    }



    //删除数据
    @ResponseBody
    @RequestMapping("/fileDelete")
    public String fileDelete (@RequestBody String json){
        JSONObject jsonObject = JSON.parseObject(json);
        String id = jsonObject.getString("dataid");
        Integer dataid = Integer.parseInt(id);
        BusinessFile businessFile = fileRepository.findById(dataid).orElse(null);
        String filepath = businessFile.getFilepath();
        System.out.println(filepath);

        //删除服务器记录
        fileRepository.deleteById(dataid);
        //删除对应文件
        fileService.deleteFile(filepath);
    return id;
    }

    //搜索数据
    @GetMapping(value = "/dataSearch")
    public ModelAndView dataSearch(Model model, @RequestParam("search_text") String search_text){
        //拼接模糊查询的通配符
        String res = "%" +search_text+"%";
        System.out.println(res);
        //返回查到的list
        Iterable<BusinessFile> businessFileList= fileRepository.findByfilenameLike(res);
        System.out.println(businessFileList);
        model.addAttribute("businessFileList",businessFileList);
        return new ModelAndView("business/fileManage","upFileList",model);
    }

    /*文件下载*/
    @GetMapping("/businessFileDown/{dataid}")
    public String fileDown(HttpServletResponse response, String filePath, @PathVariable("dataid") Integer dataid) {
      BusinessFile businessFile=  businessFileRepository.findById(dataid).orElse(null);
       // DatamodelSource datamodelSource = fileRepository.findById(dataid).orElse(null);
        String filepath=businessFile.getFilepath();
//        String filepath = datamodelSource.getSourcepath();
        System.out.println(filepath);

        try {
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
        return null;

    }





//    @ResponseBody
//    @RequestMapping("/fileDown")
//    public  String fileDown (HttpServletResponse response, String filePath, @RequestBody String json) {
//        JSONObject jsonObject = JSON.parseObject(json);
//        String id = jsonObject.getString("dataid");
//        Integer dataid = Integer.parseInt(id);
//        BusinessFile businessFile = fileRepository.findById(dataid).orElse(null);
//        String filepath = businessFile.getFilepath();
//        System.out.println(filepath);
//        try {
//            // path是指欲下载的文件的路径。
//            File file = new File(filepath);
//            // 取得文件名。
//            String filename = file.getName();
//            // 取得文件的后缀名。
//            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
//
//            // 以流的形式下载文件。
//            InputStream fis = new BufferedInputStream(new FileInputStream(filepath));
//            byte[] buffer = new byte[fis.available()];
//            fis.read(buffer);
//            fis.close();
//            // 清空response
//            response.reset();
//            // 设置response的Header
//            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
//            response.addHeader("Content-Length", "" + file.length());
//            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
//            response.setContentType("application/octet-stream");
//            toClient.write(buffer);
//            toClient.flush();
//            toClient.close();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        return id;
//
//    }
}
