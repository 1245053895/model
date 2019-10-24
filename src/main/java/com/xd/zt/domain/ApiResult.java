package com.xd.zt.domain;

public class ApiResult {
    private Integer code;
    private String messange;
    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessange() {
        return messange;
    }

    public void setMessange(String messange) {
        this.messange = messange;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
