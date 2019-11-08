package com.xd.zt.domain.analyse;

public class AnalyseResult {
    Integer resultid;
    Integer instanceid;
    String modelPathname;
    String modelPath;

    public Integer getResultid() {
        return resultid;
    }

    public void setResultid(Integer resultid) {
        this.resultid = resultid;
    }

    public Integer getInstanceid() {
        return instanceid;
    }

    public void setInstanceid(Integer instanceid) {
        this.instanceid = instanceid;
    }

    public String getModelPathname() {
        return modelPathname;
    }

    public void setModelPathname(String modelPathname) {
        this.modelPathname = modelPathname;
    }

    public String getModelPath() {
        return modelPath;
    }

    public void setModelPath(String modelPath) {
        this.modelPath = modelPath;
    }
}
