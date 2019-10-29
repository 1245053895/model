package com.xd.zt.domain.experiment;

public class ExperimentModel {
    private Integer id;
    private String testname;
    private Integer analysemodeid;
    private Integer processid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTestname() {
        return testname;
    }

    public void setTestname(String testname) {
        this.testname = testname;
    }

    public Integer getAnalysemodeid() {
        return analysemodeid;
    }

    public void setAnalysemodeid(Integer analysemodeid) {
        this.analysemodeid = analysemodeid;
    }

    public Integer getProcessid() {
        return processid;
    }

    public void setProcessid(Integer processid) {
        this.processid = processid;
    }
}
