package com.xd.zt.domain.analyse;

public class AnalyseModel {
    private Integer modelid;
    private String name;
    private Integer questionid;
    private String questioname;
    private Integer programmeid;

    public Integer getProgrammeid() {
        return programmeid;
    }

    public void setProgrammeid(Integer programmeid) {
        this.programmeid = programmeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuestioname() {
        return questioname;
    }

    public void setQuestioname(String questioname) {
        this.questioname = questioname;
    }



    public Integer getModelid() {
        return modelid;
    }

    public void setModelid(Integer modelid) {
        this.modelid = modelid;
    }

    public Integer getQuestionid() {
        return questionid;
    }

    public void setQuestionid(Integer questionid) {
        this.questionid = questionid;
    }
}
