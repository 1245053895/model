package com.xd.zt.mapper;

import com.xd.zt.domain.analyse.AnalyseInstance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ApiMapper {
    List<AnalyseInstance> findInstanceByProgrammeId(@Param("programmeid")Integer programmeid);

    AnalyseInstance selectInstanceById(@Param("modelinstanceid")Integer modelinstanceid);
}
