
package com.xd.zt.repository.business;



import com.xd.zt.domain.business.BusinessObject;
import com.xd.zt.domain.business.BusinessType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by KAIRUN on 2019/3/6.
 */

@Repository
public interface TypeRepository extends JpaRepository<BusinessType,Integer> {

}


