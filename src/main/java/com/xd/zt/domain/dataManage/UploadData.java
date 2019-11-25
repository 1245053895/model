package com.xd.zt.domain.dataManage;


import java.util.Date;

public class UploadData {
    Integer PointID;
    String DeviceNumber;
    String DeviceType;
    String PointName;
    Float CurrentValue;
    String Unit;
    Date ChangeTime;

    public Date getChangeTime() {
        return ChangeTime;
    }

    public void setChangeTime(Date changeTime) {
        ChangeTime = changeTime;
    }

    public Integer getPointID() {
        return PointID;
    }

    public void setPointID(Integer pointID) {
        PointID = pointID;
    }

    public String getDeviceNumber() {
        return DeviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        DeviceNumber = deviceNumber;
    }

    public String getDeviceType() {
        return DeviceType;
    }

    public void setDeviceType(String deviceType) {
        DeviceType = deviceType;
    }

    public String getPointName() {
        return PointName;
    }

    public void setPointName(String pointName) {
        PointName = pointName;
    }

    public Float getCurrentValue() {
        return CurrentValue;
    }

    public void setCurrentValue(Float currentValue) {
        CurrentValue = currentValue;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

}
