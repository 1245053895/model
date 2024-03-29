package com.xd.zt.domain.business;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class BusinessType {
    @Id//主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增策略
    private Integer dataid;

    private String datatypename;

    private String typea;

    private String typeb;

    private String typec;

    public Integer getDataid() {
        return dataid;
    }

    public void setDataid(Integer dataid) {
        this.dataid = dataid;
    }

    public String getDatatypename() {
        return datatypename;
    }

    public void setDatatypename(String datatypename) {
        this.datatypename = datatypename == null ? null : datatypename.trim();
    }

    public String getTypea() {
        return typea;
    }

    public void setTypea(String typea) {
        this.typea = typea == null ? null : typea.trim();
    }

    public String getTypeb() {
        return typeb;
    }

    public void setTypeb(String typeb) {
        this.typeb = typeb == null ? null : typeb.trim();
    }

    public String getTypec() {
        return typec;
    }

    public void setTypec(String typec) {
        this.typec = typec == null ? null : typec.trim();
    }
}