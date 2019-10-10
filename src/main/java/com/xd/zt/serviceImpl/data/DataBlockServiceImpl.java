package com.xd.zt.serviceImpl.data;


import com.xd.zt.domain.data.DatamodelBao;
import com.xd.zt.domain.data.DatamodelBlock;
import com.xd.zt.domain.data.DatamodelInfo;
import com.xd.zt.mapper.data.DataBlockMapper;
import com.xd.zt.service.data.DataBlockService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataBlockServiceImpl implements DataBlockService {

@Autowired
private DataBlockMapper dataBlockMapper;
    @Override
    public List<DatamodelBlock> selectBlockById(@Param("modelid") Integer modelid) {
        List<DatamodelBlock> datamodelBlockList = dataBlockMapper.selectBlockById(modelid);
        return datamodelBlockList;
    }

    @Override
    public List<DatamodelInfo> selectDataAreaResultById(@Param("modelid") Integer modelid) {
        List<DatamodelInfo> datamodelInfoList = dataBlockMapper.selectDataAreaResultById(modelid);
        return datamodelInfoList;
    }

    @Override
    public void saveDataBlockResult(@Param("modelid") String modelid,@Param("blockname") String blockname,@Param("flowchart") String flowchart,@Param("dataarea") String dataarea) {
        dataBlockMapper.saveDataBlockResult(modelid, blockname,flowchart,dataarea);
    }

    @Override
    public void deleteblock(@Param("blockid") String blockid) {
        dataBlockMapper.deleteblock(blockid);
    }




    @Override
    public List<DatamodelBao> selectBaoById(Integer modelid) {
        List<DatamodelBao> datamodelBaoList = dataBlockMapper.selectBaoById(modelid);
        return datamodelBaoList;
    }

    @Override
    public List<DatamodelInfo> selectDataBlockResultById(Integer modelid) {
        List<DatamodelInfo> datamodelInfoList = dataBlockMapper.selectDataBlockResultById(modelid);
        return datamodelInfoList;
    }


    @Override
    public String selectAreaName(Integer blockid) {
        return dataBlockMapper.selectAreaName(blockid);
    }

    @Override
    public DatamodelBlock selectCreateProcess(Integer blockid) {
        return dataBlockMapper.selectCreateProcess(blockid);
    }

    @Override
    public void saveDataBaoResult(String modelid, String baoname, String blockid) {
        dataBlockMapper.saveDataBaoResult(modelid,baoname,blockid);
    }

    @Override
    public void deletebao(@Param("baoid")Integer baoid) {
        dataBlockMapper.deletebao(baoid);
    }

    @Override
    public void deletebaoFromInfo(Integer baoid) {
        dataBlockMapper.deletebaoFromInfo(baoid);
    }

    @Override
    public void saveDataBaoCsvResult(String dataresultname,String databao,String blockid,String modelid,String dataaddr) {
        dataBlockMapper.saveDataBaoCsvResult(dataresultname,databao,blockid,modelid,dataaddr);
    }

    @Override
    public String selectDataBaoByBaoName(String baoname) {
        return dataBlockMapper.selectDataBaoByBaoName(baoname);
    }

    @Override
    public String selectAreaId(@Param("dataresultid") String dataresultid) {
        return dataBlockMapper.selectAreaId(dataresultid);
    }

    @Override
    public DatamodelInfo selectDataAreaResultByDatablock(String datablock) {
        DatamodelInfo datamodelInfo = dataBlockMapper.selectDataAreaResultByDatablock(datablock);
        return datamodelInfo;
    }
}
