
package com.xd.zt.repository.data;




import com.xd.zt.domain.data.DatamodelSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by KAIRUN on 2019/3/6.
 */

@Repository
public interface FileRepository extends JpaRepository<DatamodelSource,Integer> {
    Iterable<DatamodelSource> findByModeid(Integer modeid);

/*    //按名称进行模糊搜索
    List<DatamodelSource> findByfilenameLike(String sourcename);*/




}


