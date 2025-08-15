package com.xuxiaolei.controller;

import com.xuxiaolei.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xuxiaolei
 * @Description: TODO:
 * @CreatTime: 2025/08/15 11:46
 **/
@RestController
public class OrderController {
    @Autowired
    private ProductService productService;

    @GetMapping("/test")
    public String test(){
        return productService.getTest2();
    }
    @GetMapping("/test1")
    public String test1(){
        return productService.getTest();
    }
}
