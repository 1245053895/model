package com.xd.zt.mapper.data;


import com.xd.zt.domain.data.DatamodelBao;
import com.xd.zt.domain.data.DatamodelJi;
import com.xd.zt.domain.data.DatamodelName;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DataAggregationMapper {
     void saveDataPack(@Param("jiname") String jiname, @Param("jitype") String jitype, @Param("jitime") String jitime, @Param("baoid") String baoid, @Param("modelid") String modelid);
     DatamodelJi selectDataCollect(Integer jiid);
     DatamodelBao selectDataBaoByBaoId(Integer baoid);
     List<DatamodelJi> selectDataCollec(Integer modelid);
     List<DatamodelBao> selectDataPack(Integer modelid);
     List<DatamodelBao> findByPacknameLike(@Param("modelid") String modelid, String name);
     void deletDataCollect(@Param("datacollecid") Integer datacollecid);

     public void saveModelName(String modelname);

     boolean addDatamoddelName(DatamodelName datamodelName);
}

