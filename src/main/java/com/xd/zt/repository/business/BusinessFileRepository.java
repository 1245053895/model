
package com.xd.zt.repository.business;



import com.xd.zt.domain.business.BusinessFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by KAIRUN on 2019/3/6.
 */

@Repository
public interface BusinessFileRepository extends JpaRepository<BusinessFile,Integer> {

    //按名称进行模糊搜索
    List<BusinessFile> findByfilenameLike(String name);

    List<BusinessFile> findAllByBusinessid(Integer businessid);

}


