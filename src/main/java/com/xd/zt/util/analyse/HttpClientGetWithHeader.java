package com.xd.zt.util.analyse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class HttpClientGetWithHeader {
    public static String restGetWithHeader(String url,String[] HeaderKey ,String[] HeaderValue) {
        JSONObject jsonObject = new JSONObject();
        // 创建一个可关闭的http客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建get请求
        HttpGet get = new HttpGet(url);
        for (int i = 0 ; i < HeaderKey.length ; i++){
            get.addHeader(HeaderKey[i], HeaderValue[i]);
        }
        HttpResponse response = null;
        try {
            //执行请求
            response = httpClient.execute(get);
            //得到并返回数据
            String Result = EntityUtils.toString(response.getEntity(), String.valueOf(Charset.forName("UTF-8")));
            return Result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
