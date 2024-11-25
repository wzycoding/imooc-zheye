package com.imooc.base.impl;

import com.imooc.base.UserBaseService;
import com.imooc.dto.user.UserDetailDTO;
import com.imooc.entity.User;
import com.imooc.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wzy
 * @date 2024/11/25 11:36
 */
@Slf4j
@Service
public class UserBaseServiceImpl implements UserBaseService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserById(Long userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public UserDetailDTO getUserDetail(Long userId) {
        User user = userMapper.selectById(userId);
        UserDetailDTO result = new UserDetailDTO();
        BeanUtils.copyProperties(user, userId);

        return result;
    }
}
