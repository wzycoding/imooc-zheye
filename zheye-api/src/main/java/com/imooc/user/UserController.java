package com.imooc.user;

import com.imooc.dto.UserInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户相关接口
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/login")
    public UserInfoDTO login() {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setName("wzy");
        return userInfoDTO;
    }
}
