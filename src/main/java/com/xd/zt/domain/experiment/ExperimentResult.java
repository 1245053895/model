package com.xd.zt.domain.experiment;

public class ExperimentResult {
    Integer id;
    String resultname;
    String resultpath;
    String datetime;
    Integer experimentid;
    Integer experimentcongfigid;

    public Integer getExperimentcongfigid() {
        return experimentcongfigid;
    }

    public void setExperimentcongfigid(Integer experimentcongfigid) {
        this.experimentcongfigid = experimentcongfigid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResultname() {
        return resultname;
    }

    public void setResultname(String resultname) {
        this.resultname = resultname;
    }

    public String getResultpath() {
        return resultpath;
    }

    public void setResultpath(String resultpath) {
        this.resultpath = resultpath;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Integer getExperimentid() {
        return experimentid;
    }

    public void setExperimentid(Integer experimentid) {
        this.experimentid = experimentid;
    }
}
