package com.momao.sycy.service.strategy;

import com.momao.sycy.domain.vo.LoginRequestVo;
import com.momao.sycy.domain.vo.LoginVo;

public interface LoginStrategy {
    /**
     * 登录处理
     */
    LoginVo login(LoginRequestVo loginRequest);
}


