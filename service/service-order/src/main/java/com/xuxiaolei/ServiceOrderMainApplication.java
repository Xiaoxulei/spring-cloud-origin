package com.xuxiaolei;

import com.xuxiaolei.common.utils.ServiceJwtUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * @Author: xuxiaolei
 * @Description: TODO: ${description}
 * @CreatTime: 2025/08/15 10:29
 **/
@SpringBootApplication
@EnableFeignClients
public class ServiceOrderMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOrderMainApplication.class,args);
    }
    @Bean
    public ServiceJwtUtils serviceJwtUtils() {
        return new ServiceJwtUtils();
    }
}