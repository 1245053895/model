package com.xd.zt.service.analyse;

import com.xd.zt.domain.analyse.AnalyseCsv;
import com.xd.zt.domain.analyse.AnalyseResult;
import com.xd.zt.domain.analyse.AnalyseSource;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public interface AnalyseResultService {
    void saveAnalyseResult(AnalyseResult analyseResult);
    AnalyseResult selectInstanceid(@Param("instanceid") Integer instanceid);
    void updateAnalyseResult(AnalyseResult analyseResult);

    List<AnalyseCsv> selectAnalyseCsv(@Param("modelinstanceid")Integer modelinstanceid);
    AnalyseCsv selectCsvByid(@Param("id")Integer id);

    List<AnalyseSource> selectAnalyseSource(@Param("analysemodelid")Integer analysemodelid);
    void saveAnalyseSource(AnalyseSource analyseSource);
    AnalyseSource selectAnalyseSourceById(@Param("analysesourceid")Integer analysesourceid);
    void deleteAnalyseSource(@Param("analysesourceid")Integer analysesourceid);
}
