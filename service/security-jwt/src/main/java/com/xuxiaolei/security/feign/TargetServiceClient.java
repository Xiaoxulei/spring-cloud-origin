package com.xuxiaolei.security.feign;

import com.xuxiaolei.security.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

// Feign 接口
@FeignClient(name = "service-product", configuration = FeignClientConfig.class)
public interface TargetServiceClient {

    @GetMapping("/test")
    String test();
}
