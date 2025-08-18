package com.xuxiaolei;

import com.xuxiaolei.common.utils.ServiceJwtUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @Author: xuxiaolei
 * @Description: TODO: ${description}
 * @CreatTime: 2025/08/14 15:29
 **/
@SpringBootApplication
public class ServiceProductMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceProductMainApplication.class, args);
    }
    @Bean
    public ServiceJwtUtils serviceJwtUtils() {
        return new ServiceJwtUtils();
    }
}