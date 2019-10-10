package com.xd.zt.domain.data;

import java.util.Date;

public class DatamodelJi {
    private Integer jiid;

    private String jiname;

    private String jitype;

    private Date jitime;

    private Integer baoid;

    private Integer modelid;

    private String baoname;

    public String getBaoname() {
        return baoname;
    }

    public void setBaoname(String baoname) {
        this.baoname = baoname;
    }

    public Integer getModelid() {
        return modelid;
    }

    public void setModelid(Integer modelid) {
        this.modelid = modelid;
    }

    public Integer getJiid() {
        return jiid;
    }

    public void setJiid(Integer jiid) {
        this.jiid = jiid;
    }

    public String getJiname() {
        return jiname;
    }

    public void setJiname(String jiname) {
        this.jiname = jiname == null ? null : jiname.trim();
    }

    public String getJitype() {
        return jitype;
    }

    public void setJitype(String jitype) {
        this.jitype = jitype == null ? null : jitype.trim();
    }

    public Date getJitime() {
        return jitime;
    }

    public void setJitime(Date jitime) {
        this.jitime = jitime;
    }

    public Integer getBaoid() {
        return baoid;
    }

    public void setBaoid(Integer baoid) {
        this.baoid = baoid;
    }
}