package com.xuxiaolei.service;

import com.xuxiaolei.feign.ProductFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: xuxiaolei
 * @Description: TODO:
 * @CreatTime: 2025/08/15 10:51
 **/
@Service
public class ProductService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductFeignClient productFeignClient;
    /*
     * 调用 product-service
     */

    public String getTest(){
    // 微服务 URL，假设使用服务名 + Nacos 注册发现
    String url = "http://service-product/test";
    // 调用微服务 GET 方法，返回 String 类型数据
    return restTemplate.getForObject(url,String.class);
    }

    public String getTest2(){
        return productFeignClient.test();
    }


}
