package com.xd.zt.service.analyse;

import com.xd.zt.domain.analyse.AnalyseResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;

@Controller
public interface AnalyseResultService {
    void saveAnalyseResult(AnalyseResult analyseResult);
    AnalyseResult selectInstanceid(@Param("instanceid") Integer instanceid);
    void updateAnalyseResult(AnalyseResult analyseResult);
}
