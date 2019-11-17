package com.xd.zt.service.experiment;


import com.xd.zt.domain.experiment.ExperimentConfig;
import com.xd.zt.domain.experiment.ExperimentResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExperimentRunService {
    void deleteConfig(@Param("id")Integer id);
    ExperimentConfig selectConfig(@Param("id") String id);
    List<ExperimentResult> selectResult(@Param("experimentcongfigid")Integer experimentcongfigid);
    void saveResult(ExperimentResult experimentResult);
    void deleteResult(@Param("experimentcongfigid")Integer experimentcongfigid);
    void updateProgrammeId(@Param("programmeid")Integer programmeid,@Param("id")Integer id);
    List<ExperimentResult> selectResultById(@Param("experimentid")Integer experimentid);
}
