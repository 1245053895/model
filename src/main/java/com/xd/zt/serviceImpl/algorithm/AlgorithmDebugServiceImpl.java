package com.xd.zt.serviceImpl.algorithm;


import com.xd.zt.domain.analyse.Algorithm;
import com.xd.zt.mapper.algorithm.AlgorithmDebugMapper;
import com.xd.zt.service.algorithm.AlgorithmDebugService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlgorithmDebugServiceImpl implements AlgorithmDebugService {
    @Autowired
    private AlgorithmDebugMapper algorithmDebugMapper;


    @Override
    public List<Algorithm> selectAlgorithm() {
      List<Algorithm> algorithmList=  algorithmDebugMapper.selectAlgorithm();
        return algorithmList;
    }

    @Override
    public List<Algorithm> selectAlgorithmCommon(@Param("algorithmlabel") String algorithmlabel) {
      List<Algorithm> algorithmList=  algorithmDebugMapper.selectAlgorithmCommon(algorithmlabel);
        return algorithmList;
    }

    @Override
    public List<Algorithm> selectAlgorithmProcess(@Param("algorithmlabel") String algorithmlabel) {
      List<Algorithm> algorithmProcess=  algorithmDebugMapper.selectAlgorithmProcess(algorithmlabel);
        return algorithmProcess;
    }

    @Override
    public List<Algorithm> selectAlgorithmLogical(@Param("algorithmlabel") String algorithmlabel) {
      List<Algorithm> algorithmLogical=  algorithmDebugMapper.selectAlgorithmLogical(algorithmlabel);
        return algorithmLogical;
    }

    @Override
    public Algorithm selectAlgorithmById(Integer algorithmid) {
        Algorithm algorithm = algorithmDebugMapper.selectAlgorithmById(algorithmid);
        return algorithm;
    }

    @Override
    public List<Algorithm> searchAlgorithm(String searchcontent) {
        List<Algorithm> algorithmList = algorithmDebugMapper.searchAlgorithm(searchcontent);
        return algorithmList;
    }

    @Override
    public void deleteAlgorithm(String algorithmid) {
        algorithmDebugMapper.deleteAlgorithm(algorithmid);
    }

//    @Override
//    public void saveAlgorithm(@Param("algorithmname")String algorithmname,@Param("algorithmtype")String algorithmtype,@Param("algorithmdescribe")String algorithmdescribe, @Param("algorithmlabel")String algorithmlabel,@Param("algorithmtime")String algorithmtime,@Param("algorithmversion")String algorithmversion, @Param("algorithmparams")String algorithmparams,@Param("algorithmpath")String algorithmpath,@Param("algorithmman")String algorithmman) {
//        algorithmDebugMapper.saveAlgorithm(algorithmname,algorithmtype,algorithmdescribe,algorithmlabel,algorithmtime,algorithmversion,algorithmparams,algorithmpath,algorithmman);
//    }

    @Override
    public void saveAlgorithm(Algorithm algorithm) {
        algorithmDebugMapper.saveAlgorithm(algorithm);
    }

    @Override
    public void insertAlgorithm(Algorithm algorithm) {
        algorithmDebugMapper.insertAlgorithm(algorithm);
    }

/*    @Override
    public void saveAlgorithm(Algorithm algorithm) {
        algorithmDebugMapper.saveAlgorithm(algorithm);
    }*/


//    @Override
//    public List<AlgorithmModel> selectAlgorithmModel() {
//        List<AlgorithmModel> algorithmModelList = algorithmDebugMapper.selectAlgorithmModel();
//        return algorithmModelList;
//    }
//
//    @Override
//    public void deleteAlgorithmModel(String algorithmmodelid) {
//        algorithmDebugMapper.deleteAlgorithmModel(algorithmmodelid);
//    }
//
//    @Override
//    public AlgorithmModel selectAlgorithmModelById(Integer algorithmmodelid) {
//        AlgorithmModel algorithmModel = algorithmDebugMapper.selectAlgorithmModelById(algorithmmodelid);
//        return algorithmModel;
//    }
}
