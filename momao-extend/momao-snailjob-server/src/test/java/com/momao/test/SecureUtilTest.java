package com.momao.test;

import cn.hutool.crypto.SecureUtil;
import org.apache.coyote.http11.upgrade.UpgradeServletOutputStream;
import org.jetbrains.annotations.TestOnly;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: TokyoMomao
 * DateTime: 2025-03-26 14:32
 */


public class SecureUtilTest {

    @Test
    public void test() {
        String s = SecureUtil.sha256("123456");
        System.out.println(s);
        String s1 = SecureUtil.sha256("e10adc3949ba59abbe56e057f20f883e");
        System.out.println(s1);

    }
}
