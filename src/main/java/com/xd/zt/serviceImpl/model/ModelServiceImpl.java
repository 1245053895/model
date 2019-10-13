package com.xd.zt.serviceImpl.model;

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
}
