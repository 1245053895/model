package com.xd.zt.serviceImpl.business;


import com.xd.zt.domain.business.BusinessModel;
import com.xd.zt.domain.business.BusinessScene;
import com.xd.zt.mapper.business.BusinessModelMapper;
import com.xd.zt.service.business.BusinessModelService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessModelServiceImpl implements BusinessModelService {
    @Autowired
    private BusinessModelMapper businessModelMapper;

    @Override
    public List<BusinessModel> selectbusinessmodel() {
        List<BusinessModel> businessModelList= businessModelMapper.selectbusinessmodel();
        return businessModelList;
    }

    @Override
    public List<BusinessScene> selectAllNode(@Param("businessid") String businessid) {
       List<BusinessScene> businessNodeList= businessModelMapper.selectAllNode(businessid);
        return businessNodeList;
    }

    @Override
    public Integer selectprocesid(Integer businessid) {
        Integer processid = businessModelMapper.selectprocesid(businessid);
        return processid;
    }

    @Override
    public List<BusinessModel> searchBusiness(String businessname) {
        List<BusinessModel> businessModelList = businessModelMapper.searchBusiness(businessname);
        return businessModelList;
    }


}
