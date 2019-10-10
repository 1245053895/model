package com.xd.zt.domain.business;

public class BusinessLine {
    private Integer lineid;

    private String linename;

    private Integer flowid;

    private Integer businessid;

    private String linea;

    private String lineb;

    private String linec;

    public Integer getLineid() {
        return lineid;
    }

    public void setLineid(Integer lineid) {
        this.lineid = lineid;
    }

    public String getLinename() {
        return linename;
    }

    public void setLinename(String linename) {
        this.linename = linename == null ? null : linename.trim();
    }

    public Integer getFlowid() {
        return flowid;
    }

    public void setFlowid(Integer flowid) {
        this.flowid = flowid;
    }

    public Integer getBusinessid() {
        return businessid;
    }

    public void setBusinessid(Integer businessid) {
        this.businessid = businessid;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea == null ? null : linea.trim();
    }

    public String getLineb() {
        return lineb;
    }

    public void setLineb(String lineb) {
        this.lineb = lineb == null ? null : lineb.trim();
    }

    public String getLinec() {
        return linec;
    }

    public void setLinec(String linec) {
        this.linec = linec == null ? null : linec.trim();
    }
}