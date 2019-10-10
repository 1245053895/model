package com.xd.zt.controller.data.flow;


import com.xd.zt.domain.business.flow.AnalyseProcess;
import com.xd.zt.domain.data.*;
import com.xd.zt.service.data.FlowService;
import com.xd.zt.service.data.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@RequestMapping("/data")
@Controller
public class FlowList {
    @Autowired
    private FlowService flowService;
    @Autowired
    private SourceService sourceService;

    @RequestMapping("/getFlowList")
    public ModelAndView getFlowList() {
        ModelAndView modelAndView = new ModelAndView("data/maintool/flowList");
        List<AnalyseProcess> flowList = flowService.getFlowList();
        System.out.println(flowList.get(0).getEdittime());
        modelAndView.addObject("flowList", flowList);
        return modelAndView;
    }


    @RequestMapping("/delete/{Id}")
    public ModelAndView deleteProcessId(@PathVariable("Id") int Id) {
        try {
            flowService.deleteBlocksAndConnects(Id);
            flowService.deleteProcessName(Id);
        } catch (Exception e) {
            System.out.println(e);
        }
        ModelAndView modelAndView = new ModelAndView("data/maintool/flowList");
        List<AnalyseProcess> flowList = flowService.getFlowList();
        modelAndView.addObject("flowList", flowList);
        return modelAndView;
    }

    @RequestMapping("/deletex/{processid}")
    public ModelAndView deleteProcessIdx(Model model, @PathVariable("processid") int processid){
        try {
            flowService.dLdeleteBlocks(processid);
            flowService.dLdeleteProcessName(processid);
        }catch (Exception e){
            System.out.println(e);
        }
        DatamodelLink datamodelLink = flowService.processidTomodeid(processid);
        int modeid = datamodelLink.getModeid();
        flowService.deletdatalinkToProcessid(processid);
        List<DatamodelLink> datamodelLinkList = sourceService.dataModelLink(modeid);
        model.addAttribute("datamodelLinkList", datamodelLinkList);
        model.addAttribute("modeid", modeid);
        return new ModelAndView("data/datalinkshow", "modelModel", model);
    }


    @RequestMapping("/deletexx/{processid}")
    public ModelAndView deleteProcessIdxx(Model model,@PathVariable("processid") int processid){
        try {
            flowService.dardeleteBlocks(processid);
            flowService.dardeleteProcessName(processid);
        }catch (Exception e){
            System.out.println(e);
        }
        DatamodelArea xin = sourceService.darprocessidTomodeid(processid);
        int modelid =xin.getModeid();
        List<DatamodelArea> datamodelAreaList = sourceService.selectModelArea(modelid);
        model.addAttribute("datamodelAreaList", datamodelAreaList);
        model.addAttribute("modelid", modelid);
        return new ModelAndView("data/datalareashow", "modelModel", model);
    }






//    @RequestMapping("/revise/{Id}")
//    public ModelAndView getJsplumbModel(@PathVariable("Id") int id) {
//        ModelAndView modelAndView1 = new ModelAndView("/maintool/reviewsave");
//        List<JsPlumbBlock> blocks = flowService.getBlocks(id);
//        List<JsPlumbConnect> connects = flowService.getConnects(id);
//        modelAndView1.addObject("myblocks", blocks);
//        modelAndView1.addObject("myconnects", connects);
//        modelAndView1.addObject("Id",id);
//        return modelAndView1;
//    }

    @RequestMapping("/Linkviewx/{processid}")
    public ModelAndView dlgetJsplumbModel(Model model, @PathVariable("processid") int processid){
        ModelAndView modelAndView = new ModelAndView("data/Linkview");
//           从datamodollink中查processid到modeid
        DatamodelLink datamodelLink = sourceService.processidTomodeid(processid);
        int modeid = datamodelLink.getModeid();
//         BusinessQuestion businessQuestion =sourceService.modelidToscenceid(modeid);
//         int sceneid = businessQuestion.getSceneid();

        List<DlJsPlumbBlock> blocks = flowService.dlgetBlocks(processid);
        List<DlJsplumbConnect> connects = flowService.dlgetConnects(processid);

        modelAndView.addObject("myblocks", blocks);
        modelAndView.addObject("myconnects", connects);
        modelAndView.addObject("modeid",modeid);
        modelAndView.addObject("Id",processid);
        return modelAndView;
    }

    @RequestMapping("/areaViewx/{processid}")
    public ModelAndView dargetJsplumbModel(Model model, @PathVariable("processid") int processid){
        ModelAndView modelAndView = new ModelAndView("data/dataareaReview");
        //           从datamodolarea中查processid到modeid
        DatamodelArea xin = sourceService.darprocessidTomodeid(processid);
        int modelid =xin.getModeid();
        int linkid = xin.getLinkid();
        int arearid = xin.getAreaid();
        modelAndView.addObject("datalinkInfo",sourceService.dataModelLink(modelid));
        List<DarJsPlumbBlock> blocks = flowService.dargetBlocks(processid);
        List<DarJsplumbConnect> connects =flowService.dargetConnects(processid);
        modelAndView.addObject("myblocks", blocks);
        modelAndView.addObject("myconnects", connects);
        modelAndView.addObject("modeid",modelid);
        modelAndView.addObject("Id",processid);
        modelAndView.addObject("linkid",linkid);
        modelAndView.addObject("arearid",arearid);
        return modelAndView;
    }

    @GetMapping("/listsName")
    public ModelAndView getlistNameBill(@RequestParam(value = "name") String name) {
        ModelAndView modelAndView2 = new ModelAndView("data/maintool/flowList");
        List<AnalyseProcess> flowList = flowService.getAllList(name);
        modelAndView2.addObject("flowList", flowList);
        return modelAndView2;
    }


    //页面访问
    @RequestMapping(value = "/newflowPage")
    public String newflowController() {
        return "data/maintool/newflowPage";
    }


    //绑定信息页面
    @RequestMapping(value = "/blockinform")
    public String blockinformController() {
        return "data/maintool/blockinform";
    }
}