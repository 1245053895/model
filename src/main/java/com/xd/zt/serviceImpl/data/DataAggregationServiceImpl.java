package com.xd.zt.serviceImpl.data;


import com.xd.zt.domain.data.DatamodelBao;
import com.xd.zt.domain.data.DatamodelJi;
import com.xd.zt.domain.data.DatamodelName;
import com.xd.zt.mapper.data.DataAggregationMapper;
import com.xd.zt.service.data.DataAggregationService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DataAggregationServiceImpl implements DataAggregationService {
    @Autowired
    private DataAggregationMapper dataAggregationMapper;

    @Override
    public void saveDataPack(String jiname, String jitype, String jitime, String baoid, String modelid) {
        dataAggregationMapper.saveDataPack(jiname,jitype,jitime,baoid,modelid);
    }

    @Override
    public DatamodelJi selectDataCollect(Integer jiid) {
        return dataAggregationMapper.selectDataCollect(jiid);
    }

    @Override
    public DatamodelBao selectDataBaoByBaoId(Integer baoid) {
        return dataAggregationMapper.selectDataBaoByBaoId(baoid);
    }

    @Override
    public List<DatamodelJi> selectDataCollec(Integer modelid) {

        return dataAggregationMapper.selectDataCollec(modelid);
    }

    @Override
    public List<DatamodelBao> selectDataPack(Integer modelid) {

        return dataAggregationMapper.selectDataPack(modelid);
    }

    @Override
    public List<DatamodelBao> findByPacknameLike(@Param("modelid") String modelid,String name) {
        return dataAggregationMapper.findByPacknameLike(modelid,name);
    }

    @Override
    public void deletDataCollect(@Param("datacollecid") Integer datacollecid) {
        dataAggregationMapper.deletDataCollect(datacollecid);

    }

    @Override
    public void saveModelName(String modelname) {
        dataAggregationMapper.saveModelName(modelname);
    }

    @Override
    public String addDatamoddelName(DatamodelName datamodelName) {
        String datainfo= "";
        Boolean msg =dataAggregationMapper.addDatamoddelName(datamodelName);
        if(msg){
            datainfo = "保存成功";
        }else {
            datainfo = "保存失败";
        }
        return datainfo;
    }

}



