package com.xd.zt.domain.data;

public class DatamodelMenu {
    private Integer menuid;

    private String menuname;

    private String menuopen;

    public Integer getMenuid() {
        return menuid;
    }

    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname == null ? null : menuname.trim();
    }

    public String getMenuopen() {
        return menuopen;
    }

    public void setMenuopen(String menuopen) {
        this.menuopen = menuopen == null ? null : menuopen.trim();
    }
}