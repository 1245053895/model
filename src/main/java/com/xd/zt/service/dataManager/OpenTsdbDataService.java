package com.xd.zt.service.dataManager;

import com.xd.zt.domain.dataManage.UploadData;
import org.springframework.stereotype.Service;

@Service
public interface OpenTsdbDataService {
    void insertOpenTsdbData(UploadData uploadData);
}
