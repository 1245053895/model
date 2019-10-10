package com.xd.zt.domain.data;

public class DatamodelInfo {

    private Integer dataresultid;

    private String dataresultname;

    private String dataaddr;

    private String datalink;

    private String dataarea;

    private String datablock;

    private String databao;

    private String dataji;

    private Integer modelid;


    public Integer getDataresultid() {
        return dataresultid;
    }

    public void setDataresultid(Integer dataresultid) {
        this.dataresultid = dataresultid;
    }

    public String getDataresultname() {
        return dataresultname;
    }

    public void setDataresultname(String dataresultname) {
        this.dataresultname = dataresultname;
    }

    public String getDataaddr() {
        return dataaddr;
    }

    public void setDataaddr(String dataaddr) {
        this.dataaddr = dataaddr;
    }



    public String getDatalink() {
        return datalink;
    }

    public void setDatalink(String datalink) {
        this.datalink = datalink == null ? null : datalink.trim();
    }

    public String getDataarea() {
        return dataarea;
    }

    public void setDataarea(String dataarea) {
        this.dataarea = dataarea == null ? null : dataarea.trim();
    }

    public String getDatablock() {
        return datablock;
    }

    public void setDatablock(String datablock) {
        this.datablock = datablock == null ? null : datablock.trim();
    }

    public String getDatabao() {
        return databao;
    }

    public void setDatabao(String databao) {
        this.databao = databao == null ? null : databao.trim();
    }

    public String getDataji() {
        return dataji;
    }

    public void setDataji(String dataji) {
        this.dataji = dataji == null ? null : dataji.trim();
    }

    public Integer getModelid() {
        return modelid;
    }

    public void setModelid(Integer modelid) {
        this.modelid = modelid;
    }
}