package com.xuxiaolei.test;

import com.xuxiaolei.common.utils.R;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: xuxiaolei
 * @Description: TODO:
 * @CreatTime: 2025/08/15 12:08
 **/
@SpringBootTest
public class RestTest {

    @Test
    public void test() {
        System.out.println(R.ok("xuxiaolei"));
    }
}
