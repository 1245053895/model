package com.xd.zt.mapper.business;


import com.xd.zt.domain.business.BusinessModel;
import com.xd.zt.domain.business.BusinessScene;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BusinessModelMapper {
    public List<BusinessModel> selectbusinessmodel();

    public List<BusinessScene> selectAllNode(@Param("businessid") String businessid);

    Integer selectprocesid(@Param("businessid") Integer businessid);

    List<BusinessModel> searchBusiness(@Param("businessname")String businessname);
}

