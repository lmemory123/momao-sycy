package com.momao.sycy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: TokyoMomao
 * DateTime: 2025-04-26 19:08
 */
@SpringBootApplication
@SuppressWarnings("SpringJavaAutowiringInspection")
public class SycyApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SycyApplication.class);
        application.setApplicationStartup(new BufferingApplicationStartup(2048));
        application.run(args);
        System.out.println("(♥◠‿◠)ﾉﾞ  sycy启动成功   ლ(´ڡ`ლ)ﾞ");
    }
}
