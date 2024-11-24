package com.imooc.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.imooc.UserService;
import com.imooc.dto.user.UserDetailDTO;
import com.imooc.dto.user.UserInfoDTO;
import com.imooc.dto.user.UserLoginDTO;
import com.imooc.entity.Column;
import com.imooc.entity.User;
import com.imooc.enums.BizCodeEnum;
import com.imooc.exception.BizException;
import com.imooc.mapper.ColumnMapper;
import com.imooc.mapper.UserMapper;
import com.imooc.param.user.UserCreateParam;
import com.imooc.param.user.UserLoginParam;
import com.imooc.param.user.UserUpdateParam;
import com.imooc.properties.SecurityProperties;
import com.imooc.util.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    @Resource
    private HttpServletRequest request;

    @Resource
    private SecurityProperties securityProperties;


    @Override
    public UserLoginDTO login(UserLoginParam loginParam) {

        User userInfo = getUserByEmail(loginParam.getEmail());
        if (Objects.isNull(userInfo)) {
            throw new BizException(BizCodeEnum.PARAM_ERROR, "用户不存在");
        }
        // 判断用户名密码是否正确
        String md5Password = DigestUtil.md5Hex(loginParam.getPassword());

        if (!userInfo.getPassword().equals(md5Password)) {
            throw new BizException(BizCodeEnum.PARAM_ERROR, "用户密码错误");
        }

        UserLoginDTO loginInfo = new UserLoginDTO();
        Map<String, String> userInfoMap = new HashMap<>();
        userInfoMap.put("userId", userInfo.getId().toString());
        userInfoMap.put("email", loginParam.getEmail());

        loginInfo.setToken(JwtUtils.createToken(userInfoMap, securityProperties.getTokenExpired()));
        return loginInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDetailDTO createUser(UserCreateParam createParam) {

        User user = new User();
        user.setEmail(createParam.getEmail());
        user.setPassword(DigestUtil.md5Hex(createParam.getPassword()));
        user.setNickname(createParam.getNickname());

        userMapper.insert(user);

        Column column = new Column();

        column.setUserId(user.getId());
        column.setAuthor(user.getNickname());
        column.setAvatar(user.getAvatar());
        column.setTitle("这个人很懒什么都没有留下...");

        columnMapper.insert(column);
        UserDetailDTO detailDTO = new UserDetailDTO();
        BeanUtils.copyProperties(user, detailDTO);
        return detailDTO;
    }

    @Override
    public UserInfoDTO getCurrentUser() {
        String token = request.getHeader("token");
        if (StrUtil.isEmpty(token)) {
            throw new BizException(BizCodeEnum.USER_NOT_LOGIN, "登录信息为空");
        }

        Claims claims = JwtUtils.parseToken(token, securityProperties.getAllowedClockSkewSeconds());
        if (Objects.isNull(claims)) {
            throw new BizException(BizCodeEnum.USER_NOT_LOGIN, "token信息错误");
        }
        String userId = claims.get("userId", String.class);

        if (StrUtil.isEmpty(userId)) {
            throw new BizException(BizCodeEnum.PARAM_ERROR, "用户信息不存在");
        }
        User user = userMapper.selectById(Long.valueOf(userId));

        UserInfoDTO userInfoDTO = new UserInfoDTO();
        BeanUtils.copyProperties(user, userInfoDTO);

        return userInfoDTO;
    }

    @Override
    public UserInfoDTO updateUser(String userId, UserUpdateParam updateParam) {
        User updateUser = userMapper.selectById(userId);

        if (Objects.isNull(updateUser)) {
            throw new BizException(BizCodeEnum.PARAM_ERROR, "用户信息不存在");
        }

        BeanUtils.copyProperties(updateParam, updateUser);
        updateUser.setPassword(DigestUtil.md5Hex(updateParam.getPassword()));
        userMapper.updateById(updateUser);

        UserInfoDTO result = new UserInfoDTO();
        BeanUtils.copyProperties(updateUser, updateParam);
        return result;
    }


    private User getUserByEmail(String email) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getEmail, email);
        return userMapper.selectOne(wrapper);
    }
}
