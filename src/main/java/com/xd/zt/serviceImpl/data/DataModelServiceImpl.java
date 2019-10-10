package com.xd.zt.serviceImpl.data;


import com.xd.zt.domain.business.BusinessModel;
import com.xd.zt.domain.business.BusinessQuestion;
import com.xd.zt.domain.data.*;
import com.xd.zt.mapper.data.DataModelMapper;
import com.xd.zt.service.data.DataModelService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DataModelServiceImpl implements DataModelService {
    @Autowired
    private DataModelMapper dataModelMapper;

    @Override
    public List<DatamodelName> selectdatamodel() {
        List<DatamodelName> dataModelList = dataModelMapper.selectdatamodel();
        return dataModelList;
    }

    @Override
    public Iterable<DatamodelName> findByModelnameLike(String res) {
        return dataModelMapper.findByModelnameLike(res);
    }

    @Override
    public void dataModelDelete(@Param("modelid") Integer modelid) {
        dataModelMapper.dataModelDelete(modelid);
    }

    @Override
    public DatamodelName isExitDataModel(@Param("modelid") Integer modelid) {
        DatamodelName dataModel = dataModelMapper.isExitDataModel(modelid);
        return dataModel;
    }

    @Override
    public void savedatamodel(@Param("datamodelname") String datamodelname) {
        dataModelMapper.savedatamodel(datamodelname);
    }

    @Override
    public List<DatamodelLink> dataModelLink(Integer modeid) {
        return dataModelMapper.dataModelLink(modeid);
    }

    @Override
    public List<DatamodelArea> dataModelLogic(Integer modeid) {
        return dataModelMapper.dataModelLogic(modeid);
    }

    @Override
    public String dataModelLinkName(Integer linkid) {
        return dataModelMapper.dataModelLinkName(linkid);
    }

    @Override
    public String areaResultName(Integer areaid) {
        return dataModelMapper.areaResultName(areaid);
    }

    @Override
    public String areaResultid(Integer areaid) {
        return dataModelMapper.areaResultid(areaid);
    }

    @Override
    public List<String> blockResultName(String blockid) {
        return dataModelMapper.blockResultName(blockid);
    }

    @Override
    public String blockResultAddr(Integer blockid) {
        return dataModelMapper.blockResultAddr(blockid);
    }

    @Override
    public List<DatamodelBlock> dataModelBlock(Integer modeid) {
        return dataModelMapper.dataModelBlock(modeid);
    }

    @Override
    public List<String> selectAreaName(Integer blockid) {
        return dataModelMapper.selectAreaName(blockid);
    }

    @Override
    public List<DatamodelBao> selectBaoId(Integer modeid) {
        return dataModelMapper.selectBaoId(modeid);
    }

    @Override
    public List<DatamodelInfo> selectBaoInfo(Integer baoid) {
        return dataModelMapper.selectBaoInfo(baoid);
    }

    @Override
    public List<DatamodelJi> selectDataCollect(Integer modeid) {
        return dataModelMapper.selectDataCollect(modeid);
    }

    @Override
    public List<Integer> blockResultid(String blockid) {
        return dataModelMapper.blockResultid(blockid);
    }


//
@Override
public List<BusinessModel> allBusinessModel() {
    return dataModelMapper.allBusinessModel();
}

    @Override
    public List<BusinessQuestion> selectquestion(int businessid) {
        return dataModelMapper.selectquestion(businessid);
    }

}



