package com.xd.zt.controller.dataManager;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xd.zt.domain.dataManage.MysqlData;
import com.xd.zt.service.dataManager.OpenTsdbDataService;
import com.xd.zt.util.analyse.HttpClientGetWithHeader;
import com.xd.zt.util.dataManager.GetMysqlData;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/mysql")
@Controller
public class MysqlController {
    @Autowired
    private OpenTsdbDataService openTsdbDataService;
    //获取整表
    @ResponseBody
    @RequestMapping("/getTableData")
    public Map<String,Object> getTableData(@RequestBody String json) throws IOException {
        Map map = new HashMap();
        try {
            JSONObject jsonObject = JSON.parseObject(json);
            String dataClass = jsonObject.getString("dataClass");
            String tableName = jsonObject.getString("tableName");
            String fileName = jsonObject.getString("fileName");

            String[] HeaderKey = new String[]{"authorization"};
            String[] HeaderValue = new String[]{"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjoidXNlciIsImlhdCI6MTU3Mzk3NzM4NSwiZXhwIjoxNjA5OTc3Mzg1fQ.gYci1A8DuQjsRxs8T96EIolqMWb9fsxoyDS2G_kCJf8"};
            String url = "http://10.101.201.171:9543/mysql/getData?dataClass="+dataClass+"&tableName="+tableName;
            String Data = HttpClientGetWithHeader.restGetWithHeader(url,HeaderKey,HeaderValue);
            System.out.printf("\n\n"+Data);

            String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();

            String csv = GetMysqlData.JsonToCsv(Data);
            System.out.printf(csv);

            FileUtils.writeStringToFile(new File("/var/data/celery/input/dataManager/"+uuid+".csv"), csv);

            MysqlData mysqlData = new MysqlData();

            mysqlData.setMysqldataname(fileName+".csv");
            mysqlData.setMysqldatapath("/var/data/celery/input/dataManager/"+uuid+".csv");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            /* System.out.println(df.format(new Date()));*/// new Date()为获取当前系统时间
            String fileTime = df.format(new Date());
            mysqlData.setMysqldatatime(fileTime);

            openTsdbDataService.insertMysqlData(mysqlData);
            map.put("resp_msg","接入成功");
        }
        catch (Exception e){
            e.printStackTrace();
            map.put("resp_msg",e.toString());
        }
        return map;
    }

    //获取表名
    @ResponseBody
    @RequestMapping("/getTables")
    public Map<String,Object> getTables(@RequestBody String json) throws IOException {
        Map map = new HashMap();
        try {
            JSONObject jsonObject = JSON.parseObject(json);
            String dataClass = jsonObject.getString("dataClass");

            String[] HeaderKey = new String[]{"authorization"};
            String[] HeaderValue = new String[]{"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjoidXNlciIsImlhdCI6MTU3Mzk3NzM4NSwiZXhwIjoxNjA5OTc3Mzg1fQ.gYci1A8DuQjsRxs8T96EIolqMWb9fsxoyDS2G_kCJf8"};
            String url = "http://10.101.201.171:9543/mysql/tables?dataClass="+dataClass;
            String MysqlData = HttpClientGetWithHeader.restGetWithHeader(url,HeaderKey,HeaderValue);
            System.out.printf("\n\n"+MysqlData);
            try{
                JSONObject MysqlJson = JSON.parseObject(MysqlData);
                Integer status = MysqlJson.getInteger("status");
                map.put("resp_code",500);
                map.put("resp_msg",MysqlJson.getString("message"));
            }
            catch (Exception e){
                map.put("resp_code",0);
                map.put("resp_msg",MysqlData);
            }

        }
        catch (Exception e){
            e.printStackTrace();
            map.put("resp_msg",e.toString());
        }
        return map;
    }

}
