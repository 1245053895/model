package com.xd.zt.domain.experiment;

public class ExperimentConfig {
    private Integer id;
    private String param;
    private Integer analyprocessid;
    private Integer experimentid;
    private String configname;
    private String configflow;
    private String dataid;
    private String algorithmid;

    public String getConfigname() {
        return configname;
    }

    public void setConfigname(String configname) {
        this.configname = configname;
    }

    public String getConfigflow() {
        return configflow;
    }

    public void setConfigflow(String configflow) {
        this.configflow = configflow;
    }

    public String getDataid() {
        return dataid;
    }

    public void setDataid(String dataid) {
        this.dataid = dataid;
    }

    public String getAlgorithmid() {
        return algorithmid;
    }

    public void setAlgorithmid(String algorithmid) {
        this.algorithmid = algorithmid;
    }

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
