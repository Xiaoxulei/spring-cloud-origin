package com.xuxiaolei.Test;

import com.xuxiaolei.security.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: xuxiaolei
 * @Description: TODO:
 * @CreatTime: 2025/08/15 17:20
 **/
@SpringBootTest
public class SecurityFeignTest {
    @Autowired
    private JwtUtils jwtUtils;
    @Test
    void test() {
        String token = jwtUtils.generateToken("gateway-service");
        System.out.println("token = " + token);

    }
}
