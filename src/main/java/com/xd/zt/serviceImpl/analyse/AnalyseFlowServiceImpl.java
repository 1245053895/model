package com.xd.zt.serviceImpl.analyse;


import com.xd.zt.domain.analyse.*;
import com.xd.zt.domain.business.flow.AnalyseProcess;
import com.xd.zt.domain.business.flow.JsPlumbBlock;
import com.xd.zt.domain.business.flow.JsPlumbConnect;
import com.xd.zt.domain.business.flow.LinkBlockInform;
import com.xd.zt.mapper.analyse.AnalyseFlowMapper;
import com.xd.zt.service.analyse.AnalyseFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
  public class AnalyseFlowServiceImpl implements AnalyseFlowService {

    @Autowired
    private AnalyseFlowMapper analyseFlowMapper;

    @Override
    @Transactional
    public String saveProcess(String processName, List<JsPlumbBlock> blocks, List<JsPlumbConnect> connects, int modelid)throws Exception{

        boolean flag=false;
        String msg="";
        AnalyseProcess analyseProcess = new AnalyseProcess();
       AnalyseFlowprocess analyseFlowprocess = new AnalyseFlowprocess();
//        JSONObject object = new JSONObject();
        try {
            analyseProcess.setName(processName);
            String editTime =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString();
            analyseProcess.setEdittime(editTime);
            analyseFlowMapper.saveProcess(analyseProcess);
            int processid = analyseProcess.getId();

            analyseFlowprocess.setProcessid(processid);

            analyseFlowprocess.setModelid(modelid);

            boolean msg4 = analyseFlowMapper.saveAnalyseModelController(analyseFlowprocess);
            boolean msg2 = analyseFlowMapper.saveBlocks(blocks);
            boolean msg3 = analyseFlowMapper.saveConnects(connects);
            List<Integer> bLocksids = new ArrayList<>();
            for(int i=0; i<blocks.size(); i++){
                bLocksids .add(blocks.get(i).getId());
            }
            System.out.println(editTime);
            boolean msg1= analyseFlowMapper.saveProBlocks(processid,bLocksids);

            if(msg1&&msg2&&msg3&&msg4){
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



    @Override
    @Transactional
    public String saveunitflowPage(String processName, List<JsPlumbBlock> blocks, List<JsPlumbConnect> connects, int unitid) throws Exception {
        boolean flag=false;
        String msg="";
        AnalyseProcess analyseProcess = new AnalyseProcess();
        try {

            analyseProcess.setName(processName);
            String editTime =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString();
            analyseProcess.setEdittime(editTime);
            analyseFlowMapper.saveProcess(analyseProcess);
            int processid = analyseProcess.getId();
            boolean msg2 = analyseFlowMapper.saveBlocks(blocks);
            boolean msg3 = analyseFlowMapper.saveConnects(connects);
            analyseFlowMapper.insertProcessid(processid,unitid);
            List<Integer> bLocksids = new ArrayList<>();
            for(int i=0; i<blocks.size(); i++){
                bLocksids .add(blocks.get(i).getId());
            }
            System.out.println(editTime);
            boolean msg1= analyseFlowMapper.saveProBlocks(processid,bLocksids);

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


    @Override
    public void addLinkBlockInforms(LinkBlockInform linkBlockInform){
        analyseFlowMapper.insertLinkBlockInforms(linkBlockInform);
    }


    @Override
    public void modifyLinkBlockInforms(LinkBlockInform linkBlockInform){
        analyseFlowMapper.updateLinkBlockInforms(linkBlockInform);
    }


    @Override
    public LinkBlockInform getLinkBlockInform(String uuid) {
        return analyseFlowMapper.selectLinkBlockInform(uuid);
    }

    @Override
    public AnalyseFlowprocess slectProcessidToModelid(int Id) {
        return analyseFlowMapper.slectProcessidToModelid(Id);
    }

    @Override
    public void insertProcessid(Integer processid, Integer unitid) {
        analyseFlowMapper.insertProcessid(processid,unitid);
    }

//    @Override
//    public AnalyseUnit selectAnalyseUnit(Integer unitid) {
//        return analyseFlowMapper.selectAnalyseUnit(unitid);
//    }
//
//    @Override
//    public AnalyseUnit selectUnitid(int Id) {
//        return analyseFlowMapper.selectUnitid(Id);
//    }


    @Override
    public List<AnalyseProcess> getFlowList(){
        return analyseFlowMapper.flowsList();
    }

    @Override
    public List<JsPlumbBlock> getBlocks(int Id){
        return analyseFlowMapper.getBlocks(Id);
    }

    @Override
    public List<JsPlumbConnect> getConnects(int Id){
         return analyseFlowMapper.getConnects(Id);
    }

    @Override
    public void deleteProcessName(int Id){
        analyseFlowMapper.deleteProcessName(Id);
    }

    @Override
    public void deleteBlocksAndConnects(int Id){
        analyseFlowMapper.deleteBlocksAndConnects(Id);}

    @Override
    public List<AnalyseProcess> getAllList(String name){return analyseFlowMapper.getAllList(name);}

    @Override
    public String delSaveBlock(List<JsPlumbConnect> connects){
        boolean delmsg = analyseFlowMapper.delSaveBlock(connects);
         return "xxx";
    }
}

