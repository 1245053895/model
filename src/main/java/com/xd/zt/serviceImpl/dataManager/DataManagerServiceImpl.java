package com.xd.zt.serviceImpl.dataManager;

import com.xd.zt.domain.dataManage.DataManage;
import com.xd.zt.mapper.dataManage.DataManageMapper;
import com.xd.zt.service.dataManager.DataManagerService;
import org.apache.ibatis.annotations.Param;
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

    @Override
    public List<DataManage> moHuDataList(@Param("res") String res) {
        List<DataManage> moHuDataList=  dataManageMapper.moHuDataList(res);
        return moHuDataList;
    }

    @Override
    public List<DataManage> moHuDataList1(@Param("res")String res) {
        List<DataManage> moHuDataList2= dataManageMapper.moHuDataList1(res);
        return moHuDataList2;
    }

    @Override
    public List<DataManage> moHuDataList2(@Param("res")String res) {
      List<DataManage>  moHuDataList3= dataManageMapper.moHuDataList2(res);
        return moHuDataList3;
    }
}
