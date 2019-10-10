package com.xd.zt.domain.business;

public class BusinessScene {
    private Integer sceneid;

    private String scenename;



    private Integer businessid;

    private Integer blockprocessid;

    private String blockid;

    private Integer knowledgeid;

    private Integer dataid;

    private Integer objectid;

    private String scenea;

    private String sceneb;

    private String scenec;

    private String blockContent;

    public String getBlockid() {
        return blockid;
    }

    public void setBlockid(String blockid) {
        this.blockid = blockid;
    }

    public Integer getBusinessid() {
        return businessid;
    }

    public void setBusinessid(Integer businessid) {
        this.businessid = businessid;
    }

    public Integer getSceneid() {
        return sceneid;
    }
    public String getBlockContent() {
        return blockContent;
    }

    public void setBlockContent(String blockContent) {
        this.blockContent = blockContent;
    }
    public void setSceneid(Integer sceneid) {
        this.sceneid = sceneid;
    }

    public String getScenename() {
        return scenename;
    }

    public void setScenename(String scenename) {
        this.scenename = scenename == null ? null : scenename.trim();
    }

    public Integer getBlockprocessid() {
        return blockprocessid;
    }

    public void setBlockprocessid(Integer blockprocessid) {
        this.blockprocessid = blockprocessid;
    }



    public Integer getKnowledgeid() {
        return knowledgeid;
    }

    public void setKnowledgeid(Integer knowledgeid) {
        this.knowledgeid = knowledgeid;
    }

    public Integer getDataid() {
        return dataid;
    }

    public void setDataid(Integer dataid) {
        this.dataid = dataid;
    }

    public Integer getObjectid() {
        return objectid;
    }

    public void setObjectid(Integer objectid) {
        this.objectid = objectid;
    }

    public String getScenea() {
        return scenea;
    }

    public void setScenea(String scenea) {
        this.scenea = scenea == null ? null : scenea.trim();
    }

    public String getSceneb() {
        return sceneb;
    }

    public void setSceneb(String sceneb) {
        this.sceneb = sceneb == null ? null : sceneb.trim();
    }

    public String getScenec() {
        return scenec;
    }

    public void setScenec(String scenec) {
        this.scenec = scenec == null ? null : scenec.trim();
    }
}