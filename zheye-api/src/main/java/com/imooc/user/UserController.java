package com.imooc.user;

import com.imooc.dto.UserCreateDTO;
import com.imooc.dto.UserLoginDTO;
import com.imooc.param.UserCreateParam;
import com.imooc.param.UserLoginParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户相关接口
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/login")
    public UserLoginDTO login(@RequestBody UserLoginParam loginParam) {
        UserLoginDTO loginInfo = new UserLoginDTO();
        loginInfo.setToken("u1ng3hj1");
        return loginInfo;
    }

    @PostMapping("users")
    public UserCreateDTO createUser(@RequestBody UserCreateParam createParam) {
        return new UserCreateDTO();
    }
}
