package com.xd.zt.controller.analyse;

import com.alibaba.fastjson.JSONObject;

import com.xd.zt.domain.business.flow.JsPlumbBlock;
import com.xd.zt.domain.business.flow.JsPlumbConnect;
import com.xd.zt.domain.business.flow.LinkBlockInform;
import com.xd.zt.service.analyse.AnalyseFlowService;
import com.xd.zt.service.business.BusinessFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RequestMapping("/analyse")
@Controller
public class AnalyseFlowController {
    @Autowired
    private AnalyseFlowService analyseFlowService;

    @Autowired
    private BusinessFlowService flowService;

    //保存
    @ResponseBody
    @PostMapping(value = "/newflows",consumes = "application/json;charset=utf-8")
    public String saveProcess(@RequestBody JSONObject jsonParam) throws Exception {
        List<JsPlumbConnect> connects = JSONObject.parseArray(jsonParam.getJSONArray("connects").toJSONString(),JsPlumbConnect.class);
        List<JsPlumbBlock> blocks = JSONObject.parseArray(jsonParam.getJSONArray("blocks").toJSONString(), JsPlumbBlock.class);
        String processName = jsonParam.get("name").toString();
        //System.out.println(processName);
        String modelid1 = jsonParam.get("modelid").toString();
        //System.out.println(modelid1);
        int modelid = Integer.parseInt( modelid1) ;
        String val = analyseFlowService.delSaveBlock(connects);
        if (val ==null){
            String msg = analyseFlowService.saveProcess(processName, blocks, connects,modelid);

            //删除重复线条
            List<JsPlumbConnect> jsPlumbConnectList = flowService.selectRepeatConnection();
            for (int i= 0; i<jsPlumbConnectList.size();i++){
                String connectionId = jsPlumbConnectList.get(i).getConnectionId();
                String maxid =  flowService.selectMaxConnectId(connectionId);
                String minid = flowService.selectMinConnectId(connectionId);
                flowService.deleteGroupConnect(maxid,minid);
            }

            return msg ;
        }else {
            String msg = analyseFlowService.saveProcess(processName, blocks, connects,modelid);
            //删除重复线条
            List<JsPlumbConnect> jsPlumbConnectList = flowService.selectRepeatConnection();
            for (int i= 0; i<jsPlumbConnectList.size();i++){
                String connectionId = jsPlumbConnectList.get(i).getConnectionId();
                String maxid =  flowService.selectMaxConnectId(connectionId);
                String minid = flowService.selectMinConnectId(connectionId);
                flowService.deleteGroupConnect(maxid,minid);
            }

            return msg+";" ;
        }
    }



    //分析单元流程保存
//    @ResponseBody
//    @PostMapping(value = "/unitflowPage",consumes = "application/json;charset=utf-8")
//    public String saveunitflowPage(@RequestBody JSONObject jsonParam) throws Exception {
//        List<JsPlumbConnect> connects = JSONObject.parseArray(jsonParam.getJSONArray("connects").toJSONString(),JsPlumbConnect.class);
//        List<JsPlumbBlock> blocks = JSONObject.parseArray(jsonParam.getJSONArray("blocks").toJSONString(), JsPlumbBlock.class);
//        String processName = jsonParam.get("name").toString();
//        System.out.println(processName);
//        String unitid1 = jsonParam.get("unitid").toString();
//        //System.out.println(unitid1);
//        int unitid = Integer.parseInt( unitid1) ;
//        String val = flowService.delSaveBlock(connects);
//
//        if (val ==null){
//            String msg = flowService.saveunitflowPage(processName, blocks, connects,unitid);
//            return msg ;
//        }else {
//            String msg = flowService.saveunitflowPage(processName, blocks, connects,unitid);
//           return msg+";" ;
//       }
//    }





    //节点信息保存
    @ResponseBody
    @PostMapping(value = "/blocklinkinform")
    public String addlinkinform(@RequestParam("parameter1") String parameter1,@RequestParam("parameter2") String parameter2,@RequestParam("uuid") String uuid){
        LinkBlockInform linkBlockInform = new LinkBlockInform();
        linkBlockInform.setParameter1(parameter1);
        linkBlockInform.setParameter2(parameter2);
        linkBlockInform.setUuid(uuid);
        LinkBlockInform linkinformExist = analyseFlowService.getLinkBlockInform(uuid);
        if (linkinformExist == null) {
            analyseFlowService.addLinkBlockInforms(linkBlockInform);
            System.out.println("12");
        } else {
            analyseFlowService.modifyLinkBlockInforms(linkBlockInform);
            System.out.println("34");
        }
        return "保存成功";
    }

    //节点信息回显
    @ResponseBody
    @PostMapping(value = "/getNodeInfo")
    public Map<String,String> getNodeInfo(@RequestParam("uuid") String uuid){
       // System.out.println("getNode");
        LinkBlockInform linkinformExist = analyseFlowService.getLinkBlockInform(uuid);
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

