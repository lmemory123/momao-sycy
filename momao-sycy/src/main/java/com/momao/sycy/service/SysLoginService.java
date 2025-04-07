package com.momao.sycy.service;

import com.momao.sycy.domain.vo.LoginRequestVo;
import com.momao.sycy.domain.vo.LoginVo;

public interface SysLoginService {
    /**
     * 登录
     */
    LoginVo login(LoginRequestVo loginRequest);

    /**
     * 登出
     */
    void logout();
}
