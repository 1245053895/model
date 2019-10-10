package com.xd.zt.serviceImpl.analyse;


import com.xd.zt.domain.analyse.*;
import com.xd.zt.domain.business.BusinessModel;
import com.xd.zt.domain.business.BusinessQuestion;
import com.xd.zt.domain.business.flow.AnalyseProcess;
import com.xd.zt.mapper.analyse.AnalyseModelMapper;
import com.xd.zt.service.analyse.AnalyseModelService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class AnalyseModelServiceImpl implements AnalyseModelService {
    @Autowired
    private AnalyseModelMapper analyseModelMapper;

  /*  @Override
    public List<BusinessQuestion> selectquestion() {

        return analyseModelMapper.selectquestion();
    }*/

    @Override
    public List<BusinessQuestion> selectquestion(Integer businessid) {

        return analyseModelMapper.selectquestion(businessid);
    }

    @Override
    public List<BusinessModel> selectBusiness() {

        return analyseModelMapper.selectBusiness();
    }

    @Override
    public List<AnalyseModel> selectqueslist() {
        return analyseModelMapper.selectqueslist();
    }

    @Override
    public List<AnalyseProcess> selectAnalyseProcess(Integer modelid) {
        return analyseModelMapper.selectAnalyseProcess(modelid);
    }

    @Override
    public List<BusinessQuestion> selectquesInfo(Integer questionid) {
        return analyseModelMapper.selectquesInfo(questionid);
    }

    @Override
    public void insertAnalyseModel(@Param("questionid") Integer questionid, @Param("questioname") String questioname,@Param("name") String name) {
        analyseModelMapper.insertAnalyseModel(questionid,questioname,name);

    }

    @Override
    public String selectQuestionName(Integer questionid) {
       String questioname= analyseModelMapper.selectQuestionName(questionid);
        return questioname;
    }
    @Override
    public AnalyseModel selectquestionid(Integer modelid) {
        return analyseModelMapper.selectquestionid(modelid);
    }

    @Override
    public void insertModelid(int modelid) {
        analyseModelMapper.insertModelid(modelid);
    }

//    @Override
//    public List<AnalyseUnit> selectAnalyseUnit() {
//        return analyseModelMapper.selectAnalyseUnit();
//    }


    @Override
    public String selectProcessName(Integer modelid) {
        String processname = analyseModelMapper.selectProcessName(modelid);
        return processname;
    }

    @Override
    public void savedataPacket(@Param("databag") String databag, @Param("resultdefine") String resultdefine,@Param("flowprocessname") String flowprocessname,@Param("flowprocessid") String flowprocessid) {
        analyseModelMapper.savedataPacket(databag,resultdefine,flowprocessname,flowprocessid);

    }

    @Override
    public List<AnalyseProcess> selectAnalyseFlow(Integer modelid) {
        return analyseModelMapper.selectAnalyseFlow(modelid);
    }


//    public AnalyseUnit selectUnitid(String unitname) {
//        return analyseModelMapper.selectUnitid(unitname);
//    }

//    public List<AnalyseInstance> selectAll(@Param("modelinstancename")String modelinstancename,@Param("modelid")String modelid) {
//        List<AnalyseInstance> analyseInstanceList=analyseModelMapper.selectAll(modelinstancename,modelid);
//        return analyseInstanceList;
//    }
@Override
    public List<AnalyseInstance> selectAll(String name, String modelid){
        return analyseModelMapper.selectAll(name,modelid);
    }
    @Override
    public List<AnalyticsTask> selectS(String name){
        return analyseModelMapper.selectS(name);
    }

    @Override
    public void deleteAnalyse(Integer modelid) {
        analyseModelMapper.deleteAnalyse(modelid);
    }

    @Override
    public Integer selectLastflowprocessid(Integer modelid) {
        Integer flowprocessid = analyseModelMapper.selectLastflowprocessid(modelid);
        return flowprocessid;
    }

    @Override
    public List<AnalyseFlowprocess> selectAllFlowProcess(Integer modelid) {
        List<AnalyseFlowprocess> analyseFlowprocessList = analyseModelMapper.selectAllFlowProcess(modelid);
        return analyseFlowprocessList;
    }

    @Override
    public AnalyseFlowprocess selectProcessId(Integer flowprocessid) {
        AnalyseFlowprocess analyseFlowprocess = analyseModelMapper.selectProcessId(flowprocessid);
        return analyseFlowprocess;
    }


}



