package com.xd.zt.domain.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity   //该注解自动将实体类生成表
public class DatamodelSource {
    @Id//主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增策略
    private Integer sourceid;
    private String sourcename;
    private String sourcetime;
    private String sourcepath;
    private String sourcesize;
    private String status;
    private String linksource;
private Integer modeid;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLinksource() {
        return linksource;
    }

    public void setLinksource(String linksource) {
        this.linksource = linksource;
    }

    public Integer getModeid() {
        return modeid;
    }

    public void setModeid(Integer modeid) {
        this.modeid = modeid;
    }

    public Integer getSourceid() {
        return sourceid;
    }

    public void setSourceid(Integer sourceid) {
        this.sourceid = sourceid;
    }

    public String getSourcename() {
        return sourcename;
    }

    public void setSourcename(String sourcename) {
        this.sourcename = sourcename;
    }

    public String getSourcetime() {
        return sourcetime;
    }

    public void setSourcetime(String sourcetime) {
        this.sourcetime = sourcetime;
    }

    public String getSourcepath() {
        return sourcepath;
    }

    public void setSourcepath(String sourcepath) {
        this.sourcepath = sourcepath;
    }

    public String getSourcesize() {
        return sourcesize;
    }

    public void setSourcesize(String sourcesize) {
        this.sourcesize = sourcesize;
    }
}
