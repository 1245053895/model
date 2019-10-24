package com.xd.zt.serviceImpl;

import com.xd.zt.domain.analyse.AnalyseInstance;
import com.xd.zt.mapper.ApiMapper;
import com.xd.zt.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import sun.dc.pr.PRError;

import java.util.List;

@Controller
public class ApiServiceImpl implements ApiService {
    @Autowired
    private ApiMapper apiMapper;

    @Override
    public List<AnalyseInstance> findInstanceByProgrammeId(Integer programmeid) {
        List<AnalyseInstance> analyseInstanceList = apiMapper.findInstanceByProgrammeId(programmeid);
        return analyseInstanceList;
    }

    @Override
    public AnalyseInstance selectInstanceById(Integer modelinstanceid) {
       AnalyseInstance analyseInstance = apiMapper.selectInstanceById(modelinstanceid);
       return analyseInstance;
    }
}
