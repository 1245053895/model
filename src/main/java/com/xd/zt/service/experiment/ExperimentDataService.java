package com.xd.zt.service.experiment;
import com.xd.zt.domain.analyse.AnalyseModel;
import com.xd.zt.domain.experiment.ExperimentData;
import com.xd.zt.domain.experiment.ExperimentModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface ExperimentDataService {
    public ExperimentData modelDataByExperimentId(Integer experimentid);

}