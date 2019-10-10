package com.xd.zt.service.analyse;

import com.xd.zt.domain.analyse.*;
import com.xd.zt.domain.business.BusinessModel;
import com.xd.zt.domain.business.BusinessQuestion;
import com.xd.zt.domain.business.flow.AnalyseProcess;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface AnalyseModelService {
    /*List<BusinessQuestion> selectquestion();*/
    List<BusinessQuestion> selectquestion(Integer businessid);
    List<BusinessModel> selectBusiness();
    List<BusinessQuestion> selectquesInfo(Integer questionid);


    void insertAnalyseModel(@Param("questionid") Integer questionid, @Param("questioname") String questioname,@Param("name") String name);

   String selectQuestionName(Integer questionid);

    List<AnalyseModel>  selectqueslist();

    List<AnalyseProcess> selectAnalyseProcess(Integer modelid);

    AnalyseModel selectquestionid(Integer modelid);

//    addLinkBlockInforms

    void insertModelid(int modelid);

//    List<AnalyseUnit> selectAnalyseUnit();

    public String selectProcessName(Integer modelid);
    public void savedataPacket( @Param("databag") String databag, @Param("resultdefine") String resultdefine,@Param("flowprocessname") String flowprocessname,@Param("flowprocessid") String flowprocessid);

    List<AnalyseProcess> selectAnalyseFlow(Integer modelid);

//    AnalyseUnit selectUnitid(String unitname);

    public List<AnalyseInstance> selectAll(@Param("name") String name, @Param("modelid") String modelid);

    public List<AnalyticsTask> selectS(@Param("name") String name);

//    zlj
void deleteAnalyse(@Param("modelid")Integer modelid);
    Integer selectLastflowprocessid(@Param("modelid")Integer modelid);
    List<AnalyseFlowprocess> selectAllFlowProcess(@Param("modelid")Integer modelid);
    AnalyseFlowprocess selectProcessId(@Param("flowprocessid")Integer flowprocessid);
}
