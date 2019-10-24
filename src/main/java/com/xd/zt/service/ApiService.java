package com.xd.zt.service;

import com.xd.zt.domain.analyse.AnalyseInstance;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public interface ApiService {
    List<AnalyseInstance> findInstanceByProgrammeId(@Param("programmeid")Integer programmeid);

    AnalyseInstance selectInstanceById(@Param("modelinstanceid")Integer modelinstanceid);
}
