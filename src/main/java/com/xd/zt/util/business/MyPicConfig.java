package com.xd.zt.util.business;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//新增加一个类用来添加虚拟路径映射
@Configuration
public class MyPicConfig implements WebMvcConfigurer {
   @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
       String a=System.getProperty("user.dir");
        String b = a.replaceAll("\\\\","/");
      registry.addResourceHandler("/uploadImage/**").addResourceLocations("file:"+b+"/src/main/resources/static/uploadImage/");
 }
}
