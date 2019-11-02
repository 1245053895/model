package com.xd.zt.controller.data.flow;

import com.alibaba.fastjson.JSONObject;

import com.xd.zt.domain.business.BusinessQuestion;
import com.xd.zt.domain.business.flow.AnalyseProcess;
import com.xd.zt.domain.business.flow.JsPlumbBlock;
import com.xd.zt.domain.business.flow.JsPlumbConnect;
import com.xd.zt.domain.business.flow.LinkBlockInform;
import com.xd.zt.domain.data.*;
import com.xd.zt.service.data.FlowService;
import com.xd.zt.service.data.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RequestMapping("/data")
@Controller
public class FlowController {
    @Autowired
    private FlowService flowService;
@Autowired
private SourceService sourceService;
    //保存
    @ResponseBody
    @PostMapping(value = "/newflows",consumes = "application/json;charset=utf-8")
    public String saveProcess(@RequestBody JSONObject jsonParam) throws Exception {
        List<JsPlumbConnect> connects = JSONObject.parseArray(jsonParam.getJSONArray("connects").toJSONString(),JsPlumbConnect.class);
        List<JsPlumbBlock> blocks = JSONObject.parseArray(jsonParam.getJSONArray("blocks").toJSONString(), JsPlumbBlock.class);
        String processName = jsonParam.get("name").toString();
//        System.out.println(blocks.get(0).getBlockName());
        String msg = flowService.saveProcess(processName, blocks, connects);
        return msg ;
    }


    //数据链再保存
    @ResponseBody
    @PostMapping(value = "/newflowsdl",consumes = "application/json;charset=utf-8")
    public String saveProcessdl(@RequestBody JSONObject jsonParam) throws Exception {
        String id = jsonParam.get("Id").toString();
        String modeid = jsonParam.get("modeid").toString();

        int Id = Integer.parseInt(id);
        int Modeid = Integer.parseInt(modeid);
//             flowService.dLdeleteBlocks(Id);
        flowService.dLdeleteProcessName(Id);

        List<DlJsplumbConnect> connects = JSONObject.parseArray(jsonParam.getJSONArray("connects").toJSONString(), DlJsplumbConnect.class);
        List<DlJsPlumbBlock> blocks = JSONObject.parseArray(jsonParam.getJSONArray("blocks").toJSONString(), DlJsPlumbBlock.class);
        String processName = jsonParam.get("name").toString();
        String msg = flowService.dlsaveProcess(processName, blocks, connects);
        DlAnalyseProcess dlAnalyseProcess = sourceService.selectlastprocessidx();
        String linkname = dlAnalyseProcess.getName();
        int processid =dlAnalyseProcess.getId();
        DatamodelLink datamodelLink = new DatamodelLink();
        datamodelLink.setLinkname(linkname);
        datamodelLink.setProcessid(processid);
        datamodelLink.setModeid(Modeid);
        sourceService.insertlinkModeidx(datamodelLink);

        List<DlJsplumbConnect> jsPlumbConnectList = flowService.selectRepeatConnection();
        for (int i= 0; i<jsPlumbConnectList.size();i++){
            String pageSourceId = jsPlumbConnectList.get(i).getPageSourceId();
            String pageTargetId = jsPlumbConnectList.get(i).getPageTargetId();
            String maxid =  flowService.selectMaxConnectId(pageSourceId,pageTargetId);
            String minid = flowService.selectMinConnectId(pageSourceId,pageTargetId);
            flowService.deleteGroupConnect(maxid,minid,pageSourceId,pageTargetId);
        }
        return msg ;
    }

    //数据区保存
    @ResponseBody
    @PostMapping(value = "/newflowsd2",consumes = "application/json;charset=utf-8")
    public String saveProcessd2(@RequestBody JSONObject jsonParam) throws Exception {
        String id = jsonParam.get("Id").toString();
        String Linkid = jsonParam.get("linkid").toString();
        String modeid = jsonParam.get("modelid").toString();
        Integer Id = Integer.parseInt(id);
        Integer Modeid = Integer.parseInt(modeid);
        Integer linkid = Integer.parseInt(Linkid);
        flowService.dardeleteProcessName(Id);
        List<DarJsplumbConnect> connects = JSONObject.parseArray(jsonParam.getJSONArray("connects").toJSONString(), DarJsplumbConnect.class);
        List<DarJsPlumbBlock> blocks = JSONObject.parseArray(jsonParam.getJSONArray("blocks").toJSONString(), DarJsPlumbBlock.class);
        String processName = jsonParam.get("name").toString();
        String msg = flowService.darsaveProcess(processName, blocks, connects);
        DarAnalyseProcess darAnalyseProcess = sourceService.selectlastprocessidxx();
        Integer processid = darAnalyseProcess.getId();
        String areaname = darAnalyseProcess.getName();

       /* DatamodelArea datamodelArea = new DatamodelArea();
        datamodelArea.setLinkid(linkid);
        datamodelArea.setModeid(Modeid);
        datamodelArea.setProcessid(processid);*/
  /*      datamodelArea.setAreaname(areaname);*/
        sourceService.insertdataLinkx(linkid.toString(),processid.toString(),modeid.toString());

        List<DarJsplumbConnect> jsPlumbConnectList = flowService.selectRepeatConnectionx();
        for (int i= 0; i<jsPlumbConnectList.size();i++){
            String pageSourceId = jsPlumbConnectList.get(i).getPageSourceId();
            String pageTargetId = jsPlumbConnectList.get(i).getPageTargetId();
            String maxid =  flowService.selectMaxConnectIdx(pageSourceId,pageTargetId);
            String minid = flowService.selectMinConnectIdx(pageSourceId,pageTargetId);
            flowService.deleteGroupConnectx(maxid,minid,pageSourceId,pageTargetId);
        }
        return msg ;
    }


    //数据链保存
    @RequestMapping("/dataLinkFirstflow/{modeid}")
    public ModelAndView dataLink(@PathVariable("modeid") Integer modeid) throws Exception {
        //modeid查sceneid查id
        BusinessQuestion businessQuestion =sourceService.modelidToscenceid(modeid);
        int sceneid = businessQuestion.getSceneid();

        AnalyseProcess analyseProcess  =flowService.sceneidToId(sceneid);
        int id = analyseProcess.getId();

        List<DlJsPlumbBlock> blocks = flowService.getBlocks(id);
        List<DlJsplumbConnect> connects = flowService.getConnects(id);
        String processName = analyseProcess.getName();
        String msg = flowService.dlsaveProcess(processName, blocks, connects);


        List<DlJsplumbConnect> jsPlumbConnectList = flowService.selectRepeatConnection();
        for (int i= 0; i<jsPlumbConnectList.size();i++){
            String pageSourceId = jsPlumbConnectList.get(i).getPageSourceId();
            String pageTargetId = jsPlumbConnectList.get(i).getPageTargetId();
            String maxid =  flowService.selectMaxConnectId(pageSourceId,pageTargetId);
            String minid = flowService.selectMinConnectId(pageSourceId,pageTargetId);
            flowService.deleteGroupConnect(maxid,minid,pageSourceId,pageTargetId);
        }

        DlAnalyseProcess dlAnalyseProcess = sourceService.selectlastprocessidx();
        ModelAndView modelAndView = new ModelAndView("data/dataLink");
        int processid = dlAnalyseProcess.getId();
        List<DlJsPlumbBlock> blocks1 = flowService.dlgetBlocks(processid);
        List<DlJsplumbConnect> connects1 = flowService.dlgetConnects(processid);

        modelAndView.addObject("myblocks", blocks1);
        modelAndView.addObject("myconnects", connects1);
        modelAndView.addObject("modeid",modeid);
        modelAndView.addObject("Id",processid);
        return modelAndView;
    }

    //数据区保存
    @RequestMapping("/dataareaflowx/{processid}")
    public ModelAndView dataareax(@PathVariable("processid") Integer id) throws Exception {

        DatamodelLink datamodelLink =flowService.processidTomodeid(id);
        int linkid = datamodelLink.getLinkid();
        int modeid =datamodelLink.getModeid();

        List<DarJsPlumbBlock> blocks = flowService.darGetBlocks(id);
        List<DarJsplumbConnect> connects = flowService.darGetConnects(id);
        String processName = "xxxxx";
        String msg = flowService.darsaveProcess(processName, blocks, connects);

        List<DarJsplumbConnect> jsPlumbConnectList = flowService.selectRepeatConnectionx();
        for (int i= 0; i<jsPlumbConnectList.size();i++){
            String pageSourceId = jsPlumbConnectList.get(i).getPageSourceId();
            String pageTargetId = jsPlumbConnectList.get(i).getPageTargetId();
            String maxid =  flowService.selectMaxConnectIdx(pageSourceId,pageTargetId);
            String minid = flowService.selectMinConnectIdx(pageSourceId,pageTargetId);
            flowService.deleteGroupConnectx(maxid,minid,pageSourceId,pageTargetId);
        }
        DarAnalyseProcess darAnalyseProcess = sourceService.selectlastprocessidxx();
        ModelAndView modelAndView = new ModelAndView("data/dataarea");
        int processid = darAnalyseProcess.getId();
        ;
        List<DarJsPlumbBlock> blocks1 = flowService.dargetBlocks(processid);
        List<DarJsplumbConnect> connects1 =flowService.dargetConnects(processid);

        modelAndView.addObject("datalinkInfo", sourceService.dataModelLink(modeid));
        modelAndView.addObject("myblocks", blocks1);
        modelAndView.addObject("myconnects", connects1);
        modelAndView.addObject("modelid",modeid);
        modelAndView.addObject("Id",processid);
        modelAndView.addObject("linkid",linkid);
        return modelAndView;
    }






    @ResponseBody
    @PostMapping(value = "/newflowsx",consumes = "application/json;charset=utf-8")
    public String saveProcessx(@RequestBody JSONObject jsonParam) throws Exception {
        String id = jsonParam.get("Id").toString();
        int Id =Integer.parseInt(id);
        flowService.deleteBlocksAndConnects(Id);
        flowService.deleteProcessName(Id);
        List<JsPlumbConnect> connects = JSONObject.parseArray(jsonParam.getJSONArray("connects").toJSONString(),JsPlumbConnect.class);
        List<JsPlumbBlock> blocks = JSONObject.parseArray(jsonParam.getJSONArray("blocks").toJSONString(), JsPlumbBlock.class);
        String processName = jsonParam.get("name").toString();
        String msg = flowService.saveProcess(processName, blocks, connects);
        return msg ;
    }



    //节点信息保存
    @ResponseBody
    @PostMapping(value = "/blocklinkinform")
    public String addlinkinform(@RequestParam("parameter1") String parameter1, @RequestParam("parameter2") String parameter2, @RequestParam("uuid") String uuid){
        LinkBlockInform linkBlockInform = new LinkBlockInform();
        linkBlockInform.setParameter1(parameter1);
        linkBlockInform.setParameter2(parameter2);
        linkBlockInform.setUuid(uuid);
        LinkBlockInform linkinformExist = flowService.getLinkBlockInform(uuid);
        if (linkinformExist == null) {
            flowService.addLinkBlockInforms(linkBlockInform);
            System.out.println("12");
        } else {
            flowService.modifyLinkBlockInforms(linkBlockInform);
            System.out.println("34");
        }
        return "保存成功";
    }

    //节点信息回显
    @ResponseBody
    @PostMapping(value = "/getNodeInfo")
    public Map<String,String> getNodeInfo(@RequestParam("uuid") String uuid){
//        System.out.println("getNode");
        LinkBlockInform linkinformExist = flowService.getLinkBlockInform(uuid);
        Map map = new HashMap();
        if (linkinformExist == null) {
            map.put("parameter1","null");
            map.put("parameter2","null");
        } else {
            map.put("parameter1",linkinformExist.getParameter1());
            map.put("parameter2",linkinformExist.getParameter2());
        }
        return map;
    }
}

