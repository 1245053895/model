package com.xd.zt.serviceImpl.model;

import com.xd.zt.domain.analyse.AnalyseModel;
import com.xd.zt.domain.business.BusinessModel;
import com.xd.zt.domain.data.DatamodelName;
import com.xd.zt.domain.model.Programme;
import com.xd.zt.mapper.model.ModelMapper;
import com.xd.zt.service.model.ModelService;
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
    public void insertProgram(Programme programme) {
        modelMapper.insertProgram(programme);
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
}
