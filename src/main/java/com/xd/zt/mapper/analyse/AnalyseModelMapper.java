package com.xd.zt.mapper.analyse;


import com.xd.zt.domain.analyse.*;
import com.xd.zt.domain.business.BusinessModel;
import com.xd.zt.domain.business.BusinessQuestion;
import com.xd.zt.domain.business.flow.AnalyseProcess;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AnalyseModelMapper {
    /* List<BusinessQuestion> selectquestion();*/
     List<BusinessQuestion> selectquestion(Integer businessid);
     List<BusinessQuestion> selectquesInfo(Integer questionid);
     List<BusinessModel> selectBusiness();

    void insertAnalyseModel(@Param("questionid") Integer questionid, @Param("questioname") String questioname,@Param("name") String name);

     public String selectQuestionName(Integer questionid);
     List<AnalyseModel>  selectqueslist();
     List<AnalyseProcess> selectAnalyseProcess(Integer modelid);



    AnalyseModel selectquestionid(@Param("modelid") Integer modelid);

    void insertModelid(Integer modelid);

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



