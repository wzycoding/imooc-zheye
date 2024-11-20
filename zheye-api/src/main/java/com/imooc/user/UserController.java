package com.imooc.user;

import com.imooc.dto.user.UserCreateDTO;
import com.imooc.dto.user.UserInfoDTO;
import com.imooc.dto.user.UserLoginDTO;
import com.imooc.param.user.UserCreateParam;
import com.imooc.param.user.UserLoginParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 用户相关接口
 *
 * @date 2024-11-20 20:00:28
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public UserLoginDTO login(@RequestBody UserLoginParam loginParam) {
        UserLoginDTO loginInfo = new UserLoginDTO();
        loginInfo.setToken("u1ng3hj1");
        return loginInfo;
    }

    /**
     * 创建用户
     */
    @PostMapping("users")
    public UserCreateDTO createUser(@RequestBody UserCreateParam createParam) {
        return new UserCreateDTO();
    }

    /**
     * 获取当前用户信息
     */
    @GetMapping("current")
    public UserInfoDTO getCurrentUser() {
        return new UserInfoDTO();
    }

    /**
     * 更新用户信息
     */
    @PatchMapping("/{id}")
    public UserInfoDTO updateUser(@PathVariable(name = "id") String id) {
        return new UserInfoDTO();
    }
}
