package com.xd.zt.util.analyse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class HttpCientPostWithHeader {
    public static String restPost(String url ,String Content,String[] HeaderName,String[] HeaderValue) throws IOException {
//        JSONObject jsonObject = JSON.parseObject(json);
        // 创建一个可关闭的http客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建post请求
        HttpPost post = new HttpPost(url);
        /**
         * 添加json数据
         */
        for (int i = 0; i < HeaderName.length; i++){
            post.addHeader(HeaderName[i],HeaderValue[i]);
        }
        post.setEntity(new StringEntity(Content, Charset.forName("UTF-8")));
        // 执行请求
        HttpResponse response = httpClient.execute(post);
        //得到并返回数据
        String Result = EntityUtils.toString(response.getEntity());

        return Result;
    }

}
