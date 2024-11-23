package com.imooc;

import com.imooc.dto.user.UserDetailDTO;
import com.imooc.dto.user.UserInfoDTO;
import com.imooc.dto.user.UserLoginDTO;
import com.imooc.param.user.UserCreateParam;
import com.imooc.param.user.UserLoginParam;
import com.imooc.param.user.UserUpdateParam;

/**
 * @author wzy
 * @date 2024/11/21 09:47
 */
public interface UserService {
    /**
     * 用户登录
     *
     * @param loginParam 登录参数
     * @return token信息
     */
    UserLoginDTO login(UserLoginParam loginParam);

    /**
     * 创建用户
     */
    UserDetailDTO createUser(UserCreateParam createParam);

    /**
     * 获取当前用户信息
     */
    UserInfoDTO getCurrentUser();

    /**
     * 更新用户信息
     */
    UserInfoDTO updateUser(String userId, UserUpdateParam userUpdateParam);
}
