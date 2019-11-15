package com.xd.zt.serviceImpl.model;

import com.xd.zt.domain.experiment.ExperimentConfig;
import com.xd.zt.domain.model.Programme;
import com.xd.zt.domain.model.ProgrammeResult;
import com.xd.zt.mapper.model.ModelEditorMapper;
import com.xd.zt.service.model.ModelEditorService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author:ykr
 * Date:2019/11/13
 * Description:
 */
@Service
public class ModelEditorServiceImpl implements ModelEditorService {
    @Autowired
    private ModelEditorMapper modelEditorMapper;
    @Override
    public ExperimentConfig getModelFlowByProgrammeId(@Param("programmeid") Integer programmeid) {
     ExperimentConfig experimentConfig=   modelEditorMapper.getModelFlowByProgrammeId(programmeid);
        return experimentConfig;
    }

    @Override
    public Integer queryExperimentIdByProgrammeId(@Param("programmeid") Integer programmeid) {
      Integer experimentid=  modelEditorMapper.queryExperimentIdByProgrammeId(programmeid);
        return experimentid;
    }

    @Override
    public Programme getAllByProgrammeId(@Param("programmeid") Integer programmeid) {
       Programme programme= modelEditorMapper.getAllByProgrammeId(programmeid);
        return programme;
    }

    @Override
    public void updateProgrammeByProgrammeId(@Param("programmePathString") String programmepath, @Param("programmeid") String programmeid) {
        modelEditorMapper.updateProgrammeByProgrammeId(programmepath,programmeid);

    }

    @Override
    public List<ProgrammeResult> selectProgrammeResult(Integer programmeid) {
        List<ProgrammeResult> programmeResultList = modelEditorMapper.selectProgrammeResult(programmeid);
        return programmeResultList;
    }

    @Override
    public void saveProgrammeResult(ProgrammeResult programmeResult) {
        modelEditorMapper.saveProgrammeResult(programmeResult);
    }

    @Override
    public void deleteProgrammeResult(Integer programmeid) {
        modelEditorMapper.deleteProgrammeResult(programmeid);
    }


}
