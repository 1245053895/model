package com.xd.zt.mapper.algorithm;


import com.xd.zt.domain.analyse.Algorithm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface AlgorithmDebugMapper {
    List<Algorithm> selectAlgorithm();
//    查询行业通用算法
    List<Algorithm> selectAlgorithmCommon(@Param("algorithmlabel") String algorithmlabel);

    //    查询行业专用算法
    List<Algorithm> selectAlgorithmProcess(@Param("algorithmlabel") String algorithmlabel);
    //    查询人工智能算法
    List<Algorithm> selectAlgorithmLogical(@Param("algorithmlabel") String algorithmlabel);


    //根据id查询算法
    Algorithm selectAlgorithmById(@Param("algorithmid") Integer algorithmid);

    //根据名字模糊搜索
    List<Algorithm> searchAlgorithm(@Param("searchcontent") String searchcontent);

    //删除算法
    void deleteAlgorithm(@Param("algorithmid") String algorithmid);

    void insertAlgorithm(Algorithm algorithm);

//    void saveAlgorithm(@Param("algorithmname")String algorithmname,@Param("algorithmtype")String algorithmtype,@Param("algorithmdescribe")String algorithmdescribe, @Param("algorithmlabel")String algorithmlabel,@Param("algorithmtime")String algorithmtime,@Param("algorithmversion")String algorithmversion, @Param("algorithmparams")String algorithmparams,@Param("algorithmpath")String algorithmpath,@Param("algorithmman")String algorithmman);
        void saveAlgorithm(Algorithm algorithm);
////模型
//    //查询所有模型
//    List<AlgorithmModel> selectAlgorithmModel();
//    void deleteAlgorithmModel(@Param("algorithmmodelid") String algorithmmodelid);
//    //根据id查询算法
//    AlgorithmModel selectAlgorithmModelById(@Param("algorithmmodelid") Integer algorithmmodelid);
}
