package com.xd.zt.serviceImpl.experiment;

import com.xd.zt.domain.analyse.*;
import com.xd.zt.domain.business.BusinessModel;
import com.xd.zt.domain.business.BusinessQuestion;
import com.xd.zt.domain.experiment.ExperimentConfig;
import com.xd.zt.domain.experiment.ExperimentData;
import com.xd.zt.domain.experiment.ExperimentModel;
import com.xd.zt.domain.experiment.ExperimentTraintest;
import com.xd.zt.domain.model.Programme;
import com.xd.zt.mapper.experiment.ExperimentConfigMapper;
import com.xd.zt.mapper.experiment.ExperimentMapper;
import com.xd.zt.service.experiment.ExperimentConfigService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperimentConfigServicelmp implements ExperimentConfigService {
    @Autowired
    private ExperimentConfigMapper experimentConfigMapper;


    @Override
    public ExperimentModel showExperiment(int id) {
        return experimentConfigMapper.showExperiment(id);
    }

    @Override
    public AnalyseModelProcess showAnalyseModelProcess(int analysemodeid) {
        return experimentConfigMapper.showAnalyseModelProcess(analysemodeid);
    }

    @Override
    public AnalyseModelProcess showAnalyseModelProcessx(int modelid) {
        return experimentConfigMapper.showAnalyseModelProcessx(modelid);
    }

    @Override
    public ExperimentTraintest showExperimentTaintest(String trainname) {
        return experimentConfigMapper.showExperimentTaintest(trainname);
    }

    @Override
    public Algorithm showAlgorithm(String algorithmname) {
        return experimentConfigMapper.showAlgorithm(algorithmname);
    }

    @Override
    public List<ExperimentConfig> findAllByExperimentId(@Param("experimentid") Integer experimentid) {
        List<ExperimentConfig> experimentConfigList=  experimentConfigMapper.findAllByExperimentId(experimentid);
        return experimentConfigList;
    }

    @Override
    public  List<Algorithm> showAlgorithmtype(String algorithmtype) {
        return experimentConfigMapper.showAlgorithmtype(algorithmtype);
    }

    @Override
    public List<AnalyseModelProcess> allAnalyseModelProcess(int analysemodeid) {
        return experimentConfigMapper.allAnalyseModelProcess(analysemodeid);
    }

    @Override
    public List<AnalyseInstance> allAnalyseInstance(int modelid) {
        return experimentConfigMapper.allAnalyseInstance(modelid);
    }

    @Override
    public List<AnalyseResult> showAnalyseResuit(int instanceid) {
        return experimentConfigMapper.showAnalyseResuit(instanceid);
    }

    @Override
    public List<ExperimentData> showExperimentData(int experimentid) {
        return experimentConfigMapper.showExperimentData(experimentid);
    }

    @Override
    public boolean insertExperimentConfig(ExperimentConfig experimentConfig) {
        return experimentConfigMapper.insertExperimentConfig(experimentConfig);
    }

    @Override
    public ExperimentConfig showExperimentConfig(int id) {
        return experimentConfigMapper.showExperimentConfig(id);
    }

    @Override
    public void insertProgrammence(Programme programme) {
        experimentConfigMapper.insertProgrammence(programme);
    }

    @Override
    public void updataExperimentmodel(Integer programmeid,Integer id) {
        experimentConfigMapper.updataExperimentmodel(programmeid,id);
    }

    @Override
    public void updataAnalysemodel(Integer programmeid, Integer modelid) {
        experimentConfigMapper.updataAnalysemodel(programmeid,modelid);
    }

    @Override
    public void updataBusinessmodel(Integer programmeid, Integer businessid) {
        experimentConfigMapper.updataBusinessmodel(programmeid,businessid);
    }

    @Override
    public AnalyseModel showAnalyseModel(Integer modelid) {
        return experimentConfigMapper.showAnalyseModel(modelid);
    }

    @Override
    public BusinessQuestion showBusinessQuestion(Integer questionid) {
        return experimentConfigMapper.showBusinessQuestion(questionid);
    }

    @Override
    public BusinessModel showBusinessModel(Integer businessid) {
        return experimentConfigMapper.showBusinessModel(businessid);
    }


}
