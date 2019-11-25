package com.xd.zt.serviceImpl.dataManager;

import com.xd.zt.domain.dataManage.MysqlData;
import com.xd.zt.domain.dataManage.UploadData;
import com.xd.zt.mapper.dataManage.OpenTsdbDataMapper;
import com.xd.zt.service.dataManager.OpenTsdbDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpenTsdbDataServiceImpl implements OpenTsdbDataService {
    @Autowired
    private OpenTsdbDataMapper openTsdbDataMapper;
    @Override
    public void insertOpenTsdbData(UploadData uploadData) {
        openTsdbDataMapper.insertOpenTsdbData(uploadData);
    }

    @Override
    public void insertMysqlData(MysqlData mysqlData) {
        openTsdbDataMapper.insertMysqlData(mysqlData);
    }
}
