package com.xd.zt.mapper.algorithm;


import com.xd.zt.domain.analyse.Algorithm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface AlgorithmDebugMapper {
//    查询所有算法
    List<Algorithm> selectAlgorithm();

    //根据id查询算法
    Algorithm selectAlgorithmById(@Param("algorithmid") Integer algorithmid);

    //根据名字模糊搜索
    List<Algorithm> searchAlgorithm(@Param("searchcontent") String searchcontent);

    //删除算法
    void deleteAlgorithm(@Param("algorithmid") String algorithmid);

    void insertAlgorithm(Algorithm algorithm);
////模型
//    //查询所有模型
//    List<AlgorithmModel> selectAlgorithmModel();
//    void deleteAlgorithmModel(@Param("algorithmmodelid") String algorithmmodelid);
//    //根据id查询算法
//    AlgorithmModel selectAlgorithmModelById(@Param("algorithmmodelid") Integer algorithmmodelid);
}
