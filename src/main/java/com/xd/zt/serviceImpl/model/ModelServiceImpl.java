package com.xd.zt.serviceImpl.model;

import com.xd.zt.domain.analyse.AnalyseModel;
import com.xd.zt.domain.business.BusinessModel;
import com.xd.zt.domain.data.DatamodelName;
import com.xd.zt.domain.model.Programme;
import com.xd.zt.mapper.model.ModelMapper;
import com.xd.zt.service.model.ModelService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {
    @Autowired
      private ModelMapper modelMapper;

    @Override
    public List<Programme> selectAllModel() {
        List<Programme> programmeList = modelMapper.selectAllModel();
        return programmeList;
    }

    @Override
    public List<Programme> selectAllModelByType(@Param("programmetype") String programmetype) {
        List<Programme> programmeList = modelMapper.selectAllModelByType(programmetype);
        return programmeList;
    }



    @Override
    public void deleteModel(Integer programmeid) {
        modelMapper.deleteModel(programmeid);
    }

    @Override
    public void saveProgramme(Programme programme) {
        modelMapper.saveProgramme(programme);
    }

    @Override
    public Programme selectProgrammeById(Integer programmeid) {
        Programme programme = modelMapper.selectProgrammeById(programmeid);
        return programme;
    }

    @Override
    public List<Programme> getAllList(String programmename) {
        return modelMapper.getAllList(programmename);
    }

    @Override
    public List<BusinessModel> selectBusinessModelByProgramme(Integer programmeid) {
        List<BusinessModel> businessModelList= modelMapper.selectBusinessModelByProgramme(programmeid);
        return businessModelList;
    }

    @Override
    public List<DatamodelName> selectDataModelByProgramme(Integer programmeid) {
        List<DatamodelName> datamodelNameList = modelMapper.selectDataModelByProgramme(programmeid);
        return datamodelNameList;
    }

    @Override
    public List<AnalyseModel> selectAnalyseModelByProgramme(Integer programmeid) {
        List<AnalyseModel> analyseModelList = modelMapper.selectAnalyseModelByProgramme(programmeid);
        return analyseModelList;
    }

    @Override
    public void insertProgram(@Param("programmename") String programmename,@Param("programmetype") String programmetype,@Param("programmedescribe") String programmedescribe,@Param("programmetime")String programmetime,@Param("username")String username) {
        modelMapper.insertProgram(programmename,programmetype,programmedescribe,programmetime,username);
    }
}
