package com.xd.zt.mapper.experiment;

import com.xd.zt.domain.analyse.Algorithm;
import com.xd.zt.domain.analyse.AnalyseFlowprocess;
import com.xd.zt.domain.analyse.AnalyseModelProcess;
import com.xd.zt.domain.experiment.ExperimentConfig;
import com.xd.zt.domain.experiment.ExperimentModel;
import com.xd.zt.domain.experiment.ExperimentTraintest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExperimentConfigMapper {
    ExperimentModel showExperiment(@Param("id") int id);
    AnalyseModelProcess showAnalyseModelProcess(@Param("analysemodeid") int analysemodeid);
    AnalyseModelProcess showAnalyseModelProcessx(@Param("modelid") int modelid);
    ExperimentTraintest showExperimentTaintest(@Param("trainname") String trainname);
    Algorithm showAlgorithm(@Param("algorithmname") String algorithmname);

    public List<ExperimentConfig> findAllByExperimentId(@Param("experimentid") Integer experimentid);

}
