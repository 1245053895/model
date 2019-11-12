package com.xd.zt.mapper.dataManage;

import com.xd.zt.domain.dataManage.DataManage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface DataManageMapper {

    List<DataManage> selectDataList();
    List<DataManage> selectDataList1();
    List<DataManage> selectDataList2();
}