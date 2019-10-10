package com.xd.zt.domain.business;

public class BusinessNode {
    private Integer nodeid;

    private String nodename;

    private Integer lineid;

    private Integer flowid;

    private Integer objectid;

    private Integer datatypeid;

    private Integer knowledgeid;

    private String nodestatus;

    private String nodea;

    private String nodeb;

    private String nodec;

    public Integer getNodeid() {
        return nodeid;
    }

    public void setNodeid(Integer nodeid) {
        this.nodeid = nodeid;
    }

    public String getNodename() {
        return nodename;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename == null ? null : nodename.trim();
    }

    public Integer getLineid() {
        return lineid;
    }

    public void setLineid(Integer lineid) {
        this.lineid = lineid;
    }

    public Integer getFlowid() {
        return flowid;
    }

    public void setFlowid(Integer flowid) {
        this.flowid = flowid;
    }

    public Integer getObjectid() {
        return objectid;
    }

    public void setObjectid(Integer objectid) {
        this.objectid = objectid;
    }

    public Integer getDatatypeid() {
        return datatypeid;
    }

    public void setDatatypeid(Integer datatypeid) {
        this.datatypeid = datatypeid;
    }

    public Integer getKnowledgeid() {
        return knowledgeid;
    }

    public void setKnowledgeid(Integer knowledgeid) {
        this.knowledgeid = knowledgeid;
    }

    public String getNodestatus() {
        return nodestatus;
    }

    public void setNodestatus(String nodestatus) {
        this.nodestatus = nodestatus == null ? null : nodestatus.trim();
    }

    public String getNodea() {
        return nodea;
    }

    public void setNodea(String nodea) {
        this.nodea = nodea == null ? null : nodea.trim();
    }

    public String getNodeb() {
        return nodeb;
    }

    public void setNodeb(String nodeb) {
        this.nodeb = nodeb == null ? null : nodeb.trim();
    }

    public String getNodec() {
        return nodec;
    }

    public void setNodec(String nodec) {
        this.nodec = nodec == null ? null : nodec.trim();
    }
}