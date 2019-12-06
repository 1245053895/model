package com.xd.zt.util.dataManager;

import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.parser.Feature;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.xd.zt.util.data.JsonKeyToStringList;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;


public class GetMysqlData {
    public static String JsonToCsv(String json) throws JSONException {
        try {
            JsonParser parser = new JsonParser();
            JsonArray GsonjsonArray = parser.parse(json).getAsJsonArray();
            GsonjsonArray.iterator().forEachRemaining(System.out::println);
            String[] GsonString = new String[GsonjsonArray.size()];
            for (int i = 0 ; i < GsonjsonArray.size() ; i++){
                GsonString[i] = GsonjsonArray.get(i).toString();
            }
            JSONArray jsonArray = new JSONArray();
            for (int j = 0 ; j < GsonString.length ; j++) {
                LinkedHashMap<String, Object> jsonMap = JSON.parseObject(GsonString[j],LinkedHashMap.class, Feature.OrderedField);
                com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject(true);
                jsonObject.putAll(jsonMap);
                String[] Keys = JsonKeyToStringList.translateKey(jsonObject);

                JsonElement GsonElement = GsonjsonArray.get(j);
                JsonObject GsonObject = GsonElement.getAsJsonObject();

                JSONObject jsonObj = new JSONObject(new LinkedHashMap());

                for (int k = 0 ; k < Keys.length ; k++){
                    if (GsonObject.get(Keys[k]) != null)
                    jsonObj.put(Keys[k],GsonObject.get(Keys[k]));
                }

                jsonArray.put(jsonObj);
            }

            String csv = CDL.toString(jsonArray);
            return csv;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}


