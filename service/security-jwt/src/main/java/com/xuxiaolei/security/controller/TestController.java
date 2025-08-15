package com.xuxiaolei.security.controller;

import com.xuxiaolei.security.feign.TargetServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TargetServiceClient targetServiceClient;

    @GetMapping("/call")
    public String call() {
        // 这里调用另一个服务，Feign 自动带 JWT
        return targetServiceClient.test();
    }
}
