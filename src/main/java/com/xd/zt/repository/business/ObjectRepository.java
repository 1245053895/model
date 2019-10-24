
package com.xd.zt.repository.business;



import com.xd.zt.domain.business.BusinessFile;
import com.xd.zt.domain.business.BusinessObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by KAIRUN on 2019/3/6.
 */

@Repository
public interface ObjectRepository extends JpaRepository<BusinessObject,Integer> {

}


