package com.xd.zt.repository.business;



import com.xd.zt.domain.business.BusinessObject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*
 * @Description:    java类作用描述
 * @Author:         XiaYiTiao
 * @CreateDate:     2019/3/4 20:38
 * @Bug
 */
@Repository//接口注解
public interface BusinessObjectRepository extends CrudRepository<BusinessObject,Integer> {


}
