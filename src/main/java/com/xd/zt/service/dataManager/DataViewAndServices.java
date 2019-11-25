package com.xd.zt.service.dataManager;

import com.xd.zt.domain.dataManage.DataManage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface DataViewAndServices {
    DataManage selectDatamanageById (@Param("sourceid") Integer sourceid);
    DataManage selectDatamodelInfo(@Param("dataresultid") Integer dataresultid);
    DataManage selectAnalyseCsv(@Param("id") Integer id);
}
