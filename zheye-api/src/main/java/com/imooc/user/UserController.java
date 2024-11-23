package com.imooc.user;

import com.imooc.UserService;
import com.imooc.dto.user.UserDetailDTO;
import com.imooc.dto.user.UserInfoDTO;
import com.imooc.dto.user.UserLoginDTO;
import com.imooc.param.user.UserCreateParam;
import com.imooc.param.user.UserLoginParam;
import com.imooc.param.user.UserUpdateParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户相关接口
 *
 * @date 2024-11-20 20:00:28
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public UserLoginDTO login(@RequestBody UserLoginParam loginParam) {
        return userService.login(loginParam);
    }

    /**
     * 创建用户
     */
    @PostMapping("users")
    public UserDetailDTO createUser(@RequestBody UserCreateParam createParam) {
        return userService.createUser(createParam);
    }

    /**
     * 获取当前用户信息
     */
    @GetMapping("current")
    public UserInfoDTO getCurrentUser() {
        return userService.getCurrentUser();
    }

    /**
     * 更新用户信息
     */
    @PatchMapping("/{id}")
    public UserInfoDTO updateUser(@PathVariable(name = "id") String id,
                                  @RequestBody UserUpdateParam updateParam) {
        return userService.updateUser(id, updateParam);
    }
}
