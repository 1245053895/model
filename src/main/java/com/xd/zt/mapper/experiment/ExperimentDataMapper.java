package com.xd.zt.mapper.experiment;


import com.xd.zt.domain.experiment.ExperimentData;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExperimentDataMapper {
    public ExperimentData modelDataByExperimentId(Integer experimentid);
}
