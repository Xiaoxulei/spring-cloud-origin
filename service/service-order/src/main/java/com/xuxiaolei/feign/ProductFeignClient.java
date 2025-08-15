package com.xuxiaolei.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: xuxiaolei
 * @Description: TODO:
 * @CreatTime: 2025/08/15 11:16
 **/
@FeignClient("service-product")
public interface ProductFeignClient {
    @GetMapping("/test")
    String test();
}
