package com.momao.sycy.service.strategy.impl;

import com.momao.common.core.exception.ServiceException;
import com.momao.sycy.domain.vo.LoginRequestVo;
import com.momao.sycy.domain.vo.LoginVo;
import com.momao.sycy.service.strategy.LoginStrategy;
import com.momao.system.domain.vo.SysUserVo;
import com.momao.system.service.impl.SysUserServiceImpl;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service("EmailLoginStrategy")
@RequiredArgsConstructor
public class EmailLoginStrategy implements LoginStrategy {

    private final SysUserServiceImpl sysUserService;

    @Override
    public LoginVo login(LoginRequestVo loginRequest) {
        SysUserVo user = sysUserService.selectUserByEmail(loginRequest.getAccount());
        if (user == null) {
            // create user
        }


        // 验证登录方式


        return createLoginVo(user);
    }

    private LoginVo createLoginVo(SysUserVo user) {
        return null;
    }


}

