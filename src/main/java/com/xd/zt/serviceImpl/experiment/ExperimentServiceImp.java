package com.xd.zt.serviceImpl.experiment;

import com.xd.zt.domain.analyse.AnalyseModel;
import com.xd.zt.domain.experiment.ExperimentModel;
import com.xd.zt.mapper.experiment.ExperimentMapper;
import com.xd.zt.service.experiment.ExperimentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExperimentServiceImp implements ExperimentService {
    @Autowired
    private ExperimentMapper experimentMapper;
    @Override
    public List<AnalyseModel> selectAnalyse() {

        return experimentMapper.selectAnalyse();
    }

    @Override
    public List<ExperimentModel> selectTestname() {
        return experimentMapper.selectTestname();
    }

    @Override
    public List<ExperimentModel> selectExperimentModelList() {
        return experimentMapper.selectExperimentModelList();
    }

    @Override
    public void deleteExperiment(Integer modelid) {
        experimentMapper.deleteExperiment(modelid);
    }

    @Override
    public void insertExperimentModel( @Param("testname") String testname,@Param("analysemodeid")Integer analysemodeid) {
        experimentMapper.insertExperimentModel(testname,analysemodeid);
    }


    @Override
    public ArrayList modelConfiguration() {
        return experimentMapper.modelConfiguration();
    }
}
