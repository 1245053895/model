
package com.xd.zt.controller.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.xd.zt.domain.business.BusinessQuestion;
import com.xd.zt.domain.data.*;
import com.xd.zt.repository.data.FileRepository;
import com.xd.zt.service.business.ModelCreateService;
import com.xd.zt.service.data.DataBlockService;
import com.xd.zt.service.data.FileService;
import com.xd.zt.service.data.FlowService;
import com.xd.zt.service.data.SourceService;
import com.xd.zt.util.data.ShellUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by KAIRUN on 2019/3/6.
 */
@RequestMapping("/data")
@Controller
public class FileController {
    /*  @RequestMapping("/upFile/{id}")
      public String upFile(Model model, @PathVariable("id") Integer businessid){
          model.addAttribute("businessid",businessid);
          return  "upFile";
      }*/
    @Autowired
    private ModelCreateService modelCreateService;
    @Autowired
    private DataBlockService dataBlockService;

    @Autowired
    private SourceService sourceService;
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private FileService fileService;
    @Autowired
    private FlowService flowService;

//    @RequestMapping("/upFile/{modeid}")
//    public ModelAndView upFile(Model model, @PathVariable("modeid") Integer modeid) {
//
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("modeid", modeid);
//        modelAndView.setViewName("data/upFile");
//        return modelAndView;
//    }

    @RequestMapping("/datalinkshow/{modeid}")
    public ModelAndView datalinkshow(Model model, @PathVariable("modeid") Integer modeid) {
        List<DatamodelLink> datamodelLinkList = sourceService.dataModelLink(modeid);
        model.addAttribute("datamodelLinkList", datamodelLinkList);
        model.addAttribute("modeid", modeid);
        return new ModelAndView("data/datalinkshow", "modelModel", model);
    }

    @RequestMapping("/dataLinkFirst/{modeid}")
    public ModelAndView dataLinkFirst(Model model, @PathVariable("modeid") Integer modeid) {
        //根据modelid得到questionid
         DatamodelName datamodelName=sourceService.getQuestionId(modeid);
        Integer questionid=datamodelName.getQuestionid();
        List<BusinessQuestion> businessQuestionList = modelCreateService.selectquestion(questionid);
        BusinessQuestion businessQuestion = businessQuestionList.get(0);
        String path=businessQuestion.getPicture();
        File file=new File(path.trim());
        String pictureName=file.getName();
        String picture = "/uploadImage/"+pictureName;
        businessQuestion.setPicture(picture);
        model.addAttribute("businessQuestion",businessQuestion);
        ModelAndView modelAndView1 = new ModelAndView("data/dataLinkFirst");
        model.addAttribute("modeid", modeid);
        return modelAndView1;
/*
        model.addAttribute("modeid", modeid);
        model.addAttribute("businessQuestionList", sourceService.selectquesInfo());*/

    }

    @ResponseBody
    @RequestMapping("/selectQuesInfo")
    public Map<String, Object> selectQuesInfo(@RequestBody String jsonData) {
        //System.out.println(jsonData);
        JSONObject jsonObject = JSON.parseObject(jsonData);
        String questionid = jsonObject.getString("questionid");
        //System.out.println(questionid);
        List<BusinessQuestion> questionList = sourceService.selectquesList(questionid);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("questionList", questionList);
       // System.out.println(map);
        return map;
    }

//    @RequestMapping("/Linkview/{linkid}")
//    public ModelAndView getJsplumbModel(Model model, @PathVariable("linkid") Integer linkid) {
//        DatamodelLink datamodelLink = sourceService.getLinkId(linkid);
//        Integer processid = datamodelLink.getProcessid();
//        model.addAttribute("processid", processid);
//        ModelAndView modelAndView1 = new ModelAndView("data/Linkview");
//        List<JsPlumbBlock> blocks = flowService.getBlocks(processid);
//        List<JsPlumbConnect> connects = flowService.getConnects(processid);
//        modelAndView1.addObject("myblocks", blocks);
//        modelAndView1.addObject("myconnects", connects);
//        return modelAndView1;
//    }


//    @RequestMapping("/dataLink/{sceneid}")
//    public ModelAndView dataLink(Model model, @PathVariable("sceneid") Integer sceneid) {
//        System.out.println(sceneid);
//        ModelAndView modelAndView = new ModelAndView();
//        /*sourceService.insertlinkModeid(modeid);
//        DatamodelLink datamodelLink=sourceService.allLinkByModeid(modeid);
//        Integer linkid=datamodelLink.getLinkid();*/
//        /*modelAndView.setViewName("dataLink");
//         *//*     modelAndView.addObject("linkid",linkid);*//*
//        modelAndView.addObject("modeid",modeid);*/
//        return modelAndView;
//    }
/*    @RequestMapping("/upFile1")
    public String upFile1(){
        return "upFile1";
    }*/


    /*删除数据链*/
    @ResponseBody
    @RequestMapping("/deletelink")
    public Map<String, Object> deletelink(@RequestBody String jsonData) {
        JSONObject jsonObject = JSON.parseObject(jsonData);
        String linkid = jsonObject.getString("linkid");
        sourceService.deleteLink(Integer.valueOf(linkid));
        Map<String, Object> map = new HashMap<>();
        map.put("code", 1);
        return map;
    }



//数据包回显

    //预览数据
    @GetMapping(value = "/fileManage/{modeid}")
    public ModelAndView lookFile(Model model,@PathVariable("modeid") Integer modeid) {
        Iterable<DatamodelSource> datamodelSourceList1 = fileRepository.findAll();
        List<DatamodelSource> datamodelSourceList = new ArrayList<>();
        for (int i = 0 ,j = 0; i < ((List<DatamodelSource>) datamodelSourceList1).size(); i++){
            if (((List<DatamodelSource>) datamodelSourceList1).get(i).getModeid() == modeid){
                datamodelSourceList.add(j,((List<DatamodelSource>) datamodelSourceList1).get(i));
                j++;
            }
            else {
            }
        }
        model.addAttribute("datamodelSourceList", datamodelSourceList);
        model.addAttribute("modeid",modeid);
        return new ModelAndView("data/fileManage", "upFileList", model);
    }

    @RequestMapping("/dataarea/{modelid}")
    public ModelAndView dataarea(Model model, @PathVariable("modelid") Integer modelid) {
        model.addAttribute("modelid",modelid);
        model.addAttribute("datalinkInfo",sourceService.dataModelLink(modelid));
        return new ModelAndView("data/dataarea","model",model);
    }



    //文件上传，保存数据库,void方法需要加上ResponseBody否则会报错，不加会去找url作为接收页面，如果url不是页面就报错
    @ResponseBody
    @PostMapping("/saveFile")
    public void upFile(@RequestParam("filename") MultipartFile filename, @RequestParam("modeid") Integer modeid) throws Exception {

        String[] fileInformation = fileService.Upload(filename);
        String sourcename = fileInformation[0];
        String sourcepath = fileInformation[1];
        String sourcesize = fileInformation[2];
        ShellUtil.execCmd("mv /zt/"+sourcepath+" /var/data/celery/input/\n","root","/zt/IA","10.101.201.173",22);
        System.out.println(filename + "----" + sourcepath + "----" + sourcesize);
        DatamodelSource datamodelSource = new DatamodelSource();
        datamodelSource.setModeid(modeid);
        datamodelSource.setSourcename(sourcename);

        String sourcepath1 = "/var/data/celery/input/"+sourcename;
        datamodelSource.setSourcepath(sourcepath1);

        datamodelSource.setSourcesize(sourcesize);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        datamodelSource.setSourcetime(date);
        datamodelSource.setStatus("0");
/*        System.out.println(filename + "----" + sourcepath + "----" + sourcesize);
        DatamodelSource datamodelSource = new DatamodelSource();
        datamodelSource.setModeid(modeid);
        datamodelSource.setSourcename(sourcename);
        datamodelSource.setSourcepath(sourcepath);
        datamodelSource.setSourcesize(sourcesize);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        datamodelSource.setSourcetime(date);
        datamodelSource.setStatus("0");*/

        fileRepository.save(datamodelSource);
    }


    //删除数据
    @GetMapping("/fileDelete/{sourceid}")
    public ModelAndView fileDelete(@PathVariable("sourceid") Integer sourceid,Model model) {
        DatamodelSource datamodelSource = fileRepository.findById(sourceid).orElse(null);
        String filepath = datamodelSource.getSourcepath();
        System.out.println(filepath);

        //删除服务器记录
        fileRepository.deleteById(sourceid);
        //删除对应文件
        fileService.deleteFile(filepath);
        ModelAndView modelAndView=new ModelAndView();

        Iterable<DatamodelSource> datamodelSourceList = fileRepository.findAll();
        model.addAttribute("datamodelSourceList", datamodelSourceList);
      modelAndView.setViewName("data/fileManage");
        return modelAndView;
    }

    //搜索数据
    @GetMapping(value = "/dataSearch")
    public ModelAndView dataSearch(Model model, @RequestParam("search_text") String search_text) {
        //拼接模糊查询的通配符
        String res = "%" + search_text + "%";
        System.out.println(res);
        //返回查到的list
        /* Iterable<DatamodelSource> datamodelSourceList= fileRepository.findByfilenameLike(res);*/
        List<DatamodelSource> datamodelSourceList = sourceService.moHuDataSource(res);
        System.out.println(datamodelSourceList);
        model.addAttribute("datamodelSourceList", datamodelSourceList);
        return new ModelAndView("data/fileManage", "upFileList", model);
    }

    @GetMapping("/fileDown/{sourceid}")
    public String fileDown(HttpServletResponse response, String filePath, @PathVariable("sourceid") Integer dataid) {
        DatamodelSource datamodelSource = fileRepository.findById(dataid).orElse(null);
        String filepath = datamodelSource.getSourcepath();
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
}
