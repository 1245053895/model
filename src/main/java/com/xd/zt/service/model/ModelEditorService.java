package com.xd.zt.service.model;

import com.xd.zt.domain.experiment.ExperimentConfig;
import com.xd.zt.domain.model.Programme;
import com.xd.zt.domain.model.ProgrammeResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author:ykr
 * Date:2019/11/13
 * Description:
 */
@Service
public interface ModelEditorService {
    public ExperimentConfig getModelFlowByProgrammeId(@Param("programmeid") Integer programmeid);
    public Integer queryExperimentIdByProgrammeId(@Param("programmeid")Integer programmeid);
    public Programme getAllByProgrammeId(@Param("programmeid") Integer programmeid);
    public void updateProgrammeByProgrammeId(@Param("programmepath") String programmepath,@Param("programmeid") String programmeid);

    List<ProgrammeResult> selectProgrammeResult(@Param("programmeid")Integer programmeid);
    void saveProgrammeResult(ProgrammeResult programmeResult);
    void deleteProgrammeResult(@Param("programmeid")Integer programmeid);
}
