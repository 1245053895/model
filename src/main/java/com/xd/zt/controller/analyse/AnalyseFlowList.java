package com.xd.zt.controller.analyse;


import com.xd.zt.domain.analyse.*;
import com.xd.zt.domain.business.flow.AnalyseProcess;
import com.xd.zt.domain.business.flow.JsPlumbBlock;
import com.xd.zt.domain.business.flow.JsPlumbConnect;
import com.xd.zt.service.analyse.AnalyseModelService;
import com.xd.zt.service.analyse.AnalyseFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@RequestMapping("/analyse")
@Controller
public class AnalyseFlowList {
    @Autowired
    private AnalyseFlowService flowService;
    @Autowired
    private AnalyseModelService analyseModelService;

    @RequestMapping("/getFlowList")
    public ModelAndView getFlowList() {
        ModelAndView modelAndView = new ModelAndView("analyse/flowList");
        List<AnalyseProcess> flowList = flowService.getFlowList();
        //System.out.println(flowList.get(0).getEdittime());
        modelAndView.addObject("flowList", flowList);
        return modelAndView;
    }


    @RequestMapping("/delete/{Id}")
    public ModelAndView deleteProcessId(@PathVariable("Id") int Id) {
        try {
//            flowService.deleteBlocksAndConnects(Id);
            flowService.deleteProcessName(Id);
        } catch (Exception e) {
            System.out.println(e);
        }
        ModelAndView modelAndView = new ModelAndView("analyse/flowList");
        List<AnalyseProcess> flowList = flowService.getFlowList();
        modelAndView.addObject("flowList", flowList);
        return modelAndView;
    }



    @RequestMapping("/revise/{flowprocessid}")
    public ModelAndView getJsplumbModel(Model model,@PathVariable("flowprocessid") Integer flowprocessid) {
        ModelAndView modelAndView1 = new ModelAndView("analyse/flowProcessReview","Modelmodel",model);

        AnalyseFlowprocess analyseFlowprocess = analyseModelService.selectProcessId(flowprocessid);
        Integer Id = analyseFlowprocess.getProcessid();
        Integer modelid = analyseFlowprocess.getModelid();

        List<JsPlumbBlock> blocks = flowService.getBlocks(Id);
        List<JsPlumbConnect> connects = flowService.getConnects(Id);

        model.addAttribute("flowprocessid", flowprocessid);
        model.addAttribute("myblocks", blocks);
        model.addAttribute("myconnects", connects);
        model.addAttribute("analyseFlowprocess",analyseFlowprocess);
        model.addAttribute("modelid",modelid);
        return modelAndView1;
    }
//    @RequestMapping("/reviseUnit/{processid}")
//    public ModelAndView reviseUnit(@PathVariable("processid") int Id) {
//        ModelAndView modelAndView1 = new ModelAndView("analyse/unitFlowReview");
//        AnalyseUnit analyseUnit = flowService.selectUnitid(Id);
//        //System.out.println(analyseUnit.getUnitid());
//        AnalyseFlowprocess analyseFlowprocess = flowService.slectProcessidToModelid(Id);
//        List<JsPlumbBlock> blocks = flowService.getBlocks(Id);
//        List<JsPlumbConnect> connects = flowService.getConnects(Id);
//        //System.out.println(blocks);
//         modelAndView1.addObject("unitid",analyseUnit.getUnitid() );
//        modelAndView1.addObject("myblocks", blocks);
//        modelAndView1.addObject("myconnects", connects);
//
//        return modelAndView1;
//    }


    @GetMapping("/listsName")
    public ModelAndView getlistNameBill(@RequestParam(value = "name") String name) {
        ModelAndView modelAndView2 = new ModelAndView("analyse/flowList");
        List<AnalyseProcess> flowList = flowService.getAllList(name);
        modelAndView2.addObject("flowList", flowList);
        return modelAndView2;
    }


    //页面访问
//    @RequestMapping(value = "/unitflowPage/{unitname}")
//    public ModelAndView unitflowPage(Model model,@PathVariable("unitname") String unitname){
//        AnalyseUnit analyseUnit = analyseModelService.selectUnitid(unitname);
//        int unitid = analyseUnit.getUnitid();
//        System.out.println(unitid);
//        model.addAttribute("unitid",unitid);
//        AnalyseUnit  analyseUnitFlow =  flowService.selectAnalyseUnit(unitid);
//        int modelid = analyseUnitFlow.getModelid();
//        model.addAttribute("modelid",modelid);
//        System.out.println(modelid);
//        return new ModelAndView("analyse/unitflowPage","model",model);
//    }

    @RequestMapping(value = "/newflowPage/{modelid}")
    public ModelAndView newflowPage(Model model, @PathVariable("modelid") int modelid){
        model.addAttribute("modelid",modelid);
        return new ModelAndView("analyse/newflowPage","model",model);
    }


    //绑定信息页面
    @RequestMapping(value = "/blockinform")
    public String blockinformController() {
        return "analyse/blockinform";
    }
}