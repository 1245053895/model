package com.xd.zt.serviceImpl.experiment;

import com.xd.zt.domain.analyse.Algorithm;
import com.xd.zt.domain.analyse.AnalyseFlowprocess;
import com.xd.zt.domain.analyse.AnalyseModelProcess;
import com.xd.zt.domain.experiment.ExperimentModel;
import com.xd.zt.domain.experiment.ExperimentTraintest;
import com.xd.zt.mapper.experiment.ExperimentConfigMapper;
import com.xd.zt.mapper.experiment.ExperimentMapper;
import com.xd.zt.service.experiment.ExperimentConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
