package com.xd.zt.controller.business;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.xd.zt.domain.business.*;
import com.xd.zt.domain.business.flow.JsPlumbBlock;
import com.xd.zt.domain.business.flow.JsPlumbConnect;
import com.xd.zt.repository.business.BusinessRepository;
import com.xd.zt.repository.business.BusinessFileRepository;
import com.xd.zt.service.business.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/business")
public class BusinessModelController {
    @Autowired
    private BusinessObjectService businessObjectService;
    @Autowired
    private ModelCreateService modelCreateService;
    @Autowired
    private BusinessModelService businessModelService;
    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private BusinessFlowService flowService;

    @Autowired
    private BusinessFileRepository fileRepository;

    @Autowired
    private QuestionBuildService questionBuildService;
    @RequestMapping("/index")
    public ModelAndView selectAnalyz(Model model) {
        model.addAttribute("businessModels", businessModelService.selectbusinessmodel());
        return new ModelAndView("business/index", "modelModel", model);
    }

    @RequestMapping("/businessManage")
    public ModelAndView businessManage(Model model){
       List<BusinessModel> businessModelList= businessModelService.selectbusinessmodel();
       model.addAttribute("businessModelList",businessModelList);
       return new ModelAndView("business/businessManage","modelModel",model);
    }

    //业务搜索
    @RequestMapping("/businessSearch")
    public ModelAndView businessSearch(Model model,@RequestParam("search_text")String search_text){
        List<BusinessModel> businessModelList = businessModelService.searchBusiness(search_text);
        model.addAttribute("businessModelList",businessModelList);
        return new ModelAndView("business/businessManage","modelModel",model);
    }

    /*业务的删除*/
    @ResponseBody
    @RequestMapping("/businessDelete")
    public Map<String,Object> businessDelete(@RequestParam("businessid")  String businessid){
        Map<String,Object> map=new HashMap<>();
        Integer id=Integer.valueOf(businessid);  //将string类型转为Integer类型

        //删除当前业务主线
        String processid = modelCreateService.selectbusinessprocess(businessid);
        if (processid != null){
        flowService.deleteBlocksAndConnects(Integer.parseInt(processid));
        flowService.deleteProcessName(Integer.parseInt(processid));}

        //删除当前场景流程
        List<BusinessScene> businessSceneList = businessModelService.selectAllNode(businessid);
        for (int i = 0 ; i < businessSceneList.size(); i++){
            Integer sceneid = businessSceneList.get(i).getSceneid();
            Integer sceneprocessid = modelCreateService.reviewsceneprocess(sceneid);
            if (sceneprocessid != null) {
                flowService.deleteBlocksAndConnects(sceneprocessid);
                flowService.deleteProcessName(sceneprocessid);
            }
        }

        //删除当前业务
        businessObjectService.deleteBusinessModel(id);

    BusinessModel businessModel= businessObjectService.isExitBusinessModel(id);
    if (businessModel==null){
        map.put("code",1);
    }else {
        map.put("code",0);
    }
        return map;

    }




    @RequestMapping("/scenebuild/{id}")
    public ModelAndView scenebuild(Model model, @PathVariable("id") Integer id) {
        List<BusinessScene> businessNodeList = businessModelService.selectAllNode(id.toString());
        model.addAttribute("businessNodeList", businessNodeList);
        model.addAttribute("businessid", id);
        return new ModelAndView("business/scenebuild", "modelModel", model);
    }

    /*    *//*nodeextraction  业务节点抽取*//*
    @RequestMapping("/nodeextraction")
    public String nodeextraction(){
        return "nodeextraction";
    }*/

    /*nodeextraction  业务节点抽取*/
    @RequestMapping("/modelCreate/{id}")
    public ModelAndView modelCreate(Model model, @PathVariable("id") Integer id) {
        List<JsPlumbBlock> jsPlumbBlockList = modelCreateService.selectmodelcreate(id);
        model.addAttribute("jsPlumbBlockList", jsPlumbBlockList);
        model.addAttribute("businessid", id);
        return new ModelAndView("business/modelCreate", "modelModel", model);
    }

    @RequestMapping("/getblockid")
    @ResponseBody
    public Map<String,Object> getblockid(@RequestBody String jsonData) {
        Map<String,Object> map=new HashMap<>();
        JSONObject jsonObject = JSON.parseObject(jsonData);
        String blockid = jsonObject.getString("blockid");
        String businessid = jsonObject.getString("businessid");
//      BusinessScene businessScene=  modelCreateService.isXiHuaByBlockId(blockid);
//        if (businessScene==null){
            modelCreateService.insertblockid(businessid, blockid);
            map.put("code",1);
//        }else {
//            map.put("code",0);
//        }
         return map;
    }

    @RequestMapping("/findprocessid")
    @ResponseBody
    public String findprocessid(@RequestBody String jsonData) {
        JSONObject jsonObject = JSON.parseObject(jsonData);
        String businessid = jsonObject.getString("businessid");
        String processid = modelCreateService.selectbusinessprocess(businessid);
        return processid;
    }


    @PostMapping(value = "/business")
    @ResponseBody
    public BusinessModel businessModel(@RequestBody BusinessModel businessModel) {

        businessRepository.save(businessModel);
        return businessModel;
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "business/welcome";
    }

    @RequestMapping("/newprocess/{id}")
    public ModelAndView newflowController(Model model, @PathVariable("id") int id) {
        model.addAttribute("businessid",id);
        return new ModelAndView("business/maintool/newprocess","modelModel",model);
    }

    @RequestMapping("/processview/{Id}")
    public ModelAndView getJsplumbModel(Model model, @PathVariable("Id") int id){
        String businessid = modelCreateService.selectbusinessid(id);
        String businessprocessname = modelCreateService.selectbusinessblock(id);
        model.addAttribute("businessprocessname",businessprocessname);
        model.addAttribute("businessid",businessid);
        ModelAndView modelAndView1 = new ModelAndView("business/maintool/processview");
        List<JsPlumbBlock> blocks = flowService.getBlocks(id);
        List<JsPlumbConnect> connects=flowService.getConnects(id);
        modelAndView1.addObject("myblocks",blocks);
        modelAndView1.addObject("myconnects",connects);
        return modelAndView1;
    }

    @RequestMapping("/scenereview/{sceneid}")
    public ModelAndView getJsplumbModel(Model model, @PathVariable("sceneid") Integer id){
        String blockid = businessObjectService.secenIdByBlockId(id);
        String[] jsplumbblockid = blockid.split(";");
        List<JsPlumbBlock> jsPlumbBlockList = new ArrayList<>();
        for(int i = 0; i < jsplumbblockid.length  ; i++){
            JsPlumbBlock jsPlumbBlock= businessObjectService.selectBlockContent(Integer.parseInt(jsplumbblockid[i])); //节点显示
            jsPlumbBlockList.add(jsPlumbBlock);
        }
        model.addAttribute("jsPlumbBlockList",jsPlumbBlockList);
        model.addAttribute("sceneid",id);

        String sceneblock = modelCreateService.selectsceneblockid(id);
        Integer sceneprocessid = modelCreateService.reviewsceneprocess(id);
        model.addAttribute("sceneprocessid",sceneprocessid);
        model.addAttribute("sceneblock",sceneblock);
        ModelAndView modelAndView1 = new ModelAndView("business/maintool/sceneview");
        List<JsPlumbBlock> blocks = flowService.getBlocks(sceneprocessid);
        List<JsPlumbConnect> connects=flowService.getConnects(sceneprocessid);
        modelAndView1.addObject("myblocks",blocks);
        modelAndView1.addObject("myconnects",connects);

     /* List<SceneShow> sceneShowList =  businessObjectService.getSceneBySceneId(id);*/ /*回显场景*/
        String scenename=  businessObjectService.getSceneName(id);
        List<SceneShow> objectNameList= businessObjectService.getObjectName(id);
        List<SceneShow> dataTypeNameList=  businessObjectService.getDataTypeName(id);
        List<SceneShow> knowledgeNameList  = businessObjectService.getKnowledgeName(id);
        model.addAttribute("scenename",scenename);
        model.addAttribute("objectNameList",objectNameList);
        model.addAttribute("dataTypeNameList",dataTypeNameList);
        model.addAttribute("knowledgeNameList",knowledgeNameList);
        return modelAndView1;
    }

    //业务问题

    @RequestMapping("/questionView/{id}")   //id为questionid
    public ModelAndView questionView(Model model, @PathVariable("id") Integer questionid){
        List<BusinessQuestion> businessQuestionList = modelCreateService.selectquestion(questionid);
        BusinessQuestion businessQuestion = businessQuestionList.get(0);
        String path=businessQuestion.getPicture();
        File file=new File(path.trim());
        String pictureName=file.getName();
       String picture = "/uploadImage/"+pictureName;
        businessQuestion.setPicture(picture);
        model.addAttribute("businessQuestion",businessQuestion);
        String scenename = modelCreateService.selectnamebysceneid(questionid);
//        String sceneblock = modelCreateService.selectsceneblockbysceneid(questionid);
        String questionblock = modelCreateService.selectquestionblock(questionid);
        model.addAttribute("scenename",scenename);
//        model.addAttribute("sceneblock",sceneblock);
        model.addAttribute("questionblock",questionblock);
        model.addAttribute("questionid",questionid);
//        model.addAttribute("businessQuestionList",businessQuestionList);

        ModelAndView modelAndView1 = new ModelAndView("business/maintool/questionView");
        Integer sceneprocessid = modelCreateService.reviewsceneprocess(businessQuestion.getSceneid());
        model.addAttribute("sceneprocessid",sceneprocessid);
        List<JsPlumbBlock> blocks = flowService.getBlocks(sceneprocessid);
        List<JsPlumbConnect> connects=flowService.getConnects(sceneprocessid);
        modelAndView1.addObject("myblocks",blocks);
        modelAndView1.addObject("myconnects",connects);
        return modelAndView1;

    }

    @RequestMapping("/deletescene")
    @ResponseBody
    public String deletescene(@RequestBody String jsonData) {
        JSONObject jsonObject = JSON.parseObject(jsonData);
        String sceneid = jsonObject.getString("sceneid");

        //删除当前流程
        Integer processid = modelCreateService.reviewsceneprocess(Integer.parseInt(sceneid));
        if (processid != null) {
            flowService.deleteBlocksAndConnects(processid);
            flowService.deleteProcessName(processid);
        }
        modelCreateService.deletescene(sceneid);
        return sceneid;
    }

    @RequestMapping("/businessview/{businessid}")
    public ModelAndView businessview(Model model, @PathVariable("businessid") Integer businessid){
//        String businessid = modelCreateService.selectbusinessid(id);
        Integer id = businessModelService.selectprocesid(businessid);
        String businessprocessname = modelCreateService.selectbusinessblock(id);
        model.addAttribute("businessprocessname",businessprocessname);
        model.addAttribute("businessid",businessid);

        Iterable<BusinessFile> businessFileList= fileRepository.findAllByBusinessid(businessid);
        model.addAttribute("businessFileList",businessFileList);

        List<BusinessScene> businessNodeList = businessModelService.selectAllNode(businessid.toString());
        model.addAttribute("businessNodeList", businessNodeList);

        model.addAttribute("questionBuild",questionBuildService.selectbusinessquestion(businessid.toString()));


        ModelAndView modelAndView1 = new ModelAndView("business/maintool/businessview");
        List<JsPlumbBlock> blocks = flowService.getBlocks(id);
        List<JsPlumbConnect> connects=flowService.getConnects(id);
        modelAndView1.addObject("myblocks",blocks);
        modelAndView1.addObject("myconnects",connects);
        return modelAndView1;
    }
}