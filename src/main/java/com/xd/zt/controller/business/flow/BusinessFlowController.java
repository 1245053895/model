package com.xd.zt.controller.business.flow;


import com.alibaba.fastjson.JSONObject;
import com.xd.zt.domain.business.flow.JsPlumbBlock;
import com.xd.zt.domain.business.flow.JsPlumbConnect;
import com.xd.zt.domain.business.flow.LinkBlockInform;
import com.xd.zt.service.business.BusinessFlowService;
import com.xd.zt.service.business.ModelCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RequestMapping("/business")
@Controller
public class BusinessFlowController {
    @Autowired
    private BusinessFlowService flowService;
    @Autowired
    private ModelCreateService modelCreateService;

    //保存
    @ResponseBody
    @PostMapping(value = "/newflows/",consumes = "application/json;charset=utf-8")
    public String saveProcess(@RequestBody JSONObject jsonParam) throws Exception {
        String sceneid = jsonParam.get("sceneid").toString();

        Integer id = modelCreateService.reviewsceneprocess(Integer.parseInt(sceneid));
        //删除当前流程
        if (id != null){
        flowService.deleteBlocksAndConnects(id);
        flowService.deleteProcessName(id);}

        List<JsPlumbConnect> connects = JSONObject.parseArray(jsonParam.getJSONArray("connects").toJSONString(),JsPlumbConnect.class);
        List<JsPlumbBlock> blocks = JSONObject.parseArray(jsonParam.getJSONArray("blocks").toJSONString(), JsPlumbBlock.class);
        String processName = jsonParam.get("name").toString();
        String val = flowService.delSaveBlock(connects);
        //取流程名插入场景表business_scene

        String sceneprocessid = jsonParam.get("sceneprocessid").toString();
        if (val ==null && sceneprocessid == null || sceneprocessid == "" ){
            String msg = flowService.saveProcess(processName, blocks, connects);

            String blockprocessid = modelCreateService.selectlastprocessid().toString();
            modelCreateService.insertsceneprocessid(blockprocessid,sceneid);
             return msg ;
        }else {
            String msg = flowService.saveProcess(processName, blocks, connects);
//            String sceneblockid = modelCreateService.selectblockidbyname(sceneprocessid);
            String blockprocessid = modelCreateService.selectlastprocessid().toString();
            modelCreateService.insertsceneprocessid(blockprocessid,sceneid);
            return msg+";" ;
        }
    }

    @ResponseBody
    @PostMapping(value = "/newbusiness/",consumes = "application/json;charset=utf-8")
    public String saveBlockProcess(@RequestBody JSONObject jsonParam) throws Exception {
        List<JsPlumbConnect> connects = JSONObject.parseArray(jsonParam.getJSONArray("connects").toJSONString(),JsPlumbConnect.class);
        System.out.println(connects);

        List<JsPlumbBlock> blocks = JSONObject.parseArray(jsonParam.getJSONArray("blocks").toJSONString(), JsPlumbBlock.class);
        System.out.println(blocks);

        String processName = jsonParam.get("name").toString();
        String msg = flowService.saveProcess(processName, blocks, connects);

        //取流程名插入业务表
        String processid = modelCreateService.selectlastprocessid().toString();

        String businessid = jsonParam.get("businessid").toString();
        modelCreateService.insertbusinessprocessid(processid,businessid);

        return msg;
    }

    @ResponseBody
    @PostMapping(value = "/updateprocess",consumes = "application/json;charset=utf-8")
    public String updata(@RequestBody JSONObject jsonParam) throws Exception {
        //删除原先process
        String businessid = jsonParam.get("businessid").toString();
        String id = modelCreateService.selectbusinessprocess(businessid);
        flowService.deleteBlocksAndConnects(Integer.parseInt(id));
        flowService.deleteProcessName(Integer.parseInt(id));

        List<JsPlumbConnect> connects = JSONObject.parseArray(jsonParam.getJSONArray("connects").toJSONString(),JsPlumbConnect.class);
        List<JsPlumbBlock> blocks = JSONObject.parseArray(jsonParam.getJSONArray("blocks").toJSONString(), JsPlumbBlock.class);
        String processName = jsonParam.get("name").toString();
        String msg = flowService.saveProcess(processName, blocks, connects);

        //删除重复线条
        List<JsPlumbConnect> jsPlumbConnectList = flowService.selectRepeatConnection();
        for (int i= 0; i<jsPlumbConnectList.size();i++){
            String connectionId = jsPlumbConnectList.get(i).getConnectionId();
            String maxid =  flowService.selectMaxConnectId(connectionId);
            String minid = flowService.selectMinConnectId(connectionId);
            flowService.deleteGroupConnect(maxid,minid);
        }

//   //取流程名插入业务表
        String processid = modelCreateService.selectlastprocessid().toString();

        modelCreateService.insertbusinessprocessid(processid,businessid);
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
//            System.out.println("12");
        } else {
            flowService.modifyLinkBlockInforms(linkBlockInform);
//            System.out.println("34");
        }
        return "保存成功";
    }

    //节点信息回显
    @ResponseBody
    @PostMapping(value = "/getNodeInfo")
    public Map<String,String> getNodeInfo(@RequestParam("uuid") String uuid){
        System.out.println("getNode");
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

