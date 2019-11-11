package com.xd.zt.mapper.experiment;

import com.xd.zt.domain.experiment.ExperimentConfig;
import com.xd.zt.domain.experiment.ExperimentResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExperimentRunMapper {
    void deleteConfig(@Param("id")Integer id);
    ExperimentConfig selectConfig(@Param("id") String id);
    List<ExperimentResult> selectResult(@Param("experimentcongfigid")Integer experimentcongfigid);
    void saveResult(ExperimentResult experimentResult);
    void deleteResult(@Param("experimentcongfigid")Integer experimentcongfigid);

}
