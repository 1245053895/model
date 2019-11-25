package com.xd.zt.serviceImpl.dataManager;

import com.xd.zt.domain.dataManage.DataManage;
import com.xd.zt.mapper.dataManage.DataManageMapper;
import com.xd.zt.mapper.dataManage.DataViewAndMapper;
import com.xd.zt.service.dataManager.DataViewAndServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataViewAndServicesImpl implements DataViewAndServices {
    @Autowired
    private DataViewAndMapper dataViewAndMapper;


    @Override
    public DataManage selectDatamanageById(Integer sourceid) {
        return dataViewAndMapper.selectDatamanageById(sourceid);
    }

    @Override
    public DataManage selectDatamodelInfo(Integer dataresultid) {
        return dataViewAndMapper.selectDatamodelInfo(dataresultid);
    }

    @Override
    public DataManage selectAnalyseCsv(Integer id) {
        return dataViewAndMapper.selectAnalyseCsv(id);
    }
}
