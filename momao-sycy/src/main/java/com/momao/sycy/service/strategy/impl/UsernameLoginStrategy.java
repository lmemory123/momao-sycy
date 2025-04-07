
package com.momao.sycy.service.strategy.impl;

import com.momao.sycy.domain.vo.LoginRequestVo;
import com.momao.sycy.domain.vo.LoginVo;
import com.momao.sycy.service.strategy.LoginStrategy;
import org.springframework.stereotype.Service;

@Service("UsernameLoginStrategy")
public class UsernameLoginStrategy implements LoginStrategy {
    @Override
    public LoginVo login(LoginRequestVo loginRequest) {
        return null;
    }
    // ...implement login logic for username
}
