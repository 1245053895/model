package com.xd.zt;

import com.xd.zt.util.analyse.HttpClientGet;
import com.xd.zt.util.dataManager.GetOpenTsdb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.config.BootstrapMode;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//将hibernate在一个单独的线程中运行
@EnableJpaRepositories(bootstrapMode = BootstrapMode.DEFERRED)
@EnableAsync
@EnableCaching
@EnableTransactionManagement
@ServletComponentScan
@SpringBootApplication
public class ZtApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZtApplication.class, args);
    }

}
