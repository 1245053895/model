package com.xd.zt.serviceImpl.experiment;
import com.xd.zt.domain.experiment.ExperimentData;
import com.xd.zt.mapper.experiment.ExperimentDataMapper;
import com.xd.zt.service.experiment.ExperimentDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class  ExperimentDataServiceImpl implements ExperimentDataService {
@Autowired
private ExperimentDataMapper experimentDataMapper;

    @Override
    public ExperimentData modelDataByExperimentId(Integer experimentid) {
      ExperimentData experimentData=  experimentDataMapper.modelDataByExperimentId(experimentid);
        return experimentData;
    }
}
