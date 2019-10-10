package com.xd.zt.service.business;


import com.xd.zt.domain.business.*;
import com.xd.zt.domain.business.flow.JsPlumbBlock;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BusinessObjectService {
    public List<BusinessObject> selectbusinessobject();


    public JsPlumbBlock selectBlockContent(Integer id);

    public String secenIdByBlockId(@Param("sceneid") Integer sceneid);

    public void insertSceneObject(@Param("sceneid") Integer sceneid, @Param("objectid") Integer objectid);

    public List<SceneObject> isExistInsertData(@Param("sceneid") Integer sceneid);


    public List<BusinessType> allBusinessDataType();


    public void insertSceneData(@Param("sceneid") Integer sceneid, @Param("dataid") Integer dataid);

    public List<SceneData> isExistSceneData(@Param("sceneid") Integer sceneid);
    public List<BusinessKnowledge> AllBusinessKnowledge();



    public void insertSceneKnowledge(@Param("sceneid") Integer sceneid, @Param("knowledgeid") Integer knowledgeid);

    public List<SceneKnowledge> isExistSceneKowledge(@Param("sceneid") Integer sceneid);

    public void setScenenameBySceneId(@Param("sceneid") Integer sceneid, @Param("scenename") String scenename);
   /* public List<SceneShow> getSceneBySceneId(@Param("id") Integer sceneid);*/

    /*业务场景回显*/
    public String getSceneName(@Param("id") Integer sceneid);
    public List<SceneShow> getObjectName(@Param("id") Integer sceneid);
    public List<SceneShow> getDataTypeName(@Param("id") Integer sceneid);
    public List<SceneShow> getKnowledgeName(@Param("id") Integer sceneid);

    /*业务删除*/
    public void deleteBusinessModel(Integer businessid);
    /*查询是否删除*/
    public BusinessModel isExitBusinessModel(Integer businessid);

}


