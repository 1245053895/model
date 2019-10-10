package com.xd.zt.controller.business;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.xd.zt.domain.business.BusinessQuestion;
import com.xd.zt.domain.business.BusinessScene;
import com.xd.zt.domain.business.SceneShow;
import com.xd.zt.domain.business.flow.JsPlumbBlock;
import com.xd.zt.service.business.BusinessObjectService;
import com.xd.zt.service.business.PictureService;
import com.xd.zt.service.business.QuestionBuildService;
import com.xd.zt.util.business.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/business")
@Controller

public class QuestionController {
    @Autowired
    private QuestionBuildService questionBuildService;
    @Autowired
    private BusinessObjectService businessObjectService;
    @Autowired
    private PictureService pictureService;
    @RequestMapping("/questionbuild/{id}")
    public ModelAndView selectbusinessquestion(Model model , @PathVariable("id") Integer businessid){
        model.addAttribute("questionBuild",questionBuildService.selectbusinessquestion(businessid.toString()));
        model.addAttribute("businessid",businessid);
        return new ModelAndView("business/questionbuild","modelModel",model); }

   @RequestMapping("/questiondescribe/{id}")  //businessid  业务场景下拉框展示
    public ModelAndView questiondescribe(Model model, @PathVariable("id") Integer businessid) {
        List<BusinessScene> businessSceneList= questionBuildService.selectAllSceneNode(businessid);
        model.addAttribute("businessSceneList", businessSceneList);
        model.addAttribute("businessid",businessid);
        return new ModelAndView("business/questiondescribe","modelModel",model);
    }



   @RequestMapping(value = "/sceneinformation/{sceneid}",method = RequestMethod.GET)  //左侧下面知识数据业务对象展示
    public ModelAndView selectObjectNode(Model model, @PathVariable("sceneid") int sceneid) {

     //  String sceneObjectName =  questionBuildService.selectObjectNode(blockid);
        List<SceneShow> objectNameList= businessObjectService.getObjectName(sceneid);
        List<SceneShow> dataTypeNameList=  businessObjectService.getDataTypeName(sceneid);
        List<SceneShow> knowledgeNameList  = businessObjectService.getKnowledgeName(sceneid);
        model.addAttribute("objectNameList",objectNameList);
       model.addAttribute("dataTypeNameList",dataTypeNameList);
       model.addAttribute("knowledgeNameList",knowledgeNameList);
       return new ModelAndView("business/sceneinformation","Model",model);
    }

    @RequestMapping("/sceneinformation1")
    public String sceneinformation1(){
        return "business/sceneinformation1";
    }


   @RequestMapping("/selectNodes/{sceneid}")  //右侧上方流程显示
   public ModelAndView selectNodes(Model model, @PathVariable("sceneid") Integer sceneid) {
       List<JsPlumbBlock> objectNodesList= questionBuildService.selectChildNodes(sceneid);  //查出流程，根据sceneid
       ModelAndView modelAndView=new ModelAndView();
       modelAndView.setViewName("business/questionprocess");
       modelAndView.addObject("objectNodesList",objectNodesList);
      return modelAndView;
    /*   model.addAttribute("scenename",scenename);
       model.addAttribute("objectNameList",objectNameList);
       model.addAttribute("dataTypeNameList",dataTypeNameList);
       model.addAttribute("knowledgeNameList",knowledgeNameList);*/
   }

    @RequestMapping("/sceneobject")
    public ModelAndView selectbusinessobject(Model model){
        model.addAttribute("sceneObject",businessObjectService.selectbusinessobject());
        return new ModelAndView("business/sceneobject","modelModel",model);
    }


    @RequestMapping("/savequestion")
    @ResponseBody
    public Map<String,Object> findprocessid(@RequestBody String jsonData) {
        JSONObject jsonObject = JSON.parseObject(jsonData);
        String businessid = jsonObject.getString("businessid");
        String blockid = jsonObject.getString("blockid");
        String sceneid = jsonObject.getString("sceneid");
        Map<String,Object> map=new HashMap<>();
        BusinessQuestion businessQuestion = questionBuildService.selectquestionbyblockid(blockid);
        if(businessQuestion==null){
     /*       String sceneid = questionBuildService.selectsceneid(sceneblockid);*/
            questionBuildService.insertquestion(businessid,blockid,sceneid);
            map.put("code",1);
        }
        else {
            map.put("code",0);
        }
        return map;
    }



    @RequestMapping("/questionprocess1")
    public String questionprocess1() {
        return "business/questionprocess1";
    }


    @RequestMapping("/deletequestion")
    @ResponseBody
    public String deletequestion(@RequestBody String jsonData) {
        JSONObject jsonObject = JSON.parseObject(jsonData);
        String questionid = jsonObject.getString("questionid");
        questionBuildService.deletequestion(questionid);
        return questionid;
    }
    @RequestMapping(value = "/questionDefine/{id}",method = RequestMethod.GET)  //business_question表的blockid
    public ModelAndView questionDefine(Model model, @PathVariable("id") Integer blockid){
        BusinessQuestion businessQuestion=new BusinessQuestion();
        businessQuestion.setBlockid(blockid);
        model.addAttribute("blockid",blockid);
        model.addAttribute("businessQuestion",businessQuestion);
        return new ModelAndView("business/questionDefine","modelModel",model);
    }

    @ResponseBody
    @RequestMapping("/upLoad")
    public Map<String,Object> upFile(String questionname, Integer blockid, @RequestParam(value = "file", required = false) MultipartFile file, String questiondes, String formulatdes, HttpSession session, HttpServletRequest request) throws Exception {
        questionBuildService.savedescribe(questionname,questiondes,formulatdes,blockid.toString());
        BusinessQuestion businessQuestion=new BusinessQuestion();
        businessQuestion.setBlockid(blockid);
        // 拿到文件名
        String filename = file.getOriginalFilename();
        // 存放上传图片的文件夹
        File fileDir = UploadUtils.getImgDirFile();
        String sqlPath = null;
        // 输出文件夹绝对路径  -- 这里的绝对路径是相当于当前项目的路径而不是“容器”路径
        System.out.println(fileDir.getAbsolutePath());
        try {
            // 构建真实的文件路径
            File newFile = new File(fileDir.getAbsolutePath() + File.separator + filename);
            System.out.println(newFile.getAbsolutePath());
            // 上传图片到 -》 “绝对路径”
            file.transferTo(newFile);
            sqlPath = "/uploadImage/"+filename;
            businessQuestion.setPicture(sqlPath);
            questionBuildService.updatePicture(businessQuestion);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }









    //文件上传，保存数据库,void方法需要加上ResponseBody否则会报错，不加会去找url作为接收页面，如果url不是页面就报错
/*    @ResponseBody
    @PostMapping("/upLoad")
    public Map<String,Object> upFile(String questionname,Integer blockid,@RequestParam(value = "file", required = false) MultipartFile file,String questiondes,String formulatdes,HttpSession session,HttpServletRequest request) throws Exception {
        // String[] fileInformation = pictureService.Upload(file);  将文件存放到target目录下，但是回显的时候比较难
        //String picture=fileInformation[2];
        Map<String,Object> map=new HashMap<>();
        BusinessQuestion businessQuestion=new BusinessQuestion();
        // businessQuestion.setPicture(picture);
        if(!file.isEmpty()){
            //String path = session.getServletContext().getRealPath("/uploadImage"); //获取resources/static下的文件夹和文件
            String path= request.getSession().getServletContext().getRealPath("") + "uploadImage";
//            String path= GetResource.class.getClassLoader().getResource("image/1.jpg").getPath();
            String realName=file.getOriginalFilename();//获得文件的名字
            String realPath=path+File.separator+realName;  //获得文件的路径和文件名
            String sqlPath = null;
            businessQuestion.setBlockid(blockid);
            if (!new File(path).exists()) {
                new File(path).mkdirs();
            }
            try {
                file.transferTo(new File(realPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
            sqlPath = "/uploadImage/"+realName;
            businessQuestion.setPicture(sqlPath);
            questionBuildService.updatePicture(businessQuestion);
            questionBuildService.savedescribe(questionname,questiondes,formulatdes,blockid.toString());
        }
        map.put("code",1);
         return map;

    }*/








   }


