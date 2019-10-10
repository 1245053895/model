package com.xd.zt.serviceImpl.business;


import com.xd.zt.domain.business.*;
import com.xd.zt.domain.business.flow.JsPlumbBlock;
import com.xd.zt.mapper.business.BusinessObjectMapper;
import com.xd.zt.service.business.BusinessObjectService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessObjectServiceImpl implements BusinessObjectService {
    @Autowired
    private BusinessObjectMapper businessObjectMapper;
    public List<BusinessObject> selectbusinessobject(){
        List<BusinessObject>  businessObjectList =businessObjectMapper.selectbusinessobject();
        return businessObjectList;
    }

    @Override
    public JsPlumbBlock selectBlockContent(Integer id) {
      JsPlumbBlock jsPlumbBlock=  businessObjectMapper.selectBlockContent(id);
        return jsPlumbBlock;
    }

    @Override
    public String secenIdByBlockId(Integer sceneid) {
      String blockid=  businessObjectMapper.secenIdByBlockId(sceneid);
        return blockid;
    }

    @Override
    public void insertSceneObject(Integer sceneid, Integer objectid) {
        businessObjectMapper.insertSceneObject(sceneid,objectid);

    }

    @Override
    public List<SceneObject> isExistInsertData(@Param("sceneid") Integer sceneid) {
      List<SceneObject> sceneObjectList=  businessObjectMapper.isExistInsertData(sceneid);
        return sceneObjectList;
    }

    @Override
    public List<BusinessType> allBusinessDataType() {
      List<BusinessType>  businessTypeList= businessObjectMapper.allBusinessDataType();
        return businessTypeList;
    }

    @Override
    public void insertSceneData(Integer sceneid, Integer dataid) {
        businessObjectMapper.insertSceneData(sceneid,dataid);
    }

    @Override
    public List<SceneData> isExistSceneData(Integer sceneid) {
      List<SceneData> sceneDataList=  businessObjectMapper.isExistSceneData(sceneid);
        return sceneDataList;
    }

    @Override
    public List<BusinessKnowledge> AllBusinessKnowledge() {
      List<BusinessKnowledge> businessKnowledgeList=  businessObjectMapper.AllBusinessKnowledge();
        return businessKnowledgeList;
    }

    @Override
    public void insertSceneKnowledge(@Param("sceneid")Integer sceneid, @Param("knowledgeid")Integer knowledgeid) {
        businessObjectMapper.insertSceneKnowledge(sceneid,knowledgeid);
    }

    @Override
    public List<SceneKnowledge> isExistSceneKowledge(Integer sceneid) {
      List<SceneKnowledge> sceneKnowledgeList=  businessObjectMapper.isExistSceneKowledge(sceneid);
        return sceneKnowledgeList;
    }

    @Override
    public void setScenenameBySceneId(@Param("sceneid") Integer sceneid, @Param("scenename") String scenename) {
        businessObjectMapper.setScenenameBySceneId(sceneid,scenename);

    }

    @Override
    public String getSceneName(@Param("id") Integer sceneid) {
        String scenename=businessObjectMapper.getSceneName(sceneid);
        return scenename;
    }

    @Override
    public List<SceneShow> getObjectName(@Param("id") Integer sceneid) {
        List<SceneShow> objectNameList=  businessObjectMapper.getObjectName(sceneid);
        return objectNameList;
    }

    @Override
    public List<SceneShow> getDataTypeName(@Param("id") Integer sceneid) {
        List<SceneShow> dataTypeNameList=  businessObjectMapper.getDataTypeName(sceneid);
        return dataTypeNameList;
    }

    @Override
    public List<SceneShow> getKnowledgeName(Integer sceneid) {
        List<SceneShow> knowledgeNameList=   businessObjectMapper.getKnowledgeName(sceneid);
        return knowledgeNameList;
    }

    @Override
    public void deleteBusinessModel(@Param("id") Integer businessid) {
        businessObjectMapper.deleteBusinessModel(businessid);
    }

    @Override
    public BusinessModel isExitBusinessModel(@Param("id") Integer businessid) {
      BusinessModel businessModel=  businessObjectMapper.isExitBusinessModel(businessid);
        return businessModel;
    }

/*    @Override
    public List<SceneShow> getSceneBySceneId(@Param("id") Integer sceneid) {
        List<SceneShow> sceneShowList= businessObjectMapper.getSceneBySceneId(sceneid);
        return sceneShowList;
    }*/


}
