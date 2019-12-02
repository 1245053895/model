package com.xd.zt.util.dataManager;

import com.alibaba.fastjson.JSONObject;
import com.xd.zt.domain.ApiResult;
import com.xd.zt.util.analyse.HttpClientGetWithHeader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class GetHdfsFile {
    public static ApiResult getFile(String hdfspath) {
        ApiResult apiResult = new ApiResult();
        try{
            String[] HeaderKey = new String[]{"authorization"};
            String[] HeaderValue = new String[]{"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjoidXNlciIsImlhdCI6MTU3Mzk3NzM4NSwiZXhwIjoxNjA5OTc3Mzg1fQ.gYci1A8DuQjsRxs8T96EIolqMWb9fsxoyDS2G_kCJf8"};
            String url = "http://10.101.201.171:9543/hadoop/download?path="+hdfspath;
            String type = hdfspath.split("\\.")[1];
            String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
            String targetPath = "/var/data/celery/input/Hdfs/"+uuid+"."+type;
//            String targetPath = "F://"+uuid+"."+type;

            CloseableHttpClient httpClient = HttpClients.createDefault();
                // 这里我设置了超时时间的配置，也可以不设
                RequestConfig timeoutConfig = RequestConfig.custom()
                        .setConnectTimeout(5000).setConnectionRequestTimeout(1000)
                        .setSocketTimeout(5000).build();
                // download_url为下载文件接口地址，fileId是我自己文件接口定义的文件标识
                // 本例的文件下载接口是直接返回的文件流
                HttpGet httpGet = new HttpGet(url);
                httpGet.setConfig(timeoutConfig);
                httpGet.addHeader(HeaderKey[0],HeaderValue[0]);
                HttpResponse downLoadResponse = httpClient.execute(httpGet);
                StatusLine statusLine = downLoadResponse.getStatusLine();
                // 响应码
                int statusCode = statusLine.getStatusCode();
                // 请求成功
                if (statusCode == 200) {
                    // 获取接口返回的文件流
                    HttpEntity entity = downLoadResponse.getEntity();
                    InputStream input = entity.getContent();
                    // 本例是储存到本地文件系统，fileRealName为你想存的文件名称
                    File dest = new File(targetPath);
                    OutputStream output = new FileOutputStream(dest);
                    int len = 0;
                    byte[] ch = new byte[1024];
                      while ((len = input.read(ch)) != -1) {
                         output.write(ch, 0, len);
                         System.out.println(len);
                      }
                }

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("path",targetPath);
            apiResult.setResp_code(0);
            apiResult.setResp_msg("下载成功");
            apiResult.setDatas(jsonObject);

            if (httpGet != null) {
                httpGet.releaseConnection();
            }
        }
        catch (Exception e){
            apiResult.setResp_code(30);
            apiResult.setResp_msg("下载失败");
            apiResult.setDatas(e.toString());
        }
        return apiResult;
    }
}
