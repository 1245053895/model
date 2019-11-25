package com.xd.zt.util.dataManager;


import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;


public class GetMysqlData {
    public static String JsonToCsv(String json) throws JSONException {
        try {
            JSONArray jsonArray = new JSONArray(json);
            String csv = CDL.toString(jsonArray);
            return csv;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    }


