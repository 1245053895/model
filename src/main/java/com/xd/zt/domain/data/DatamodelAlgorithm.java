package com.xd.zt.domain.data;

public class DatamodelAlgorithm {
    private Integer algorithmid;

    private String algorithmname;

    private String algorithmtype;

    private String algorithmdescribe;

    public Integer getAlgorithmid() {
        return algorithmid;
    }

    public void setAlgorithmid(Integer algorithmid) {
        this.algorithmid = algorithmid;
    }

    public String getAlgorithmname() {
        return algorithmname;
    }

    public void setAlgorithmname(String algorithmname) {
        this.algorithmname = algorithmname == null ? null : algorithmname.trim();
    }

    public String getAlgorithmtype() {
        return algorithmtype;
    }

    public void setAlgorithmtype(String algorithmtype) {
        this.algorithmtype = algorithmtype == null ? null : algorithmtype.trim();
    }

    public String getAlgorithmdescribe() {
        return algorithmdescribe;
    }

    public void setAlgorithmdescribe(String algorithmdescribe) {
        this.algorithmdescribe = algorithmdescribe == null ? null : algorithmdescribe.trim();
    }
}