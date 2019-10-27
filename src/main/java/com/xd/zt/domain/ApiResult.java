package com.xd.zt.domain;

public class ApiResult {
    private Integer resp_code;
    private String resp_msg;
    private Object datas;

    public Integer getResp_code() {
        return resp_code;
    }

    public void setResp_code(Integer resp_code) {
        this.resp_code = resp_code;
    }

    public String getResp_msg() {
        return resp_msg;
    }

    public void setResp_msg(String resp_msg) {
        this.resp_msg = resp_msg;
    }

    public Object getDatas() {
        return datas;
    }

    public void setDatas(Object datas) {
        this.datas = datas;
    }
}
