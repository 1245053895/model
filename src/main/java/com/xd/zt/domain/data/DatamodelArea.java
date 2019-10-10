package com.xd.zt.domain.data;

public class DatamodelArea {
    private Integer areaid;

    private String areaname;

    private String arealogic;

    private String areachuli;

    private String areapicture;

    private Integer linkid;

    private Integer processid;

    private String name;

    private Integer modeid;

    private String linkname;

    private String dataresultname;

    private String dataresultid;

    public String getDataresultid() {
        return dataresultid;
    }

    public void setDataresultid(String dataresultid) {
        this.dataresultid = dataresultid;
    }

    public String getDataresultname() {
        return dataresultname;
    }

    public void setDataresultname(String dataresultname) {
        this.dataresultname = dataresultname;
    }

    public String getLinkname() {
        return linkname;
    }

    public void setLinkname(String linkname) {
        this.linkname = linkname;
    }


    public Integer getModeid() {
        return modeid;
    }

    public void setModeid(Integer modeid) {
        this.modeid = modeid;
    }

    public Integer getProcessid() {
        return processid;
    }

    public void setProcessid(Integer processid) {
        this.processid = processid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAreaid() {
        return areaid;
    }

    public void setAreaid(Integer areaid) {
        this.areaid = areaid;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname == null ? null : areaname.trim();
    }

    public String getArealogic() {
        return arealogic;
    }

    public void setArealogic(String arealogic) {
        this.arealogic = arealogic == null ? null : arealogic.trim();
    }

    public String getAreachuli() {
        return areachuli;
    }

    public void setAreachuli(String areachuli) {
        this.areachuli = areachuli == null ? null : areachuli.trim();
    }

    public String getAreapicture() {
        return areapicture;
    }

    public void setAreapicture(String areapicture) {
        this.areapicture = areapicture == null ? null : areapicture.trim();
    }

    public Integer getLinkid() {
        return linkid;
    }

    public void setLinkid(Integer linkid) {
        this.linkid = linkid;
    }
}