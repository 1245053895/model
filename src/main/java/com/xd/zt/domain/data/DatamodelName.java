package com.xd.zt.domain.data;



public class DatamodelName {
    private Integer modelid;

    private String modelname;

    private String modeltime;

    private Integer questionid;

    public String getModeltime() {
        return modeltime;
    }

    public Integer getQuestionid() {
        return questionid;
    }

    public void setQuestionid(Integer questionid) {
        this.questionid = questionid;
    }

    public void setModeltime(String modeltime) {
        this.modeltime = modeltime;
    }

    public Integer getModelid() {
        return modelid;
    }

    public void setModelid(Integer modelid) {
        this.modelid = modelid;
    }

    public String getModelname() {
        return modelname;
    }

    public void setModelname(String modelname) {
        this.modelname = modelname == null ? null : modelname.trim();
    }



    }