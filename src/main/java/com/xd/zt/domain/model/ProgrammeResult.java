package com.xd.zt.domain.model;

public class ProgrammeResult {
    Integer id;
    String programmeresultname;
    String programmeresultpath;
    String programmeresulttime;
    Integer programmeid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProgrammeresultname() {
        return programmeresultname;
    }

    public void setProgrammeresultname(String programmeresultname) {
        this.programmeresultname = programmeresultname;
    }

    public String getProgrammeresultpath() {
        return programmeresultpath;
    }

    public void setProgrammeresultpath(String programmeresultpath) {
        this.programmeresultpath = programmeresultpath;
    }

    public String getProgrammeresulttime() {
        return programmeresulttime;
    }

    public void setProgrammeresulttime(String programmeresulttime) {
        this.programmeresulttime = programmeresulttime;
    }

    public Integer getProgrammeid() {
        return programmeid;
    }

    public void setProgrammeid(Integer programmeid) {
        this.programmeid = programmeid;
    }
}
