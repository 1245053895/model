package com.xd.zt.mapper.experiment;

import com.xd.zt.domain.analyse.AnalyseModel;
import com.xd.zt.domain.experiment.ExperimentModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ExperimentMapper {


    ArrayList modelConfiguration();;

    List<AnalyseModel> selectAnalyse();
    List<ExperimentModel> selectTestname();

    List<ExperimentModel>  selectExperimentModelList();

    void deleteExperiment(@Param("modelid")Integer modelid);

    void insertExperimentModel( @Param("testname") String testname,@Param("analysemodeid") Integer analysemodeid);
}
