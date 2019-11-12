package com.xd.zt.serviceImpl.analyse;

import com.xd.zt.domain.analyse.AnalyseCsv;
import com.xd.zt.domain.analyse.AnalyseResult;
import com.xd.zt.domain.analyse.AnalyseSource;
import com.xd.zt.mapper.analyse.AnalyseResultMapper;
import com.xd.zt.service.analyse.AnalyseResultService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

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

    @Override
    public List<AnalyseCsv> selectAnalyseCsv(Integer modelinstanceid) {
        List<AnalyseCsv> analyseCsvList = analyseResultMapper.selectAnalyseCsv(modelinstanceid);
        return analyseCsvList;
    }

    @Override
    public AnalyseCsv selectCsvByid(Integer id) {
        AnalyseCsv analyseCsv = analyseResultMapper.selectCsvByid(id);
        return analyseCsv;
    }

    @Override
    public List<AnalyseSource> selectAnalyseSource(Integer analysemodelid) {
        List<AnalyseSource> analyseSourceList = analyseResultMapper.selectAnalyseSource(analysemodelid);
        return analyseSourceList;
    }

    @Override
    public void saveAnalyseSource(AnalyseSource analyseSource) {
        analyseResultMapper.saveAnalyseSource(analyseSource);
    }

    @Override
    public AnalyseSource selectAnalyseSourceById(Integer analysesourceid) {
        AnalyseSource analyseSource = analyseResultMapper.selectAnalyseSourceById(analysesourceid);
        return analyseSource;
    }

    @Override
    public void deleteAnalyseSource(Integer analysesourceid) {
        analyseResultMapper.deleteAnalyseSource(analysesourceid);
    }
}
