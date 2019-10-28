package com.xd.zt.serviceImpl.experiment;

import com.xd.zt.mapper.experiment.ExperimentMapper;
import com.xd.zt.service.experiment.ExperimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class experimentServiceImp implements ExperimentService {

    @Autowired
    private ExperimentMapper experimentMapper;

    @Override
    public ArrayList modelConfiguration() {
        return experimentMapper.modelConfiguration();
    }
}
