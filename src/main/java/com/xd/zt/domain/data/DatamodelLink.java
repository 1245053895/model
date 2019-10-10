package com.xd.zt.domain.data;

public class DatamodelLink {
    private Integer linkid;

    private String linkname;

    private String linkpicture;

    private Integer modeid;
     private Integer processid;

private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProcessid() {
        return processid;
    }

    public void setProcessid(Integer processid) {
        this.processid = processid;
    }

    public Integer getLinkid() {
        return linkid;
    }

    public void setLinkid(Integer linkid) {
        this.linkid = linkid;
    }

    public String getLinkname() {
        return linkname;
    }

    public void setLinkname(String linkname) {
        this.linkname = linkname == null ? null : linkname.trim();
    }

    public String getLinkpicture() {
        return linkpicture;
    }

    public void setLinkpicture(String linkpicture) {
        this.linkpicture = linkpicture == null ? null : linkpicture.trim();
    }

    public Integer getModeid() {
        return modeid;
    }

    public void setModeid(Integer modeid) {
        this.modeid = modeid;
    }
}