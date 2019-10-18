package com.xd.zt.mapper.model;

import com.xd.zt.domain.analyse.AnalyseModel;
import com.xd.zt.domain.business.BusinessModel;
import com.xd.zt.domain.data.DatamodelName;
import com.xd.zt.domain.model.Programme;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ModelMapper {
    List<Programme> selectAllModel();
    void insertProgram(Programme programme);

    //根据工程id查询三大建模
    List<BusinessModel> selectBusinessModelByProgramme(@Param("programmeid") Integer programmeid);
    List<DatamodelName> selectDataModelByProgramme(@Param("programmeid") Integer programmeid);
    List<AnalyseModel> selectAnalyseModelByProgramme(@Param("programmeid") Integer programmeid);
}
