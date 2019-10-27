package com.xd.zt.service.model;

import com.xd.zt.domain.analyse.AnalyseModel;
import com.xd.zt.domain.business.BusinessModel;
import com.xd.zt.domain.data.DatamodelName;
import com.xd.zt.domain.model.Programme;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public interface ModelService {
    List<Programme> selectAllModel();
    List<Programme> selectAllModelByType(@Param("programmetype") String programmetype);

    //根据工程id查询三大建模
    List<BusinessModel> selectBusinessModelByProgramme(@Param("programmeid") Integer programmeid);
    List<DatamodelName> selectDataModelByProgramme(@Param("programmeid") Integer programmeid);
    List<AnalyseModel> selectAnalyseModelByProgramme(@Param("programmeid") Integer programmeid);
    void insertProgram(Programme programme);

    void deleteModel(@Param("programmeid")Integer programmeid);
    void saveProgramme(Programme programme);
    Programme selectProgrammeById(@Param("programmeid")Integer programmeid);
}
