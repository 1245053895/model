package com.xd.zt.domain.business;

public class BusinessKnowledge {
    private Integer knowledgeid;

    private String knowledgename;

    private String knowledgedescribe;

    private String knowledgea;

    private String knowledgeb;

    private String knowledgeac;

    public Integer getKnowledgeid() {
        return knowledgeid;
    }

    public void setKnowledgeid(Integer knowledgeid) {
        this.knowledgeid = knowledgeid;
    }

    public String getKnowledgename() {
        return knowledgename;
    }

    public void setKnowledgename(String knowledgename) {
        this.knowledgename = knowledgename == null ? null : knowledgename.trim();
    }

    public String getKnowledgedescribe() {
        return knowledgedescribe;
    }

    public void setKnowledgedescribe(String knowledgedescribe) {
        this.knowledgedescribe = knowledgedescribe == null ? null : knowledgedescribe.trim();
    }

    public String getKnowledgea() {
        return knowledgea;
    }

    public void setKnowledgea(String knowledgea) {
        this.knowledgea = knowledgea == null ? null : knowledgea.trim();
    }

    public String getKnowledgeb() {
        return knowledgeb;
    }

    public void setKnowledgeb(String knowledgeb) {
        this.knowledgeb = knowledgeb == null ? null : knowledgeb.trim();
    }

    public String getKnowledgeac() {
        return knowledgeac;
    }

    public void setKnowledgeac(String knowledgeac) {
        this.knowledgeac = knowledgeac == null ? null : knowledgeac.trim();
    }
}