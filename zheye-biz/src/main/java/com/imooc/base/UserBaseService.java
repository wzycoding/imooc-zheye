package com.imooc.base;

import com.imooc.dto.user.UserDetailDTO;
import com.imooc.entity.User;

/**
 * @author wzy
 * @date 2024/11/25 11:28
 */
public interface UserBaseService {
    /**
     * 获取用户信息
     *
     * @param userId 用户id
     * @return 用户信息
     */
    User getUserById(Long userId);

    UserDetailDTO getUserDetail(Long userId);
}
