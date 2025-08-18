package com.xuxiaolei.product.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xuxiaolei
 * @Description: TODO:
 * @CreatTime: 2025/08/14 16:41
 **/
@RestController
@RefreshScope
@RequestMapping("/product")
public class TestController {

    @Value("${product.test:good}")
    private String test;

    @GetMapping("/test")
    public String test(){
        System.out.println("test = " + test);
        return test;
    }
}
