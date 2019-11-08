package com.xd.zt.domain.bean;


import lombok.Data;

import java.io.Serializable;


@Data
public class User implements Serializable
{
    /**
     * 序列号
     */
    private static final long serialVersionUID = 4074856906510638095L;

    /**
     * 身份证号码
     */
    private String idNumber;

    /**
     * 帐号
     */
    private String userName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
