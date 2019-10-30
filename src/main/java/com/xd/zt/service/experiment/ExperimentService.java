package com.xd.zt.service.experiment;
import com.xd.zt.domain.analyse.AnalyseModel;
import com.xd.zt.domain.experiment.ExperimentModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import com.xd.zt.mapper.experiment.ExperimentMapper;
import java.util.ArrayList;
import java.util.List;

@Service
public interface ExperimentService {

    ArrayList modelConfiguration();

    List<AnalyseModel> selectAnalyse();
    List<ExperimentModel> selectTestname();


    void insertExperimentModel( @Param("testname") String testname,@Param("analysemodeid") Integer analysemodeid);
}
