package com.xd.zt.mapper.data;


import com.xd.zt.domain.business.BusinessModel;
import com.xd.zt.domain.business.BusinessQuestion;
import com.xd.zt.domain.data.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DataModelMapper {
    List<DatamodelName> selectdatamodel();

    List<DatamodelName> findByModelnameLike(String res);

    void dataModelDelete(@Param("modelid") Integer modelid);

    DatamodelName isExitDataModel(@Param("modelid") Integer modelid);

    void savedatamodel(@Param("datamodelname") String datamodelname);

    List<DatamodelLink> dataModelLink(Integer modeid);

    List<DatamodelArea> dataModelLogic(Integer modeid);

    String dataModelLinkName(Integer linkid);

    String areaResultName(Integer areaid);

    String areaResultid(Integer areaid);

    List<String> blockResultName(String blockid);

    String blockResultAddr(Integer blockid);

    List<DatamodelBlock> dataModelBlock(Integer modeid);

    List<String> selectAreaName(Integer blockid);

    List<DatamodelBao> selectBaoId(Integer modeid);

    List<DatamodelInfo> selectBaoInfo(Integer baoid);

    List<DatamodelJi> selectDataCollect(Integer modeid);

    List<Integer> blockResultid(@Param("blockid") String blockid);


//
    List<BusinessModel> allBusinessModel();
    List<BusinessQuestion> selectquestion(int businessid);

    void addDatamoddelName(DatamodelName datamodelName);
}

