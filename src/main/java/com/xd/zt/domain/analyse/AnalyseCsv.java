package com.xd.zt.domain.analyse;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity   //该注解自动将实体类生成表
public class AnalyseCsv {
    @Id//主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增策略
    Integer id;
    String csvname;
    String csvpath;
    Integer modelinstanceid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCsvname() {
        return csvname;
    }

    public void setCsvname(String csvname) {
        this.csvname = csvname;
    }

    public String getCsvpath() {
        return csvpath;
    }

    public void setCsvpath(String csvpath) {
        this.csvpath = csvpath;
    }

    public Integer getModelinstanceid() {
        return modelinstanceid;
    }

    public void setModelinstanceid(Integer modelinstanceid) {
        this.modelinstanceid = modelinstanceid;
    }
}
