package com.xd.zt.domain.analyse;

public class AnalyseInstance {

    private int modelinstanceid;
    private String modelinstancename;
    private int modelid;
    private String parameters;
    private int algorithmid;

    public int getModelinstanceid() {
        return modelinstanceid;
    }

    public void setModelinstanceid(int modelinstanceid) {
        this.modelinstanceid = modelinstanceid;
    }

    public String getModelinstancename() {
        return modelinstancename;
    }

    public void setModelinstancename(String modelinstancename) {
        this.modelinstancename = modelinstancename;
    }

    public int getAlgorithmid() {
        return algorithmid;
    }

    public void setAlgorithmid(int algorithmid) {
        this.algorithmid = algorithmid;
    }






    public int getModelid() {
        return modelid;
    }

    public void setModelid(int modelid) {
        this.modelid = modelid;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }


}