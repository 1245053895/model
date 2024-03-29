package com.xd.zt.util.analyse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class HttpCientPost {
    public static String restPost(String url ,String json) throws IOException {
        JSONObject jsonObject = JSON.parseObject(json);
        // 创建一个可关闭的http客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建post请求
        HttpPost post = new HttpPost(url);
        /**
         * 添加json数据
         */
        post.addHeader("Content-type", "application/json;charset-utf-8");
        post.setEntity(new StringEntity(String.valueOf(jsonObject), Charset.forName("UTF-8")));
        post.setConfig(getConfig());
        // 执行请求
        HttpResponse response = httpClient.execute(post);
        //得到并返回数据
        String Result = EntityUtils.toString(response.getEntity());

        return Result;
    }
    public static RequestConfig getConfig(){
       RequestConfig config = RequestConfig.custom().setSocketTimeout(600000).setConnectTimeout(300000).build();
       return config;
    }
}
