package com.xd.zt.controller.business.flow;



import com.xd.zt.domain.business.flow.AnalyseProcess;
import com.xd.zt.domain.business.flow.JsPlumbBlock;
import com.xd.zt.domain.business.flow.JsPlumbConnect;
import com.xd.zt.service.business.BusinessFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/business")
@Controller
public class BusinessFlowList {
    @Autowired
    private BusinessFlowService flowService;

    @RequestMapping("/getFlowList")
    public ModelAndView getFlowList(){
        ModelAndView modelAndView = new ModelAndView("business/maintool/flowList");
        List<AnalyseProcess> flowList = flowService.getFlowList();
        System.out.println(flowList.get(0).getEdittime());
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
        ModelAndView modelAndView = new ModelAndView("business/maintool/flowList");
        List<AnalyseProcess> flowList = flowService.getFlowList();
        modelAndView.addObject("flowList", flowList);
        return modelAndView;
    }



    @RequestMapping("/revise/{Id}")
    public ModelAndView getJsplumbModel(@PathVariable("Id") int id) {
        ModelAndView modelAndView1 = new ModelAndView("business/maintool/newflowPage");
        List<JsPlumbBlock> blocks = flowService.getBlocks(id);
        List<JsPlumbConnect> connects = flowService.getConnects(id);
        modelAndView1.addObject("myblocks", blocks);
        modelAndView1.addObject("myconnects", connects);
        return modelAndView1;
    }

    @GetMapping("/listsName")
    public ModelAndView getlistNameBill(@RequestParam(value = "name") String name) {
        ModelAndView modelAndView2 = new ModelAndView("business/maintool/flowList");
        List<AnalyseProcess> flowList = flowService.getAllList(name);
        modelAndView2.addObject("flowList", flowList);
        return modelAndView2;
    }


    //页面访问
    @RequestMapping(value = "/newflowPage")
    public String newflowController() {
        return "business/maintool/newflowPage";
    }


    //绑定信息页面
    @RequestMapping(value = "/blockinform")
    public String blockinformController() {
        return "business/maintool/blockinform";
    }
}