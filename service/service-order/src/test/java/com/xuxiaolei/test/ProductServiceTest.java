package com.xuxiaolei.test;

import com.xuxiaolei.config.RestTemplateConfig;
import com.xuxiaolei.feign.ProductFeignClient;
import com.xuxiaolei.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * @Author: xuxiaolei
 * @Description: TODO:
 * @CreatTime: 2025/08/15 10:55
 **/
@SpringBootTest
/*@TestPropertySource(properties = {
        "logging.level.com.xuxiaolei.feign=DEBUG",
})*/
public class ProductServiceTest {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ProductService productService;
    @Autowired
    private DiscoveryClient discoveryClient;

    @Test
    public void RestTemplateTest01(){
        String test = productService.getTest2();
        System.out.println("test = " + test);

    }

    @Test
    public void feignClientTest(){
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        String uri = instances.get(0).getUri().toString() + "/test";
        System.out.println("uri = " + uri);
        String forObject = restTemplate.getForObject(uri, String.class);
        System.out.println("forObject = " + forObject);
    }


}
