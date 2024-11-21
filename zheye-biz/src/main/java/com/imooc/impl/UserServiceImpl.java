package com.imooc.impl;

import com.imooc.UserService;
import com.imooc.dto.user.UserDetailDTO;
import com.imooc.dto.user.UserInfoDTO;
import com.imooc.dto.user.UserLoginDTO;
import com.imooc.entity.Column;
import com.imooc.entity.User;
import com.imooc.mapper.ColumnMapper;
import com.imooc.mapper.UserMapper;
import com.imooc.param.user.UserCreateParam;
import com.imooc.param.user.UserLoginParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author wzy
 * @date 2024/11/21 10:49
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private ColumnMapper columnMapper;

    @Override
    public UserLoginDTO login(UserLoginParam loginParam) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDetailDTO createUser(UserCreateParam createParam) {

        User user = new User();
        user.setEmail(createParam.getEmail());
        user.setPassword(createParam.getPassword());
        user.setNickname(createParam.getNickname());

        userMapper.insert(user);

        Column column = new Column();

        column.setUserId(user.getId());
        column.setAuthor(user.getNickname());
        column.setAvatar(user.getAvatar());
        column.setTitle("很棒的专栏");

        columnMapper.insert(column);
        UserDetailDTO detailDTO = new UserDetailDTO();
        BeanUtils.copyProperties(user, detailDTO);
        return detailDTO;
    }

    @Override
    public UserInfoDTO getCurrentUser() {
        return null;
    }

    @Override
    public UserInfoDTO updateUser(String id) {
        return null;
    }
}
