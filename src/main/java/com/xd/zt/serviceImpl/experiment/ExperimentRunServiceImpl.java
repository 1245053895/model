package com.xd.zt.serviceImpl.experiment;

import com.xd.zt.domain.experiment.ExperimentConfig;
import com.xd.zt.domain.experiment.ExperimentResult;
import com.xd.zt.mapper.experiment.ExperimentRunMapper;
import com.xd.zt.service.experiment.ExperimentRunService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ExperimentRunServiceImpl implements ExperimentRunService {
    @Autowired
    private ExperimentRunMapper experimentRunMapper;
    @Override
    public void deleteConfig(@Param("id")Integer id) {
        experimentRunMapper.deleteConfig(id);
    }

    @Override
    public ExperimentConfig selectConfig(@Param("id") String id) {
        ExperimentConfig experimentConfig = experimentRunMapper.selectConfig(id);
        return experimentConfig;
    }

    @Override
    public List<ExperimentResult> selectResult(Integer experimentcongfigid) {
        List<ExperimentResult> experimentResultList = experimentRunMapper.selectResult(experimentcongfigid);
        return experimentResultList;
    }

    @Override
    public void saveResult(ExperimentResult experimentResult) {
        experimentRunMapper.saveResult(experimentResult);
    }

    @Override
    public void deleteResult(Integer experimentcongfigid) {
        experimentRunMapper.deleteResult(experimentcongfigid);
    }

    @Override
    public void updateProgrammeId(Integer programmeid, Integer id) {
        experimentRunMapper.updateProgrammeId(programmeid,id);
    }
}
