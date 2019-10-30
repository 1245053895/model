
package com.xd.zt.repository.experiment;



import com.xd.zt.domain.business.BusinessKnowledge;
import com.xd.zt.domain.experiment.ExperimentData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by KAIRUN on 2019/3/6.
 */

@Repository
public interface ExperimentRepository extends JpaRepository<ExperimentData,Integer> {

}


