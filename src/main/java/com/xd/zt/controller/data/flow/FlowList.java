package com.xd.zt.controller.data.flow;


import com.xd.zt.domain.business.flow.AnalyseProcess;
import com.xd.zt.domain.data.*;
import com.xd.zt.repository.data.FileRepository;
import com.xd.zt.service.data.DataModelService;
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

import java.util.ArrayList;
import java.util.List;
@RequestMapping("/data")
@Controller
public class FlowList {
    @Autowired
    private FlowService flowService;
    @Autowired
    private SourceService sourceService;

    @Autowired
    private FileRepository fileRepository;
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

    @RequestMapping("/Linkviewx/{processid}")
    public ModelAndView dlgetJsplumbModel(Model model, @PathVariable("processid") int processid){
        ModelAndView modelAndView = new ModelAndView("data/Linkview");
//           从datamodollink中查processid到modeid
        DatamodelLink datamodelLink = sourceService.processidTomodeid(processid);
        int modeid = datamodelLink.getModeid();
//         BusinessQuestion businessQuestion =sourceService.modelidToscenceid(modeid);
//         int sceneid = businessQuestion.getSceneid();

        Iterable<DatamodelSource> datamodelSourceList1 = fileRepository.findAll();

        List<DatamodelSource> datamodelSourceList = new ArrayList<>();
        for (int i = 0 , j = 0; i < ((List<DatamodelSource>) datamodelSourceList1).size(); i++){
            if (((List<DatamodelSource>) datamodelSourceList1).get(i).getModeid() == modeid){
                datamodelSourceList.add(j,((List<DatamodelSource>) datamodelSourceList1).get(i));
                j++;
            }
            else {
            }
        }
        model.addAttribute("datamodelSourceList", datamodelSourceList);
        model.addAttribute("modeid",modeid);

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
        Integer modelid =xin.getModeid();
        Integer areaid=xin.getAreaid();
        Integer linkid= xin.getLinkid();

        List<DatamodelSource> datamodelSourceList=sourceService.getSourcesByStatusAndMoelId(modelid);
        DatamodelArea datamodelArea=sourceService.areaByAreaId(areaid);

        List<DatamodelInfo> datamodelInfoList = sourceService.infoByAreaId(areaid);
        modelAndView.addObject("datamodelSourceList", datamodelSourceList);
        modelAndView.addObject("datamodelArea", datamodelArea);
        modelAndView.addObject("datamodelInfoList", datamodelInfoList);
        Integer arearid = xin.getAreaid();
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