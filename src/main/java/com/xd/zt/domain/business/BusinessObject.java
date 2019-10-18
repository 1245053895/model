package com.xd.zt.domain.business;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class BusinessObject {
    @Id//主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增策略
    private Integer objectid;

    private String objectname;

    private String objecttype;

    private String objectattribute;

    private String objectdescribe;

    private String objecta;

    private String objectb;

    private String objectc;

    public Integer getObjectid() {
        return objectid;
    }

    public void setObjectid(Integer objectid) {
        this.objectid = objectid;
    }

    public String getObjectname() {
        return objectname;
    }

    public void setObjectname(String objectname) {
        this.objectname = objectname == null ? null : objectname.trim();
    }

    public String getObjecttype() {
        return objecttype;
    }

    public void setObjecttype(String objecttype) {
        this.objecttype = objecttype == null ? null : objecttype.trim();
    }

    public String getObjectattribute() {
        return objectattribute;
    }

    public void setObjectattribute(String objectattribute) {
        this.objectattribute = objectattribute == null ? null : objectattribute.trim();
    }

    public String getObjectdescribe() {
        return objectdescribe;
    }

    public void setObjectdescribe(String objectdescribe) {
        this.objectdescribe = objectdescribe == null ? null : objectdescribe.trim();
    }

    public String getObjecta() {
        return objecta;
    }

    public void setObjecta(String objecta) {
        this.objecta = objecta == null ? null : objecta.trim();
    }

    public String getObjectb() {
        return objectb;
    }

    public void setObjectb(String objectb) {
        this.objectb = objectb == null ? null : objectb.trim();
    }

    public String getObjectc() {
        return objectc;
    }

    public void setObjectc(String objectc) {
        this.objectc = objectc == null ? null : objectc.trim();
    }
}