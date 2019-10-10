package com.xd.zt.domain.data;

import java.util.Date;

public class DatamodelBlock {
    private Integer blockid;

    private String blockname;

    private Date blocktime;

    private Integer processid;

    private Integer areaid;

    private Integer modelid;

    private String createprocess;

    private String areaname;

    private String dataresultname;

    private String dataaddr;

    private Integer dataresultid;

    public Integer getDataresultid() {
        return dataresultid;
    }

    public void setDataresultid(Integer dataresultid) {
        this.dataresultid = dataresultid;
    }

    public String getDataresultname() {
        return dataresultname;
    }

    public void setDataresultname(String dataresultname) {
        this.dataresultname = dataresultname;
    }

    public String getDataaddr() {
        return dataaddr;
    }

    public void setDataaddr(String dataaddr) {
        this.dataaddr = dataaddr;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public Integer getModelid() {
        return modelid;
    }

    public void setModelid(Integer modelid) {
        this.modelid = modelid;
    }


    public Integer getBlockid() {
        return blockid;
    }

    public void setBlockid(Integer blockid) {
        this.blockid = blockid;
    }

    public String getBlockname() {
        return blockname;
    }

    public void setBlockname(String blockname) {
        this.blockname = blockname == null ? null : blockname.trim();
    }

    public Date getBlocktime() {
        return blocktime;
    }

    public void setBlocktime(Date blocktime) {
        this.blocktime = blocktime;
    }

    public Integer getProcessid() {
        return processid;
    }

    public void setProcessid(Integer processid) {
        this.processid = processid;
    }

    public Integer getAreaid() {
        return areaid;
    }

    public void setAreaid(Integer areaid) {
        this.areaid = areaid;
    }

    public String getCreateprocess() {
        return createprocess;
    }

    public void setCreateprocess(String createprocess) {
        this.createprocess = createprocess;
    }
}