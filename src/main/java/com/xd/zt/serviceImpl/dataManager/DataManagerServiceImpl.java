package com.xd.zt.serviceImpl.dataManager;

import com.xd.zt.domain.dataManage.DataManage;
import com.xd.zt.mapper.dataManage.DataManageMapper;
import com.xd.zt.service.dataManager.DataManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DataManagerServiceImpl implements DataManagerService {

    @Autowired
    private DataManageMapper dataManageMapper;

    @Override
    public List<DataManage> selectDataList() {

        return dataManageMapper.selectDataList();
    }
    @Override
    public List<DataManage> selectDataList1() {

        return dataManageMapper.selectDataList1();
    }

    @Override
    public List<DataManage> selectDataList2() {
        return dataManageMapper.selectDataList2();
    }
}
