package com.xd.zt.controller.business;


import com.xd.zt.domain.business.BusinessKnowledge;
import com.xd.zt.domain.business.BusinessScene;
import com.xd.zt.domain.business.BusinessType;
import com.xd.zt.domain.business.flow.JsPlumbBlock;
import com.xd.zt.repository.business.BusinessRepository;
import com.xd.zt.service.business.BusinessModelService;
import com.xd.zt.service.business.BusinessObjectService;
import com.xd.zt.service.business.ModelCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RequestMapping("/business")
@Controller
public class BusinessSceneController {

    @Autowired
    private BusinessObjectService businessObjectService;

    @Autowired
    private BusinessModelService businessModelService;
    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private ModelCreateService modelCreateService;
    /*业务场景*/
//    @RequestMapping("/scenebuild/{id}")
//    public ModelAndView scenebuild(Model model, @PathVariable("id") Integer id){
//        List<BusinessScene> businessNodeList=  businessModelService.selectAllNode();
//        model.addAttribute("businessNodeList",businessNodeList);
//        model.addAttribute("businessid",id);
//        return new ModelAndView("scenebuild","modelModel",model);
//    }
/*
    */
/*nodeextraction  业务节点抽取*//*

    @RequestMapping("/modelCreate/{id}")
    public ModelAndView modelCreate(Model model,@PathVariable("id") Integer id){
        model.addAttribute("businessid",id);
        return new ModelAndView("modelCreate","modelModel",model);
    }
*/



    /*nodeRefine  业务节点细化*/
    @RequestMapping("/nodeRefine/{id}")
    public ModelAndView nodeRefine(Model model, @PathVariable("id") Integer businessid){
    String sceneid = modelCreateService.selectMaxSceneId(businessid.toString());
    model.addAttribute("sceneid",sceneid);

        return new ModelAndView("business/maintool/nodeRefine","modelModel",model);

    }
    /*节点细化：节点对象，节点数据，节点知识*/
    /*业务节点对象*/
    @RequestMapping("/sceneobject/{sceneid}")
    public ModelAndView sceneobject(Model model, @PathVariable("sceneid") Integer sceneid){
        model.addAttribute("sceneObject",businessObjectService.selectbusinessobject());  //业务对象所有的对象记录

        String blockid = businessObjectService.secenIdByBlockId(sceneid);
        String[] jsplumbblockid = blockid.split(";");
        List<JsPlumbBlock> jsPlumbBlockList = new ArrayList<>();
        for(int i = 0; i < jsplumbblockid.length  ; i++){
            JsPlumbBlock jsPlumbBlock= businessObjectService.selectBlockContent(Integer.parseInt(jsplumbblockid[i])); //节点显示
            jsPlumbBlockList.add(jsPlumbBlock);
        }

        model.addAttribute("jsPlumbBlockList",jsPlumbBlockList);
        model.addAttribute("sceneid",sceneid);
        return new ModelAndView("business/sceneobject","modelModel",model);
    }

    /*节点数据*/
/*    @ResponseBody
    @RequestMapping("/nodeData")
    public ModelAndView nodeData(Model model,@RequestParam("jsplumbblockid") Integer jsplumbblockid,@RequestParam("checkID") Integer[] checkID){
        JsPlumbBlock jsPlumbBlock= businessObjectService.selectBlockContent(jsplumbblockid);  //获得节点显示     //根据blockid查询sceneid，还要接收objectid
     Integer sceneid= businessObjectService.secenIdByBlockId(jsplumbblockid);
     for (int i=0;i<checkID.length;i++){
         businessObjectService.insertSceneObject(sceneid,checkID[i]);
     }
        model.addAttribute("jsPlumbBlock",jsPlumbBlock);
        model.addAttribute("jsplumbblockid",jsplumbblockid);
        return new ModelAndView("nodeData","modelModel",model);

    }*/

    /*保存场景id和对象id到sceneobject中*/
@ResponseBody
    @RequestMapping("/saveSceneObject")
    public Map<String, Object> nodeData(Model model, @RequestParam("sceneid") Integer sceneid, @RequestParam("checkID") Integer[] checkID){

    model.addAttribute("sceneid",sceneid);

        Map<String,Object> map=new HashMap<String,Object>();
        for (int i=0;i<checkID.length;i++){
           businessObjectService.insertSceneObject(sceneid,checkID[i]);
        }
         if (businessObjectService.isExistInsertData(sceneid)==null){
             map.put("code",0);
         }else
             map.put("code",1);
        return map;
      /*   return new ModelAndView("nodeData","modelModel",model);*/

    }


    /*节点数据*/
    @RequestMapping("/nodeData/{sceneid}")
    public ModelAndView nodeData(Model model, @PathVariable("sceneid") Integer sceneid){
        String blockid = businessObjectService.secenIdByBlockId(sceneid);
        String[] jsplumbblockid = blockid.split(";");
        List<JsPlumbBlock> jsPlumbBlockList = new ArrayList<>();
        for(int i = 0; i < jsplumbblockid.length  ; i++){
            JsPlumbBlock jsPlumbBlock= businessObjectService.selectBlockContent(Integer.parseInt(jsplumbblockid[i])); //节点显示
            jsPlumbBlockList.add(jsPlumbBlock);
        }
        model.addAttribute("jsPlumbBlockList",jsPlumbBlockList);
        model.addAttribute("sceneid",sceneid);  //获得节点显示     //根据blockid查询sceneid，还要接收objectid
     /*Integer sceneid= businessObjectService.secenIdByBlockId(jsplumbblockid);*/
        /*获得业务数据类型信息*/
     List<BusinessType> businessTypeList= businessObjectService.allBusinessDataType();
        model.addAttribute("businessTypeList",businessTypeList);
        return new ModelAndView("business/nodeData","modelModel",model);

    }



    /*保存场景id和数据id到sceneData中*/
    @ResponseBody
    @RequestMapping("/saveSceneType")
    public Map<String, Object> saveSceneType(Model model, @RequestParam("sceneid") Integer sceneid, @RequestParam("checkID") Integer[] checkID){
//        Integer sceneid= businessObjectService.secenIdByBlockId(jsplumbblockid); //根据blockid查询sceneid，还要接收objectid
        model.addAttribute("sceneid",sceneid);
        Map<String,Object> map=new HashMap<String,Object>();
        for (int i=0;i<checkID.length;i++){
            businessObjectService.insertSceneData(sceneid,checkID[i]);
        }
        if (businessObjectService.isExistSceneData(sceneid)==null){
            map.put("code",0);
        }else
            map.put("code",1);
        return map;
        /*   return new ModelAndView("nodeData","modelModel",model);*/

    }


    /*nodeKnowledge  业务节点知识*/
    @RequestMapping("/nodeKnowledge/{sceneid}")
    public ModelAndView nodeKnowledge(Model model, @PathVariable("sceneid") Integer sceneid){
        String blockid = businessObjectService.secenIdByBlockId(sceneid);
        String[] jsplumbblockid = blockid.split(";");
        List<JsPlumbBlock> jsPlumbBlockList = new ArrayList<>();
        for(int i = 0; i < jsplumbblockid.length  ; i++){
            JsPlumbBlock jsPlumbBlock= businessObjectService.selectBlockContent(Integer.parseInt(jsplumbblockid[i])); //节点显示
            jsPlumbBlockList.add(jsPlumbBlock);
        }
        model.addAttribute("jsPlumbBlockList",jsPlumbBlockList);

       List<BusinessKnowledge> businessKnowledgeList= businessObjectService.AllBusinessKnowledge();
       model.addAttribute("businessKnowledgeList",businessKnowledgeList);

        model.addAttribute("sceneid",sceneid);
        return new ModelAndView("business/nodeKnowledge","modelModel",model);

    }


    /*保存场景id和知识id到sceneKnowledge中*/
    @ResponseBody
    @RequestMapping("/saveSceneKnowledge")
    public Map<String, Object> saveSceneKnowledge(Model model, @RequestParam("sceneid") Integer sceneid, @RequestParam("checkID") Integer[] checkID){
        //根据blockid查询sceneid，还要接收objectid
        model.addAttribute("sceneid",sceneid);
        Map<String,Object> map=new HashMap<String,Object>();
        for (int i=0;i<checkID.length;i++){
            businessObjectService.insertSceneKnowledge(sceneid,checkID[i]);
        }
        if (businessObjectService.isExistSceneKowledge(sceneid)==null){
            map.put("code",0);
        }else
            map.put("code",1);
        return map;
        /*   return new ModelAndView("nodeData","modelModel",model);*/

    }


    /*完成保存场景名*/

    @ResponseBody
    @RequestMapping("/saveSceneName")
    public BusinessScene saveSceneName(@RequestBody BusinessScene businessScene){
        Integer sceneid=businessScene.getSceneid();
        String scenename=businessScene.getScenename();
     businessObjectService.setScenenameBySceneId(sceneid,scenename);  /*保存场景名*/
        return businessScene;
    }

/*完成*/
 /*   @RequestMapping("/over/{sceneid}")
    public ModelAndView over(Model model,@PathVariable("sceneid") Integer sceneid){
//        List<BusinessScene> businessNodeList=  businessModelService.selectAllNode(businessid.toString());
        model.addAttribute("sceneid",sceneid);
        return new ModelAndView("scenebuild","modelModel",model);
    }*/

}
