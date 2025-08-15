package com.xuxiaolei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: xuxiaolei
 * @Description: TODO: ${description}
 * @CreatTime: 2025/08/15 16:44
 **/
@SpringBootApplication
@EnableFeignClients
public class SecurityJwtMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityJwtMainApplication.class, args);
    }
}