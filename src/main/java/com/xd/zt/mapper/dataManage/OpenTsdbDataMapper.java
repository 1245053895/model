package com.xd.zt.mapper.dataManage;

import com.xd.zt.domain.dataManage.UploadData;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface OpenTsdbDataMapper {
    void insertOpenTsdbData(UploadData uploadData);
}
