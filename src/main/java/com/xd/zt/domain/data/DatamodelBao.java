package com.xd.zt.domain.data;

import java.util.Date;

public class DatamodelBao {
    private Integer baoid;

    private String baoname;

    private Date baotime;

    private String blockid;

    private Integer algorithmid;

    private Integer modelid;

    private String blockname;

    private Integer dataresultid;

    public Integer getDataresultid() {
        return dataresultid;
    }

    public void setDataresultid(Integer dataresultid) {
        this.dataresultid = dataresultid;
    }

    public String getBlockname() {
        return blockname;
    }

    public void setBlockname(String blockname) {
        this.blockname = blockname;
    }

    public Integer getModelid() {
        return modelid;
    }

    public void setModelid(Integer modelid) {
        this.modelid = modelid;
    }

    public Integer getBaoid() {
        return baoid;
    }

    public void setBaoid(Integer baoid) {
        this.baoid = baoid;
    }

    public String getBaoname() {
        return baoname;
    }

    public void setBaoname(String baoname) {
        this.baoname = baoname == null ? null : baoname.trim();
    }

    public Date getBaotime() {
        return baotime;
    }

    public void setBaotime(Date baotime) {
        this.baotime = baotime;
    }

    public String getBlockid() {
        return blockid;
    }

    public void setBlockid(String blockid) {
        this.blockid = blockid;
    }

    public Integer getAlgorithmid() {
        return algorithmid;
    }

    public void setAlgorithmid(Integer algorithmid) {
        this.algorithmid = algorithmid;
    }
}