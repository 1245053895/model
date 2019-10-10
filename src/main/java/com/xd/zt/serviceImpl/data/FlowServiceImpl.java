package com.xd.zt.serviceImpl.data;



import com.xd.zt.domain.business.flow.AnalyseProcess;
import com.xd.zt.domain.business.flow.JsPlumbBlock;
import com.xd.zt.domain.business.flow.JsPlumbConnect;
import com.xd.zt.domain.business.flow.LinkBlockInform;
import com.xd.zt.domain.data.*;
import com.xd.zt.mapper.data.flow.FlowMapper;
import com.xd.zt.service.data.FlowService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*import org.springframework.transaction.annotation.Transactional;*/

@Service
  public class FlowServiceImpl implements FlowService {

    @Autowired
    private FlowMapper flowMapper;


    @Override
    @Transactional
    public String saveProcess(String processName, List<JsPlumbBlock> blocks, List<JsPlumbConnect> connects)throws Exception{

        boolean flag=false;
        String msg="";
        AnalyseProcess analyseProcess=new AnalyseProcess();
//        JSONObject object = new JSONObject();
        try {
            analyseProcess.setName(processName);
            String editTime =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString();
            analyseProcess.setEdittime(editTime);
            flowMapper.saveProcess(analyseProcess);
            int processId = analyseProcess.getId();

            boolean msg2 = flowMapper.saveBlocks(blocks);
            boolean msg3 = flowMapper.saveConnects(connects);
            List<Integer> bLocksids = new ArrayList<>();
            for(int i=0; i<blocks.size(); i++){
                bLocksids .add(blocks.get(i).getId());
            }
            System.out.println(editTime);
            boolean msg1=flowMapper.saveProBlocks(processId,bLocksids);
            if(msg1&&msg2&&msg3){
                msg="创建成功";
            }else {
                msg="保存失败";
            }
        }catch (Exception e){
            System.out.println(e);
            msg="创建失败";
        }finally{
            return msg;
        }
    }




    @org.springframework.transaction.annotation.Transactional
    @Override
    public String dlsaveProcess(String processName, List<DlJsPlumbBlock> blocks, List<DlJsplumbConnect> connects) throws Exception {
        String msg ="";
        DlAnalyseProcess dlAnalyseProcess = new DlAnalyseProcess();
        dlAnalyseProcess.setName(processName);
        String editTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString();
        dlAnalyseProcess.setEdittime(editTime);
        flowMapper.dlsaveProcess(dlAnalyseProcess);
        int dlprocessId = dlAnalyseProcess.getId();

        boolean msg2 = flowMapper.dlsaveBlocks(blocks);
        boolean msg3 = flowMapper.dlsaveConnects(connects);

        List<Integer> bLocksids = new ArrayList<>();
        for (int i=0;i<blocks.size();i++){
            bLocksids.add(blocks.get(i).getId());
        }

        List<String> blockConntents = new ArrayList<>();
        for (int i=0;i<blocks.size();i++){
            blockConntents.add(blocks.get(i).getBlockContent());
        }
        boolean msg1= flowMapper.dlsaveProBlocks(dlprocessId,bLocksids);

        if(msg1&&msg2&&msg3){
            msg="创建成功";
        }else{
            msg="保存失败";
        }
        return msg;
    }


    @org.springframework.transaction.annotation.Transactional
    @Override
    public String darsaveProcess(String processName, List<DarJsPlumbBlock> blocks, List<DarJsplumbConnect> connects) throws Exception {
        String msg ="";
        DarAnalyseProcess darAnalyseProcess = new DarAnalyseProcess();
        darAnalyseProcess.setName(processName);
        String editTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString();
        darAnalyseProcess.setEdittime(editTime);
        flowMapper.darsaveProcess(darAnalyseProcess);
        int processId = darAnalyseProcess.getId();
        boolean msg2 = flowMapper.darsaveBlocks(blocks);
        boolean msg3 = flowMapper.darsaveConnects(connects);

        List<Integer> bLocksids = new ArrayList<>();
        for (int i=0;i<blocks.size();i++){
            bLocksids.add(blocks.get(i).getId());
        }

        List<String> blockConntents = new ArrayList<>();
        for (int i=0;i<blocks.size();i++){
            blockConntents.add(blocks.get(i).getBlockContent());
        }
        boolean msg1= flowMapper.barsaveProBlocks(processId,bLocksids);
        if(msg1&&msg2&&msg3){
            msg="创建成功";
        }else{
            msg="保存失败";
        }
        return msg;
    }


    @Override
    public void addLinkBlockInforms(LinkBlockInform linkBlockInform){
        flowMapper.insertLinkBlockInforms(linkBlockInform);
    }


    @Override
    public void modifyLinkBlockInforms(LinkBlockInform linkBlockInform){
        flowMapper.updateLinkBlockInforms(linkBlockInform);
    }


    @Override
    public LinkBlockInform getLinkBlockInform(String uuid) {
        return flowMapper.selectLinkBlockInform(uuid);
    }

    @Override
    public List<JsPlumbBlock> sceneidTOBlock(int sceneid) {
        return flowMapper.sceneidTOBlock(sceneid);
    }

    @Override
    public List<JsPlumbConnect> sceneidTOConnects(int sceneid) {
        return flowMapper.sceneidTOConnects(sceneid);
    }

    @Override
    public void deletesceneidTOBlock(int sceneid) {
        flowMapper.deletesceneidTOBlock(sceneid);
    }

    @Override
    public void deletesceneidProcessName(int sceneid) {
        flowMapper.deletesceneidProcessName(sceneid);
    }

    @Override
    public void insertdlblock(int sceneid) {
        flowMapper.insertdlblock(sceneid);
    }

    @Override
    public void insertdlconnect(int sceneid) {
        flowMapper.insertdlconnect(sceneid);
    }

    @Override
    public void insertdlannlyse(int sceneid) {
        flowMapper.insertdlannlyse(sceneid);
    }

    @Override
    public void insertdlpro(int sceneid) {
        flowMapper.insertdlpro(sceneid);
    }

    @Override
    public void deletdlanalyse(int sceneid) {
        flowMapper.deletdlanalyse(sceneid);
    }

    @Override
    public AnalyseProcess sceneidToId(int sceneid) {
        AnalyseProcess analyseProcess = flowMapper.sceneidToId(sceneid);
        return analyseProcess;
    }

    @Override
    public void dLdeleteProcessName(int processid) {
        flowMapper.dLdeleteProcessName(processid);
    }

    @Override
    public void dardeleteProcessName(int processid) {
        flowMapper.dardeleteProcessName(processid);
    }

    @Override
    public void dLdeleteBlocks(int processid) {
        flowMapper.dLdeleteBlocks(processid);
    }

    @Override
    public void dardeleteBlocks(int processid) {
        flowMapper.dardeleteBlocks(processid);
    }

    @Override
    public List<DlJsplumbConnect> selectRepeatConnection() {
        List<DlJsplumbConnect> jsPlumbConnectList = flowMapper.selectRepeatConnection();
        return jsPlumbConnectList;
    }

    @Override
    public String selectMaxConnectId(String pageSourceId, String pageTargetId) {
        String maxid = flowMapper.selectMaxConnectId(pageSourceId,pageTargetId);
        return maxid;
    }

    @Override
    public String selectMinConnectId(String pageSourceId, String pageTargetId) {
        String minid = flowMapper.selectMinConnectId(pageSourceId,pageTargetId);
        return minid;
    }


    @Override
    public void deleteGroupConnect(@Param("maxid")String maxid,@Param("minid")String minid,@Param("pageSourceId") String pageSourceId, @Param("pageTargetId") String pageTargetId) {
        flowMapper.deleteGroupConnect(maxid,minid,pageSourceId,pageTargetId);
    }

    @Override
    public List<DarJsplumbConnect> dargetConnects(int processid) {
        return flowMapper.dargetConnects(processid);
    }


    @Override
    public List<DlJsPlumbBlock> dlgetBlocks(int processid) {
        return flowMapper.dlgetBlocks(processid);
    }

    @Override
    public List<DarJsPlumbBlock> dargetBlocks(int processid) {
        return flowMapper.dargetBlocks(processid);
    }

    @Override
    public List<DlJsplumbConnect> dlgetConnects(int processid) {
        return flowMapper.dlgetConnects(processid);
    }

    @Override
    public List<DarJsplumbConnect> processid(int processid) {
        return flowMapper.dargetConnects(processid);
    }


    @Override
    public DatamodelLink processidTomodeid(int processid) {
        return flowMapper.processidTomodeid(processid);
    }

    @Override
    public void deletdatalinkToProcessid(int processid) {
        flowMapper.deletdatalinkToProcessid(processid);
    }

    @Override
    public List<DarJsPlumbBlock> darGetBlocks(int id) {
        return flowMapper.darGetBlocks(id);
    }

    @Override
    public List<DarJsplumbConnect> darGetConnects(int id) {
        return flowMapper.darGetConnects(id);
    }

    @Override
    public List<DarJsplumbConnect> selectRepeatConnectionx() {
        return flowMapper.selectRepeatConnectionx();
    }

    @Override
    public String selectMaxConnectIdx(String pageSourceId, String pageTargetId) {
        return flowMapper.selectMaxConnectIdx(pageSourceId,pageTargetId);
    }

    @Override
    public String selectMinConnectIdx(String pageSourceId, String pageTargetId) {
        return flowMapper.selectMinConnectIdx(pageSourceId,pageTargetId);
    }

    @Override
    public void deleteGroupConnectx(@Param("maxid")String maxid,@Param("minid")String minid, String pageSourceId, String pageTargetId) {
        flowMapper.deleteGroupConnectx(maxid,minid,pageSourceId,pageTargetId);
    }


    @Override
    public List<AnalyseProcess> getFlowList(){
        return flowMapper.flowsList();
    }

    @Override
    public List<DlJsPlumbBlock> getBlocks(int id){
        return flowMapper.getBlocks(id);
    }

    @Override
    public List<DlJsplumbConnect> getConnects(int id){
        return flowMapper.getConnects(id);
    }

    @Override
    public void deleteProcessName(int Id){
        flowMapper.deleteProcessName(Id);
    }

    @Override
    public void deleteBlocksAndConnects(int Id){flowMapper.deleteBlocksAndConnects(Id);}

    @Override
    public List<AnalyseProcess> getAllList(String name){return flowMapper.getAllList(name);}

    @Override
    public String delSaveBlock(List<JsPlumbConnect> connects){
        boolean delmsg = flowMapper.delSaveBlock(connects);
        return "xxx";
    }
}

