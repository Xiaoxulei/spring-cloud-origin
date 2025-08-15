package com.xuxiaolei.security.config;

import com.xuxiaolei.security.utils.JwtUtils;
import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: xuxiaolei
 * @Description: TODO:
 * @CreatTime: 2025/08/15 16:48
 **/
@Configuration
public class FeignClientConfig {
    @Autowired
    private JwtUtils jwtUtils;

    // Feign 请求拦截器，每次调用都会加上 JWT
    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            // 假设当前服务有一个固定用户，真实场景可从 SecurityContext 获取
            String token = jwtUtils.generateToken("feignUser");
            template.header("Authorization", "Bearer " + token);
        };
    }
    /*
    * 开启feign日志
    */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
