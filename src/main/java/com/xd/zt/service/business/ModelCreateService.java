package com.xd.zt.service.business;


import com.xd.zt.domain.business.BusinessQuestion;
import com.xd.zt.domain.business.BusinessScene;
import com.xd.zt.domain.business.flow.JsPlumbBlock;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface ModelCreateService {
    public List<JsPlumbBlock> selectmodelcreate(Integer businessid);
    public void insertblockid(@Param("businessid") String businessid, @Param("sceneid") String sceneid);

    String selectMaxSceneId(@Param("businessid") String businessid);

    public Integer selectlastprocessid();
    public void insertsceneprocessid(@Param("blockprocessid") String blockprocessid, @Param("blockid") String blockid);
    public String selectbusinessprocess(@Param("businessid") String businessid);
    public void insertbusinessprocessid(@Param("processid") String processid, @Param("businessid") String businessid);
    public Integer reviewsceneprocess(@Param("sceneid") Integer sceneid);
    public String selectsceneblockid(@Param("sceneid") Integer sceneid);
    public String selectbusinessblock(@Param("processid") Integer processid);
    public String selectbusinessid(@Param("processid") Integer processid);
    public String selectblockidbyname(@Param("sceneprocessid") String sceneprocessid);
    public void deletescene(@Param("sceneid") String sceneid) ;

    //业务问题
    public List<BusinessQuestion> selectquestion(@Param("questionid") Integer questionid);
    public String selectnamebysceneid(@Param("questionid") Integer questionid);
    public String selectsceneblockbysceneid(@Param("questionid") Integer questionid);
    public String selectquestionblock(@Param("questionid") Integer questionid);


    /*查询节点是否已经细化*/
    public BusinessScene isXiHuaByBlockId(@Param("blockid") String blockid);
}
