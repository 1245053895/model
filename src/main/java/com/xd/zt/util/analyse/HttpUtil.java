package com.xd.zt.util.analyse;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class  HttpUtil {
    public static String post(String requestUrl, String json) {
        try{
            HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
            httpRequestFactory.setConnectionRequestTimeout(2000);
            httpRequestFactory.setConnectTimeout(2000);
            httpRequestFactory.setReadTimeout(2000);

            RestTemplate restTemplate = new RestTemplate(httpRequestFactory);

            String url = requestUrl;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            HttpEntity<String> request = new HttpEntity<>(json, headers);
            ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class );
//            System.out.println(response.getBody());
            if(response.getStatusCodeValue() != 200) {
                return null;
            }
            return response.getBody();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
}
