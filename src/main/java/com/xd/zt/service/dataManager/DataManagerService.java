package com.xd.zt.service.dataManager;

import com.xd.zt.domain.dataManage.DataManage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DataManagerService {
    List<DataManage> selectDataList();
    List<DataManage> selectDataList1();
    List<DataManage> selectDataList2();
}
