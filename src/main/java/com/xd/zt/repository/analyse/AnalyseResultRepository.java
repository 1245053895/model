
package com.xd.zt.repository.analyse;




import com.xd.zt.domain.analyse.AnalyseCsv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by KAIRUN on 2019/3/6.
 */

@Repository
public interface AnalyseResultRepository extends JpaRepository<AnalyseCsv,Integer> {
/*    Iterable<AnalyseCsv> findById(Integer resultid);*/

/*    //按名称进行模糊搜索
    List<DatamodelSource> findByfilenameLike(String sourcename);*/




}


