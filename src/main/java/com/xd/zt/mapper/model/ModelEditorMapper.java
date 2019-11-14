package com.xd.zt.mapper.model;

import com.xd.zt.domain.experiment.ExperimentConfig;
import com.xd.zt.domain.model.Programme;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Author:ykr
 * Date:2019/11/13
 * Description:
 */
@Mapper
public interface ModelEditorMapper {
    public ExperimentConfig getModelFlowByProgrammeId(@Param("programmeid") Integer programmeid);
    public Integer queryExperimentIdByProgrammeId(@Param("programmeid")Integer programmeid);
    public Programme getAllByProgrammeId(@Param("programmeid") Integer programmeid);
    public void updateProgrammeByProgrammeId(@Param("programmepath") String programmepath,@Param("programmeid") String programmeid);
}
