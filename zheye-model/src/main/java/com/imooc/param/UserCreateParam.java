package com.imooc.param;

import lombok.Data;

/**
 * @author wzy
 * @date 2024/11/20 16:34
 */
@Data
public class UserCreateParam {
    private String email;
    private String nickname;
    private String password;
}
