package com.xd.zt.controller.analyse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.xd.zt.domain.analyse.*;
import com.xd.zt.domain.business.BusinessQuestion;
import com.xd.zt.domain.business.flow.AnalyseProcess;
import com.xd.zt.domain.business.flow.JsPlumbBlock;
import com.xd.zt.domain.business.flow.JsPlumbConnect;
import com.xd.zt.service.algorithm.AlgorithmDebugService;
import com.xd.zt.service.algorithm.AlgorithmUpdateService;
import com.xd.zt.service.analyse.AnalyseModelService;
import com.xd.zt.service.analyse.AnalyseService;
import com.xd.zt.service.business.BusinessFlowService;
import com.xd.zt.service.business.ModelCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.*;

@Controller
@RequestMapping("/analyse")
public class AnalyseModelController {

    @Autowired
   private AnalyseModelService analyseModelService;
    @Autowired
    private AnalyseService analyseService;

    @Autowired
    private ModelCreateService modelCreateService;

    @Autowired
   private BusinessFlowService flowService;

    @Autowired
    private AlgorithmDebugService algorithmDebugService;
    @Autowired
    private AlgorithmUpdateService algorithmUpdateService;

    @RequestMapping("/index")
    public ModelAndView selectAnalyz(Model model) {


        model.addAttribute("questionList", analyseModelService.selectqueslist());
        return new ModelAndView("analyse/index", "modelModel", model);
    }
    @RequestMapping("/analyseManage")
    public ModelAndView businessManage(Model model){
        List<AnalyseModel> analyseModelList= analyseModelService.selectqueslist();
        model.addAttribute("analyseModelList",analyseModelList);
        return new ModelAndView("analyse/analyseManage","modelModel",model);
    }

    @ResponseBody
    @RequestMapping("/analyseDelete")
    public String analyseDelete(@RequestParam("modelid")  String modelid){
        Map<String,Object> map=new HashMap<>();
        Integer id= Integer.valueOf(modelid);  //将string类型转为Integer类型
        analyseModelService.deleteAnalyse(id);
    return modelid;
    }

    @RequestMapping("/quesListshow")
    @ResponseBody
    public AnalyseModel analyseModel(@RequestBody AnalyseModel analyseModel) {
        if (analyseModel.getQuestionid() != null){
            int questionid = analyseModel.getQuestionid();
            String name = analyseModel.getName();
            String questioname=  analyseModelService.selectQuestionName(questionid);
            analyseModelService.insertAnalyseModel(questionid,questioname,name);
            System.out.printf("基于业务问题新建");
            return analyseModel;
        }
        else {
            String name = analyseModel.getName();
            analyseModelService.insertAnalyseModel(null,null,name);
            System.out.printf("直接新建");
            return analyseModel;
        }

    }


    @ResponseBody
    @RequestMapping("/selectQuestion")
    public Map<String, Object> selectQuestion(@RequestParam Integer businessid){
        List<BusinessQuestion> listQuestion = analyseModelService.selectquestion(businessid);
        Map<String,Object> map = new HashMap<>();
        Iterator<BusinessQuestion> iterator = listQuestion.iterator();
        while (iterator.hasNext()){
            BusinessQuestion bq =  iterator.next();
            map.put(String.valueOf(bq.getQuestionid()),bq.getQuestioname());
        }
        //System.out.println(map);
        return map;
    }

    @RequestMapping("/welcome")
    public ModelAndView selectBusiness(Model model){
        model.addAttribute("businessModelList",analyseModelService.selectBusiness());
        return new ModelAndView("analyse/welcome","modelModel",model);
    }

    @RequestMapping("/wel1")
    public ModelAndView wel(Model model){
        List<Algorithm> algorithmList = algorithmDebugService.selectAlgorithm();
        List<Algorithm> arr1 = new ArrayList<Algorithm>();
        List<Algorithm> arr2 = new ArrayList<Algorithm>();
        List<Algorithm> arr3 = new ArrayList<Algorithm>();
        for (Algorithm algorithm : algorithmList) {
            String sss =algorithm.getAlgorithmlabel();
            if(sss.equals("行业通用")){
                arr2.add(algorithm);
            }else {
                if(sss.equals("行业专用")){
                    arr1.add(algorithm);
                }else {
                    arr3.add(algorithm);
                }
            }
        }
        model.addAttribute("algorithmList1",arr1);
        model.addAttribute("algorithmList2",arr2);
        model.addAttribute("algorithmList3",arr3);
        return new ModelAndView("/wel1","modelModel",model);
    }

    @RequestMapping("/analyseReview/{modelid}")
    public ModelAndView analyseReview(Model model,@PathVariable("modelid") Integer modelid){
        model.addAttribute("modelid",modelid);

        AnalyseModel analyseModel = analyseModelService.selectquestionid(modelid);
        model.addAttribute("analyseModel",analyseModel);

        List<AnalyseFlowprocess> analyseFlowprocessList = analyseModelService.selectAllFlowProcess(modelid);
        model.addAttribute("analyseFlowprocessList",analyseFlowprocessList);

        List<AnalyseModelProcess> analyseModelList = analyseService.selectAnalyseModel(modelid);
        model.addAttribute("analyseModelList",analyseModelList);


        return new ModelAndView("analyse/analyseReview","modelModel",model);
    }

    @RequestMapping("/analyseProcessBuild/{modelid}")
    public ModelAndView analyseProcessBuild(Model model,@PathVariable("modelid") Integer modelid){
        model.addAttribute("modelid",modelid);
            //SLECT方法
        List<AnalyseFlowprocess> analyseFlowprocessList = analyseModelService.selectAllFlowProcess(modelid);
        model.addAttribute("analyseFlowprocessList",analyseFlowprocessList);
        return new ModelAndView("analyse/analyseProcessBuild","modelModel",model);
    }

    @RequestMapping("/analyseProcessFirst/{modelid}")
    public ModelAndView locateQuestion(Model model,@PathVariable("modelid") Integer modelid){
        //System.out.println(modelid);
        model.addAttribute("modelid",modelid);

        AnalyseModel analyseModel = analyseModelService.selectquestionid(modelid);

        if (analyseModel.getQuestioname() != null) {
            Integer questionid = analyseModel.getQuestionid();
            List<BusinessQuestion> businessQuestionList = modelCreateService.selectquestion(questionid);
            BusinessQuestion businessQuestion = businessQuestionList.get(0);
            String path = businessQuestion.getPicture();
            File file = new File(path.trim());
            String pictureName = file.getName();
            String picture = "/uploadImage/" + pictureName;
            businessQuestion.setPicture(picture);
            model.addAttribute("businessQuestion", businessQuestion);

            String scenename = modelCreateService.selectnamebysceneid(questionid);
//        String sceneblock = modelCreateService.selectsceneblockbysceneid(questionid);
            String questionblock = modelCreateService.selectquestionblock(questionid);
            model.addAttribute("scenename", scenename);
//        model.addAttribute("sceneblock",sceneblock);
            model.addAttribute("questionblock", questionblock);
            model.addAttribute("questionid", questionid);
//        model.addAttribute("businessQuestionList",businessQuestionList);

            ModelAndView modelAndView1 = new ModelAndView("analyse/questionView");
            Integer sceneprocessid = modelCreateService.reviewsceneprocess(businessQuestion.getSceneid());
            model.addAttribute("sceneprocessid", sceneprocessid);
            List<JsPlumbBlock> blocks = flowService.getBlocks(sceneprocessid);
            List<JsPlumbConnect> connects = flowService.getConnects(sceneprocessid);
            modelAndView1.addObject("myblocks", blocks);
            modelAndView1.addObject("myconnects", connects);
            return modelAndView1;
        }
        else {
            return new ModelAndView("analyse/newflowPage","model",model);
        }
    }



//    @RequestMapping("/analyseUnitBuild/{modelid}")
//    public  ModelAndView analyseUnitBuild(Model model,@PathVariable("modelid") Integer modelid){
//        model.addAttribute("analyseUnitList",analyseModelService.selectAnalyseUnit());
//        model.addAttribute("modelid",modelid);
//        return new ModelAndView("analyse/analyseUnitBuild","modelModel",model);
//    }

    @RequestMapping("/analyseUnit/{modelid}")
    public  ModelAndView analyseUnit(Model model,@PathVariable("modelid") Integer modelid){
        List<AnalyseProcess> analyseFlowList = analyseModelService.selectAnalyseFlow(modelid);
        model.addAttribute("modelid",modelid);
        model.addAttribute("analyseFlowList",analyseFlowList);
        return new ModelAndView("analyse/analyseUnit","modelModel",model);
    }

//    @RequestMapping("/flowReview/{modelid}")
//    public ModelAndView flowReview(Model model,@PathVariable("modelid") Integer modelid){
//        model.addAttribute("modelid",modelid);
//        return new ModelAndView("analyse/flowReview","modelModel",model);
//    }

//分析单元保存
    @ResponseBody
    @RequestMapping("/business")
    public void business(@RequestBody String jsonData) {
        JSONObject jsonObject = JSON.parseObject(jsonData);
        String modelid= jsonObject.getString("modelid");
        String dataPacket= jsonObject.getString("dataPacket");
        String unitname= jsonObject.getString("unitname");
        String  resultdefine= jsonObject.getString("resultdefine");

        Integer flowprocessid = analyseModelService.selectLastflowprocessid(Integer.parseInt(modelid));
        analyseModelService.savedataPacket(dataPacket,resultdefine,unitname,flowprocessid.toString());
//        analyseModelService.savedataPacket(modelid,dataPacket,unitname,resultdefine);
        //System.out.println(unitid);
        //model.addAttribute("unitid",unitid);
    }


    @RequestMapping("/exampleList")
    public ModelAndView getListNameBill(Model model,@RequestParam("name") String name,@RequestParam("modelid") String modelid){
        model.addAttribute("name",name);
        List<AnalyseInstance>  analyseInstanceList= analyseModelService.selectAll(name,modelid);
        model.addAttribute("analyseInstanceList",analyseInstanceList);
        model.addAttribute("modelid",modelid);
        return new ModelAndView("analyse/exampleList","modelModel",model);
    }

    @RequestMapping("/exampleListSearch")
    public ModelAndView getListName(Model model,@RequestParam("name") String name){
        model.addAttribute("name",name);
        List<AnalyticsTask>  analyticsTaskList= analyseModelService.selectS(name);
        model.addAttribute("analyticsTaskList",analyticsTaskList);
        return new ModelAndView("analyse/taskList","modelModel",model);
    }

    //搜索数据
//    @GetMapping(value = "/dataSearch")
//    public ModelAndView dataSearch(Model model, @RequestParam("modelinstancename") String modelinstancename){
//        //拼接模糊查询的通配符
//        String res = "%" +modelinstancename+"%";
//        System.out.println(res);
//        //返回查到的list
//        Iterable<AnalyseInstance> analyseInstanceList= fileRepository.findByfilenameLike(res);
//        System.out.println(businessFileList);
//        model.addAttribute("businessFileList",businessFileList);
//        return new ModelAndView("/fileManage","upFileList",model);
//    }

}


