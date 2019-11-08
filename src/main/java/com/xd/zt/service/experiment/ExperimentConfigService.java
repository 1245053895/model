package com.xd.zt.service.experiment;

import com.xd.zt.domain.analyse.*;
import com.xd.zt.domain.experiment.ExperimentConfig;
import com.xd.zt.domain.experiment.ExperimentData;
import com.xd.zt.domain.experiment.ExperimentModel;
import com.xd.zt.domain.experiment.ExperimentTraintest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExperimentConfigService {
    ExperimentModel showExperiment(@Param("id") int id);
    AnalyseModelProcess showAnalyseModelProcess(@Param("analysemodeid") int analysemodeid);
    AnalyseModelProcess showAnalyseModelProcessx(@Param("modelid") int modelid);
    ExperimentTraintest showExperimentTaintest(@Param("trainname") String trainname);
    Algorithm showAlgorithm(@Param("algorithmname") String algorithmname);
    public List<ExperimentConfig> findAllByExperimentId(@Param("experimentid") Integer experimentid);
    List<Algorithm> showAlgorithmtype(@Param("algorithmtype") String algorithmtype);
    List<AnalyseModelProcess> allAnalyseModelProcess(@Param("analysemodeid") int analysemodeid);
    List<AnalyseInstance> allAnalyseInstance(@Param("modelid") int modelid);
    List<AnalyseResult> showAnalyseResuit(@Param("instanceid") int instanceid);
    List<ExperimentData> showExperimentData(@Param("experimentid") int experimentid);
    boolean insertExperimentConfig(ExperimentConfig experimentConfig);
    ExperimentConfig showExperimentConfig(@Param("id") int id);
}
