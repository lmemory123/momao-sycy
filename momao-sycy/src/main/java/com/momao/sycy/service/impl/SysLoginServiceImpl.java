package com.momao.sycy.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.momao.common.core.exception.ServiceException;
import com.momao.sycy.domain.vo.LoginRequestVo;
import com.momao.sycy.domain.vo.LoginVo;
import com.momao.sycy.service.SysLoginService;
import com.momao.sycy.service.strategy.LoginStrategy;
import com.momao.sycy.service.strategy.LoginStrategyFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SysLoginServiceImpl implements SysLoginService {

    private final LoginStrategyFactory loginStrategyFactory;

    @Override
    public LoginVo login(LoginRequestVo loginRequest) {
        // 获取对应的登录策略
        LoginStrategy strategy = loginStrategyFactory.getStrategy(loginRequest.getAccountType());
        if (strategy == null) {
            throw new ServiceException("不支持的登录类型");
        }

        // 执行登录策略
        return strategy.login(loginRequest);
    }

    @Override
    public void logout() {
        try {
            StpUtil.logout();
        } catch (Exception e) {
            log.error("登出异常", e);
        }
    }
}
