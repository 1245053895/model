package com.xd.zt.mapper.dataManage;

import com.xd.zt.domain.dataManage.DataManage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface DataManageMapper {

    List<DataManage> selectDataList();
    List<DataManage> selectDataList1();
    List<DataManage> selectDataList2();

    public List<DataManage> moHuDataList(@Param("res") String res);
    public List<DataManage> moHuDataList1(@Param("res") String res);
    public List<DataManage> moHuDataList2(@Param("res") String res);
}