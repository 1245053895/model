package com.xd.zt.domain.experiment;

public class ExperimentConfig {
    private Integer id;
    private String param;
    private Integer analyprocessid;
    private Integer experimentid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public Integer getAnalyprocessid() {
        return analyprocessid;
    }

    public void setAnalyprocessid(Integer analyprocessid) {
        this.analyprocessid = analyprocessid;
    }

    public Integer getExperimentid() {
        return experimentid;
    }

    public void setExperimentid(Integer experimentid) {
        this.experimentid = experimentid;
    }
}
