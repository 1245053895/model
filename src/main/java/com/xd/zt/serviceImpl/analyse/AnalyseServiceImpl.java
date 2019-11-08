package com.xd.zt.serviceImpl.analyse;


import com.xd.zt.domain.analyse.*;
import com.xd.zt.domain.data.DatamodelInfo;
import com.xd.zt.mapper.analyse.AnalyseMapper;
import com.xd.zt.service.analyse.AnalyseService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyseServiceImpl implements AnalyseService {
@Autowired
    private AnalyseMapper analyseMapper;

    @Override
    public List<AnalyseModelProcess> selectAnalyseModel(@Param("analysemodelid") Integer analysemodelid) {
        List<AnalyseModelProcess> analyseModelList = analyseMapper.selectAnalyseModel(analysemodelid);
        return analyseModelList;
    }

    @Override
    public void insertAnalyseModel(@Param("modelname") String modelname, @Param("modelprocess") String modelprocess,@Param("analysemodelid")String analysemodelid) {
        analyseMapper.insertAnalyseModel(modelname,modelprocess,analysemodelid);
    }

    @Override
    public void deleteModel(String modelid) {
        analyseMapper.deleteModel(modelid);
    }

    @Override
    public void deleteanalyseprocess(@Param("processid")Integer processid) {
        analyseMapper.deleteanalyseprocess(processid);
    }

    @Override
    public Integer selectProcessid(String flowprocessid) {
        Integer processid = analyseMapper.selectProcessid(flowprocessid);
        return processid;
    }

    @Override
    public List<DatamodelInfo> selectDataResult(Integer modelid) {
        List<DatamodelInfo> datamodelInfoList = analyseMapper.selectDataResult(modelid);
        return datamodelInfoList;
    }

    @Override
    public void saveAnalyseCsv(AnalyseCsv analyseCsv) {
        analyseMapper.saveAnalyseCsv(analyseCsv);
    }

    @Override
    public List<AnalyseCsv> selectCsvExit(@Param("modelinstanceid") Integer modelinstanceid) {
        List<AnalyseCsv> analyseCsvList = analyseMapper.selectCsvExit(modelinstanceid);
        return analyseCsvList;
    }

    @Override
    public void deleteAnalyseCsv(@Param("modelinstanceid") Integer modelinstanceid) {
        analyseMapper.deleteAnalyseCsv(modelinstanceid);
    }


    @Override
    public String selectAnalyseProcess(@Param("modelid") Integer modelid) {
        String modelprocess = analyseMapper.selectAnalyseProcess(modelid);
        return modelprocess;
    }

    @Override
    public void updateAnalyse(@Param("modelid")String modelid, @Param("modelname") String modelname, @Param("modelprocess") String modelprocess) {
        analyseMapper.updateAnalyse(modelid,modelname,modelprocess);
    }

    @Override
    public Integer selectModelid(Integer modelinstanceid) {
        Integer modelid = analyseMapper.selectModelid(modelinstanceid);
        return modelid;
    }

    @Override
    public String selectInstanceName(String modelinstancename) {
        String modelinstanceid = analyseMapper.selectInstanceName(modelinstancename);
        return modelinstanceid;
    }

    @Override
    public List<AnalyseInstance> selectAnalyseExample(@Param("modelid") Integer modelid) {
        List<AnalyseInstance> analyseInstanceList = analyseMapper.selectAnalyseExample(modelid);
        return analyseInstanceList;
    }

    @Override
    public void insertExample(String modelid, String modelinstancename, String parameters) {
        analyseMapper.insertExample(modelid,modelinstancename,parameters);
    }

    @Override
    public void deleteExample(String modelinstanceid) {
        analyseMapper.deleteExample(modelinstanceid);
    }

    @Override
    public String selectParams(String modelinstanceid) {
        String params = analyseMapper.selectParams(modelinstanceid);
        return params;
    }

    @Override
    public List<AnalyticsTask> selectTask() {
        List<AnalyticsTask> analyticsTaskList = analyseMapper.selectTask();
        return analyticsTaskList;
    }

    @Override
    public List<Algorithm> selectAlgorithm() {
        List<Algorithm> algorithmList = analyseMapper.selectAlgorithm();
        return algorithmList;
    }

    @Override
    public Algorithm selectAlgorithmParams(String algorithmname) {
        Algorithm algorithm = analyseMapper.selectAlgorithmParams(algorithmname);
        return algorithm;
    }

}
