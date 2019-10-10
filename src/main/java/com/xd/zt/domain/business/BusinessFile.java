package com.xd.zt.domain.business;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity   //该注解自动将实体类生成表
public class BusinessFile {
    @Id//主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增策略
    private Integer dataid;

    private String filename;

    private String filepath;

    private String filetime;

    private String filesize;

    private Integer businessid;

    private String fliea;

    private String flieb;

    private String filec;

    public Integer getDataid() {
        return dataid;
    }

    public void setDataid(Integer dataid) {
        this.dataid = dataid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath == null ? null : filepath.trim();
    }

    public String getFiletime() {
        return filetime;
    }

    public void setFiletime(String filetime) {
        this.filetime = filetime;
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    public Integer getBusinessid() {
        return businessid;
    }

    public void setBusinessid(Integer businessid) {
        this.businessid = businessid;
    }

    public String getFliea() {
        return fliea;
    }

    public void setFliea(String fliea) {
        this.fliea = fliea == null ? null : fliea.trim();
    }

    public String getFlieb() {
        return flieb;
    }

    public void setFlieb(String flieb) {
        this.flieb = flieb == null ? null : flieb.trim();
    }

    public String getFilec() {
        return filec;
    }

    public void setFilec(String filec) {
        this.filec = filec == null ? null : filec.trim();
    }
}