package com.xd.zt.domain.business;

public class BusinessQuestion {
    private Integer questionid;

    private String questioname;



    private Integer businessid;
    private String questiondes;

    private String formulatdes;

    private String picture;

    private Integer sceneid;

private Integer blockid;

    private String flowa;

    private String flowb;

    private String flowc;

    public Integer getBusinessid() {
        return businessid;
    }

    public void setBusinessid(Integer businessid) {
        this.businessid = businessid;
    }
    public Integer getQuestionid() {
        return questionid;
    }

    public void setQuestionid(Integer questionid) {
        this.questionid = questionid;
    }

    public String getQuestioname() {
        return questioname;
    }

    public void setQuestioname(String questioname) {
        this.questioname = questioname == null ? null : questioname.trim();
    }

    public String getQuestiondes() {
        return questiondes;
    }

    public void setQuestiondes(String questiondes) {
        this.questiondes = questiondes == null ? null : questiondes.trim();
    }

    public String getFormulatdes() {
        return formulatdes;
    }

    public void setFormulatdes(String formulatdes) {
        this.formulatdes = formulatdes == null ? null : formulatdes.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public Integer getSceneid() {
        return sceneid;
    }

    public void setSceneid(Integer sceneid) {
        this.sceneid = sceneid;
    }


    public Integer getBlockid() {
        return blockid;
    }

    public void setBlockid(Integer blockid) {
        this.blockid = blockid;
    }

    public String getFlowa() {
        return flowa;
    }

    public void setFlowa(String flowa) {
        this.flowa = flowa == null ? null : flowa.trim();
    }

    public String getFlowb() {
        return flowb;
    }

    public void setFlowb(String flowb) {
        this.flowb = flowb == null ? null : flowb.trim();
    }

    public String getFlowc() {
        return flowc;
    }

    public void setFlowc(String flowc) {
        this.flowc = flowc == null ? null : flowc.trim();
    }
}