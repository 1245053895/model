package com.xd.zt.util.dataManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xd.zt.domain.dataManage.UploadData;
import com.xd.zt.util.analyse.HttpClientGetWithHeader;
import com.xd.zt.util.data.JsonKeyToStringList;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.*;
@Component
public class GetOpenTsdb {

    public static List<UploadData> getOpenTsdbDate(String pointName,Integer startTime,Integer endTime ){
       List<UploadData> uploadDataList = new ArrayList<>();
       try{
           String[] HeaderKey = new String[]{"authorization"};
           String[] HeaderValue = new String[]{"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjoidXNlciIsImlhdCI6MTU3Mzk3NzM4NSwiZXhwIjoxNjA5OTc3Mzg1fQ.gYci1A8DuQjsRxs8T96EIolqMWb9fsxoyDS2G_kCJf8"};
           String url = "http://10.101.201.171:9543/tsdb/getData?endTime="+endTime.toString()+"&pointName="+pointName+"&startTime="+startTime.toString();
           String TsdbData = HttpClientGetWithHeader.restGetWithHeader(url,HeaderKey,HeaderValue);
           System.out.printf("\n\n"+TsdbData);
           JSONArray DataArray = JSON.parseArray(TsdbData);
           for (int i = 0 ; i < DataArray.size(); i++) {
               JSONObject dataJson = DataArray.getJSONObject(i);

               //设置参数
               String PointName = dataJson.getJSONObject("tags").getString("PointName");
               String DeviceType = dataJson.getJSONArray("aggregateTags").getString(0);
               String unit = dataJson.getJSONArray("aggregateTags").getString(2);

               String[] changeTimeList = JsonKeyToStringList.translateKey(dataJson.getJSONObject("dps"));
               for (int j = 0 ; j < changeTimeList.length; j++) {
                   UploadData uploadData = new UploadData();

                   Calendar c = Calendar.getInstance();
                   int seconds = Integer.parseInt(changeTimeList[j]) ;//数据库中提取的数据
                   long millions = new Long(seconds).longValue() * 1000;
                   c.setTimeInMillis(millions);
                   System.out.println("" + c.getTime());
                   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                   String dateString = sdf.format(c.getTime());
                   System.out.println(dateString);
                   Date ChangeTime = sdf.parse(dateString);


                   Float CurrentValue = dataJson.getJSONObject("dps").getFloat(changeTimeList[j]);

                   uploadData.setChangeTime(ChangeTime);
                   uploadData.setCurrentValue(CurrentValue);
                   uploadData.setDeviceType(DeviceType);
                   uploadData.setPointName(PointName);
                   uploadData.setUnit(unit);

                   uploadDataList.add(uploadData);
               }
           }
       }
        catch (Exception e){
           e.printStackTrace();
        }
        return uploadDataList;
   }
}
