package com.xuxiaolei;

import com.xuxiaolei.common.utils.ServiceJwtUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

/**
 * @Author: xuxiaolei
 * @Description: TODO: ${description}
 * @CreatTime: 2025/08/18 11:14
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayMainApplication.class, args);
    }
    @Bean
    public ServiceJwtUtils serviceJwtUtils() {
        return new ServiceJwtUtils();
    }
}