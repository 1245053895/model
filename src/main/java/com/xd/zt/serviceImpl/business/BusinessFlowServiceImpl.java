package com.xd.zt.serviceImpl.business;



import com.xd.zt.domain.business.flow.AnalyseProcess;
import com.xd.zt.domain.business.flow.JsPlumbBlock;
import com.xd.zt.domain.business.flow.JsPlumbConnect;
import com.xd.zt.domain.business.flow.LinkBlockInform;
import com.xd.zt.mapper.business.flow.BusinessFlowMapper;
import com.xd.zt.service.business.BusinessFlowService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*import org.springframework.transaction.annotation.Transactional;*/

@Service
  public class BusinessFlowServiceImpl implements BusinessFlowService {

    @Autowired
    private BusinessFlowMapper flowMapper;

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


//    张立军
    @Override
    public List<JsPlumbConnect> selectRepeatConnection() {
        List<JsPlumbConnect> jsPlumbConnectList = flowMapper.selectRepeatConnection();
        return jsPlumbConnectList;
    }


    @Override
    public List<AnalyseProcess> getFlowList(){
        return flowMapper.flowsList();
    }

    @Override
    public List<JsPlumbBlock> getBlocks(int id){
        return flowMapper.getBlocks(id);
    }

    @Override
    public List<JsPlumbConnect> getConnects(int id){
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

    @Override
    public String selectMaxConnectId(@Param("connectionId")String connectionId) {
        String maxid = flowMapper.selectMaxConnectId(connectionId);
        return maxid;
    }

    @Override
    public String selectMinConnectId(@Param("connectionId")String connectionId) {
        String minid = flowMapper.selectMinConnectId(connectionId);
        return minid;
    }

    @Override
    public void deleteGroupConnect(String maxid, String minid) {
        flowMapper.deleteGroupConnect(maxid,minid);
    }
}

