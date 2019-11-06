package com.xd.zt.domain.experiment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ExperimentData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增策略
    private Integer id;
    private String dataname;
    private String datasize;
    private String datatime;
    private String datatype;
    private String params;
    private Integer experimentid;
    private String datapath;

    public String getDatapath() {
        return datapath;
    }

    public void setDatapath(String datapath) {
        this.datapath = datapath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDataname() {
        return dataname;
    }

    public void setDataname(String dataname) {
        this.dataname = dataname;
    }

    public String getDatasize() {
        return datasize;
    }

    public void setDatasize(String datasize) {
        this.datasize = datasize;
    }

    public String getDatatime() {
        return datatime;
    }

    public void setDatatime(String datatime) {
        this.datatime = datatime;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Integer getExperimentid() {
        return experimentid;
    }

    public void setExperimentid(Integer experimentid) {
        this.experimentid = experimentid;
    }
}
