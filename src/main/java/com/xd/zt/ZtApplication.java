package com.xd.zt;

import com.xd.zt.util.analyse.HttpClientGet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class ZtApplication {

    public static void main(String[] args) {

        SpringApplication.run(ZtApplication.class, args);


    }

}
