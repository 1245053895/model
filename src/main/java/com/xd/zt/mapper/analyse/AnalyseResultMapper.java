package com.xd.zt.mapper.analyse;

import com.xd.zt.domain.analyse.AnalyseResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AnalyseResultMapper {
void saveAnalyseResult(AnalyseResult analyseResult);
AnalyseResult selectInstanceid(@Param("instanceid") Integer instanceid);
void updateAnalyseResult(AnalyseResult analyseResult);
}
