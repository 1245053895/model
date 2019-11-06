package com.xd.zt.mapper.experiment;


import com.xd.zt.domain.data.DatamodelInfo;
import com.xd.zt.domain.experiment.ExperimentData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.security.PublicKey;
import java.util.List;

@Mapper
public interface ExperimentDataMapper {
    public ExperimentData modelDataByExperimentId(Integer experimentid);

    public List<ExperimentData> selectFileByExperimetnId(@Param("experimentid") Integer experimentid);

    public List<ExperimentData> moHuDataFile(@Param("res") String res);
    public ExperimentData selectDataFileById(@Param("id") Integer id);

    List<DatamodelInfo> selectDataBao(@Param("id") Integer id);
}
