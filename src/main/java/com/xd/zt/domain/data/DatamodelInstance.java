package com.xd.zt.domain.data;

public class DatamodelInstance {
    private  Integer instanceid;
    private String instancename;
    private Integer modelid;
    private Integer algorithmid;
    private String parameters;
    private Integer areaid;
    private  Integer blockid;

    public Integer getInstanceid() {
        return instanceid;
    }

    public void setInstanceid(Integer instanceid) {
        this.instanceid = instanceid;
    }

    public String getInstancename() {
        return instancename;
    }

    public void setInstancename(String instancename) {
        this.instancename = instancename;
    }

    public Integer getModelid() {
        return modelid;
    }

    public void setModelid(Integer modelid) {
        this.modelid = modelid;
    }

    public Integer getAlgorithmid() {
        return algorithmid;
    }

    public void setAlgorithmid(Integer algorithmid) {
        this.algorithmid = algorithmid;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public Integer getAreaid() {
        return areaid;
    }

    public void setAreaid(Integer areaid) {
        this.areaid = areaid;
    }

    public Integer getBlockid() {
        return blockid;
    }

    public void setBlockid(Integer blockid) {
        this.blockid = blockid;
    }
}
