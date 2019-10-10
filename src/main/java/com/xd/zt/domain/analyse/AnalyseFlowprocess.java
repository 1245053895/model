package com.xd.zt.domain.analyse;

public class AnalyseFlowprocess {
    private Integer flowprocessid;
    private Integer modelid;
    private Integer processid;
    private String flowprocessname;
    private String databag;
    private String resultdefine;

    public String getDatabag() {
        return databag;
    }

    public void setDatabag(String databag) {
        this.databag = databag;
    }

    public String getResultdefine() {
        return resultdefine;
    }

    public void setResultdefine(String resultdefine) {
        this.resultdefine = resultdefine;
    }

    public String getFlowprocessname() {
        return flowprocessname;
    }

    public void setFlowprocessname(String flowprocessname) {
        this.flowprocessname = flowprocessname;
    }

    public Integer getFlowprocessid() {
        return flowprocessid;
    }

    public void setFlowprocessid(Integer flowprocessid) {
        this.flowprocessid = flowprocessid;
    }

    public Integer getModelid() {
        return modelid;
    }

    public void setModelid(Integer modelid) {
        this.modelid = modelid;
    }

    public Integer getProcessid() {
        return processid;
    }

    public void setProcessid(Integer processid) {
        this.processid = processid;
    }
}
