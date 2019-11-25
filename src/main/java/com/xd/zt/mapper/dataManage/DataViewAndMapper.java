package com.xd.zt.mapper.dataManage;


import com.xd.zt.domain.dataManage.DataManage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DataViewAndMapper {
    DataManage selectDatamanageById (@Param("sourceid") Integer sourceid);
    DataManage selectDatamodelInfo(@Param("dataresultid") Integer dataresultid);
    DataManage selectAnalyseCsv(@Param("id") Integer id);
}
