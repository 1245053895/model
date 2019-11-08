package com.xd.zt.domain.analyse;

public class AnalyseCsv {
    Integer id;
    String csvname;
    String csvpath;
    Integer modelinstanceid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCsvname() {
        return csvname;
    }

    public void setCsvname(String csvname) {
        this.csvname = csvname;
    }

    public String getCsvpath() {
        return csvpath;
    }

    public void setCsvpath(String csvpath) {
        this.csvpath = csvpath;
    }

    public Integer getModelinstanceid() {
        return modelinstanceid;
    }

    public void setModelinstanceid(Integer modelinstanceid) {
        this.modelinstanceid = modelinstanceid;
    }
}
