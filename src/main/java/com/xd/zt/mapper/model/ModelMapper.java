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
    List<Programme> selectAllModelByType(@Param("programmetype") String programmetype);
    void insertProgram(@Param("programmename") String programmename,@Param("programmetype") String programmetype,@Param("programmedescribe") String programmedescribe,@Param("programmetime")String programmetime,@Param("username")String username);

    //根据工程id查询三大建模
    List<BusinessModel> selectBusinessModelByProgramme(@Param("programmeid") Integer programmeid);
    List<DatamodelName> selectDataModelByProgramme(@Param("programmeid") Integer programmeid);
    List<AnalyseModel> selectAnalyseModelByProgramme(@Param("programmeid") Integer programmeid);

    void deleteModel(@Param("programmeid")Integer programmeid);

    void saveProgramme(Programme programme);

    Programme selectProgrammeById(@Param("programmeid")Integer programmeid);

    List<Programme> getAllList (@Param("programmename") String programmename);
}
