package com.xd.zt.mapper.experiment;

import com.xd.zt.domain.analyse.*;
import com.xd.zt.domain.experiment.ExperimentConfig;
import com.xd.zt.domain.experiment.ExperimentData;
import com.xd.zt.domain.experiment.ExperimentModel;
import com.xd.zt.domain.experiment.ExperimentTraintest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExperimentConfigMapper {
    ExperimentModel showExperiment(@Param("id") int id);
    AnalyseModelProcess showAnalyseModelProcess(@Param("analysemodeid") int analysemodeid);

    ExperimentConfig showExperimentConfig(@Param("id") int id);


    List<AnalyseModelProcess> allAnalyseModelProcess(@Param("analysemodeid") int analysemodeid);

    List<AnalyseInstance> allAnalyseInstance(@Param("modelid") int modelid);

    List<AnalyseResult> showAnalyseResuit(@Param("instanceid") int instanceid);

   List<ExperimentData> showExperimentData(@Param("experimentid") int experimentid);




    AnalyseModelProcess showAnalyseModelProcessx(@Param("modelid") int modelid);
    ExperimentTraintest showExperimentTaintest(@Param("trainname") String trainname);
    Algorithm showAlgorithm(@Param("algorithmname") String algorithmname);


    List<Algorithm>  showAlgorithmtype(@Param("algorithmtype") String algorithmtype);
    public List<ExperimentConfig> findAllByExperimentId(@Param("experimentid") Integer experimentid);
    boolean insertExperimentConfig(ExperimentConfig experimentConfig);
}
