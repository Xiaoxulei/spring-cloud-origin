package com.xuxiaolei.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: xuxiaolei
 * @Description: TODO:
 * @CreatTime: 2025/08/15 10:49
 **/
@Configuration

public class RestTemplateConfig {
    /*
     * 创建 RestTemplate Bean
     * 可在需要调用其他微服务的地方注入使用
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
