package com.xd.zt.domain.business;




import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BusinessModel {
    @Id//主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增策略
    private Integer businessid;
    private String businessname;
    private String businessmaterial;
    private String businessline;
    private String businessscene;
    private String businessobject;
    private Integer processid;
    private Integer programmeid;

    public Integer getProgrammeid() {
        return programmeid;
    }

    public void setProgrammeid(Integer programmeid) {
        this.programmeid = programmeid;
    }

    public Integer getProcessid() {
        return processid;
    }

    public void setProcessid(Integer processid) {
        this.processid = processid;
    }

    public Integer getBusinessid() {
        return businessid;
    }

    public void setBusinessid(Integer businessid) {
        this.businessid = businessid;
    }

    public String getBusinessname() {
        return businessname;
    }

    public void setBusinessname(String businessname) {
        this.businessname = businessname;
    }

    public String getBusinessmaterial() {
        return businessmaterial;
    }

    public void setBusinessmaterial(String businessmaterial) {
        this.businessmaterial = businessmaterial;
    }

    public String getBusinessline() {
        return businessline;
    }

    public void setBusinessline(String businessline) {
        this.businessline = businessline;
    }

    public String getBusinessscene() {
        return businessscene;
    }

    public void setBusinessscene(String businessscene) {
        this.businessscene = businessscene;
    }

    public String getBusinessobject() {
        return businessobject;
    }

    public void setBusinessobject(String businessobject) {
        this.businessobject = businessobject;
    }
}
