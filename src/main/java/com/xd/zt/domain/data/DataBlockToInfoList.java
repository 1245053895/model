package com.xd.zt.domain.data;

import java.util.List;

public class DataBlockToInfoList {
    Integer blockid;
    List<DatamodelInfo> datamodelInfoList;

    public Integer getBlockid() {
        return blockid;
    }

    public void setBlockid(Integer blockid) {
        this.blockid = blockid;
    }

    public List<DatamodelInfo> getDatamodelInfoList() {
        return datamodelInfoList;
    }

    public void setDatamodelInfoList(List<DatamodelInfo> datamodelInfoList) {
        this.datamodelInfoList = datamodelInfoList;
    }
}
