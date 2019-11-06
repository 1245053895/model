
package com.xd.zt.repository.experiment;




import com.xd.zt.domain.data.DatamodelSource;
import com.xd.zt.domain.experiment.ExperimentData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by KAIRUN on 2019/3/6.
 */

@Repository
public interface DataFileRepository extends JpaRepository<ExperimentData,Integer> {
  /*  Iterable<ExperimentData> findByModeid(Integer modeid);*/

/*    //按名称进行模糊搜索
    List<DatamodelSource> findByfilenameLike(String sourcename);*/




}


