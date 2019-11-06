package com.xd.zt.service.experiment;
import com.xd.zt.domain.analyse.AnalyseModel;
import com.xd.zt.domain.data.DatamodelInfo;
import com.xd.zt.domain.experiment.ExperimentData;
import com.xd.zt.domain.experiment.ExperimentModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface ExperimentDataService {
    public ExperimentData modelDataByExperimentId(Integer experimentid);
    public List<ExperimentData> selectFileByExperimetnId(@Param("experimentid") Integer experimentid);
    public List<ExperimentData> moHuDataFile(@Param("res") String res);
    public ExperimentData selectDataFileById(@Param("id") Integer id);
    List<DatamodelInfo> selectDataBao(@Param("id") Integer id);

}
