package com.xd.zt.util.data;

import com.alibaba.fastjson.JSONObject;

public class JsonKeyToStringList {
    public static String[] translate(JSONObject jsonObject) {
        String[] StringList = new String[jsonObject.size()];
        String jsonString = jsonObject.toJSONString();
        jsonString = jsonString.replace("{","");
        jsonString = jsonString.replace("}","");
        String[] keyAndValue = jsonString.split(",");
        for (int i = 0 ; i < keyAndValue.length; i++){
            String[] keys = keyAndValue[i].split(":");
            String key = keys[1].replace("\"","");
            StringList[i] = key;
        }
        return StringList;
    }
}
