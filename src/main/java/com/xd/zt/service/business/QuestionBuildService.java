package com.xd.zt.service.business;


import com.xd.zt.domain.business.BusinessQuestion;
import com.xd.zt.domain.business.BusinessScene;
import com.xd.zt.domain.business.flow.JsPlumbBlock;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionBuildService {
    public List<BusinessQuestion> selectbusinessquestion(@Param("businessid") String businessid);

    List<BusinessScene> selectAllSceneNode(Integer businessid);

    String selectObjectNode(int blockid);

    //String selectChildNodes();
    List<JsPlumbBlock> selectChildNodes(int sceneid);

    String selectsceneid(@Param("blockid") String blockid);

    void insertquestion(@Param("businessid") String businessid, @Param("blockid") String blockid, @Param("sceneid") String sceneid);

    void savedescribe(@Param("questioname") String questioname, @Param("questiondes") String questiondes, @Param("formulatdes") String formulatdes, @Param("blockid") String blockid);

    void deletequestion(@Param("questionid") String questionid);

    BusinessQuestion selectquestionbyblockid(@Param("blockid") String blockid);
    public void updatePicture(BusinessQuestion businessQuestion);
}
