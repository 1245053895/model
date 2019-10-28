package com.xd.zt.service.experiment;
import com.xd.zt.domain.analyse.AnalyseModel;
import org.springframework.stereotype.Service;
import com.xd.zt.mapper.experiment.ExperimentMapper;
import java.util.ArrayList;
@Service
public interface ExperimentService {

    ArrayList modelConfiguration();
}
