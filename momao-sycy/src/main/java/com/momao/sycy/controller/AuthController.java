package com.momao.sycy.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.momao.common.core.domain.R;
import com.momao.common.core.utils.StringUtils;
import com.momao.common.encrypt.annotation.ApiEncrypt;
import com.momao.common.social.config.properties.SocialProperties;
import com.momao.sycy.domain.vo.LoginVo;
import com.momao.sycy.service.SysLoginService;
import com.momao.system.domain.vo.SysUserVo;
import com.momao.system.service.ISysClientService;
import com.momao.system.service.ISysConfigService;
import com.momao.system.service.ISysSocialService;
import com.momao.system.service.ISysTenantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ScheduledExecutorService;

/**
 * 认证
 *
 * @author Lion Li
 */
@Slf4j
@SaIgnore
@RequiredArgsConstructor
@RestController
@RequestMapping("/sycy/auth")
public class AuthController {

    private final SocialProperties socialProperties;
    private final SysLoginService loginService;
    private final ISysConfigService configService;
    private final ISysTenantService tenantService;
    private final ISysSocialService socialUserService;
    private final ISysClientService clientService;
    private final ScheduledExecutorService scheduledExecutorService;


    /**
     * 声韵次元 前台登录
     *
     * @param userVo 登录信息
     * @return 结果
     */
    @ApiEncrypt
    @PostMapping("/login")
    public R<LoginVo> login(@RequestBody SysUserVo userVo) {
        // 邮箱格式正则
        String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
        // 手机号格式正则
        String phonePattern = "^1[3-9]\\d{9}$";

        String username = userVo.getUserName();
        String password = userVo.getPassword();

        // 检查是否为空
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return R.fail("用户名和密码不能为空");
        }


        // 判断登录类型
        if (username.matches(emailPattern)) {
            // 邮箱登录
        } else if (username.matches(phonePattern)) {
            // 手机号登录
        } else {
            // 用户名登录
        }

        // TODD 登录验证
        return null;
    }

}
