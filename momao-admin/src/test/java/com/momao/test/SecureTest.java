package com.momao.test;

import cn.dev33.satoken.secure.BCrypt;
import cn.hutool.crypto.SecureUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created with IntelliJ IDEA.
 * Description: 生成密码
 *
 * @Author: TokyoMomao
 * DateTime: 2025-03-26 14:05
 */


// @SpringBootTest
public class SecureTest {


    @Test
    public void testSecure(){
        String hashpw = BCrypt.hashpw("123456");
        System.out.println(hashpw);


    }

}
