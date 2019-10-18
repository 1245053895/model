
package com.xd.zt.repository.business;



import com.xd.zt.domain.business.BusinessKnowledge;
import com.xd.zt.domain.business.BusinessType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by KAIRUN on 2019/3/6.
 */

@Repository
public interface KnowledgeRepository extends JpaRepository<BusinessKnowledge,Integer> {

}


