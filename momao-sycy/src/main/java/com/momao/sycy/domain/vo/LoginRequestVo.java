package com.momao.sycy.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequestVo {
    /** 账号 */
    private String account;
    /** 密码 */
    private String password;
    /** 验证码 */
    private String code;
    /** 登录方式: password/code */
    private String loginType;
    /** 账号类型: email/phone/username */
    private String accountType;
}

