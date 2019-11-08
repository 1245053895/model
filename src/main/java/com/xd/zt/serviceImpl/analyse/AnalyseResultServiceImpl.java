package com.xd.zt.serviceImpl.analyse;

import com.xd.zt.domain.analyse.AnalyseResult;
import com.xd.zt.mapper.analyse.AnalyseResultMapper;
import com.xd.zt.service.analyse.AnalyseResultService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AnalyseResultServiceImpl implements AnalyseResultService {
    @Autowired
    private AnalyseResultMapper analyseResultMapper;
    @Override
    public void saveAnalyseResult(AnalyseResult analyseResult) {
        analyseResultMapper.saveAnalyseResult(analyseResult);
    }

    @Override
    public AnalyseResult selectInstanceid(@Param("instanceid") Integer instanceid) {
        AnalyseResult analyseResult = analyseResultMapper.selectInstanceid(instanceid);
        return analyseResult;
    }

    @Override
    public void updateAnalyseResult(AnalyseResult analyseResult) {
        analyseResultMapper.updateAnalyseResult(analyseResult);
    }
}
