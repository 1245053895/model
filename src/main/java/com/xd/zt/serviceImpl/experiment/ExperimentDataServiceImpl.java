package com.xd.zt.serviceImpl.experiment;
import com.xd.zt.domain.data.DatamodelInfo;
import com.xd.zt.domain.experiment.ExperimentData;
import com.xd.zt.mapper.experiment.ExperimentDataMapper;
import com.xd.zt.service.experiment.ExperimentDataService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.List;

@Service
public class  ExperimentDataServiceImpl implements ExperimentDataService {
@Autowired
private ExperimentDataMapper experimentDataMapper;

    @Override
    public ExperimentData modelDataByExperimentId(Integer experimentid) {
      ExperimentData experimentData=  experimentDataMapper.modelDataByExperimentId(experimentid);
        return experimentData;
    }

    @Override
    public List<ExperimentData> selectFileByExperimetnId(@Param("experimentid") Integer experimentid) {
        List<ExperimentData> experimentDataList=  experimentDataMapper.selectFileByExperimetnId(experimentid);
        return experimentDataList;
    }

    @Override
    public List<ExperimentData> moHuDataFile(@Param("res") String res) {
        List<ExperimentData> experimentDataList=  experimentDataMapper.moHuDataFile(res);
        return experimentDataList;
    }

    @Override
    public ExperimentData selectDataFileById(@Param("id") Integer id) {
      ExperimentData experimentData=  experimentDataMapper.selectDataFileById(id);
        return experimentData;
    }

    @Override
    public List<DatamodelInfo> selectDataBao(@Param("id")Integer id) {
        List<DatamodelInfo> datamodelInfoList = experimentDataMapper.selectDataBao(id);
        return datamodelInfoList;
    }
}
