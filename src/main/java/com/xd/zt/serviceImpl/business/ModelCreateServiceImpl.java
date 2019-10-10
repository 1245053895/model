package com.xd.zt.serviceImpl.business;


import com.xd.zt.domain.business.BusinessQuestion;
import com.xd.zt.domain.business.BusinessScene;
import com.xd.zt.domain.business.flow.JsPlumbBlock;
import com.xd.zt.mapper.business.ModelCreateMapper;
import com.xd.zt.service.business.ModelCreateService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelCreateServiceImpl implements ModelCreateService {
@Autowired
  private ModelCreateMapper modelCreateMapper;

    @Override
    public List<JsPlumbBlock> selectmodelcreate(Integer businessid) {
        List<JsPlumbBlock> jsPlumbBlockList=modelCreateMapper.selectmodelcreate(businessid);
        return jsPlumbBlockList;
    }

    @Override
    public void insertblockid(@Param("businessid") String businessid,@Param("blockid") String blockid) {
    modelCreateMapper.insertblockid(businessid,blockid);
    }

    @Override
    public String selectMaxSceneId(String businessid) {
        String sceneid = modelCreateMapper.selectMaxSceneId(businessid);
        return sceneid;
    }

    @Override
    public Integer selectlastprocessid() {
     Integer sceneprocessid =  modelCreateMapper.selectlastprocessid();
        return sceneprocessid;
    }


    @Override
    public void insertsceneprocessid(@Param("blockprocessid") String blockprocessid,@Param("sceneid") String sceneid) {
        modelCreateMapper.insertsceneprocessid(blockprocessid,sceneid);
    }

    @Override
    public String selectbusinessprocess(String businessid) {
        String processid = modelCreateMapper.selectbusinessprocess(businessid);
    return processid;
    }

    @Override
    public void insertbusinessprocessid(String processid, String businessid) {
        modelCreateMapper.insertbusinessprocessid(processid,businessid);
    }

    @Override
    public Integer reviewsceneprocess(@Param("sceneid") Integer sceneid) {
        Integer sceneprocessid = modelCreateMapper.reviewsceneprocess(sceneid);
        return sceneprocessid;
    }

    @Override
    public String selectsceneblockid(@Param("sceneid")Integer sceneid) {
        String sceneblock = modelCreateMapper.selectsceneblockid(sceneid);
        return sceneblock;
    }

    @Override
    public String selectbusinessblock(@Param("processid")Integer processid) {
        String businessprocessname = modelCreateMapper.selectbusinessblock(processid);
        return businessprocessname;
    }

    @Override
    public String selectbusinessid(@Param("processid")Integer processid) {
        String businessid = modelCreateMapper.selectbusinessid(processid);
        return businessid;
    }

    @Override
    public String selectblockidbyname(@Param("sceneprocessid") String sceneprocessid) {
        String blockid = modelCreateMapper.selectblockidbyname(sceneprocessid);
        return blockid;
    }

    @Override
    public void deletescene(@Param("sceneid") String sceneid) {
        modelCreateMapper.deletescene(sceneid);
    }

    //业务问题
    @Override
    public List<BusinessQuestion> selectquestion(Integer questionid) {
        List<BusinessQuestion> businessQuestionList = modelCreateMapper.selectquestion(questionid);
        return businessQuestionList;
    }

    @Override
    public String selectnamebysceneid(Integer questionid) {
        String scenename = modelCreateMapper.selectnamebysceneid(questionid);
        return scenename;
    }

    @Override
    public String selectsceneblockbysceneid(Integer questionid) {
        String sceneblock = modelCreateMapper.selectsceneblockbysceneid(questionid);
        return sceneblock;
    }

    @Override
    public String selectquestionblock(Integer questionid) {
        String questionblock = modelCreateMapper.selectquestionblock(questionid);
        return questionblock;
    }

    @Override
    public BusinessScene isXiHuaByBlockId(@Param("blockid") String blockid) {
       BusinessScene businessScene= modelCreateMapper.isXiHuaByBlockId(blockid);
        return businessScene;
    }


}
