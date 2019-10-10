package com.xd.zt.service.business;


import com.xd.zt.domain.business.BusinessModel;
import com.xd.zt.domain.business.BusinessScene;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BusinessModelService {
    public List<BusinessModel> selectbusinessmodel();
    public List<BusinessScene> selectAllNode(@Param("businessid") String businessid);
    Integer selectprocesid(@Param("businessid") Integer businessid);
    List<BusinessModel> searchBusiness(@Param("businessname")String businessname);
}
