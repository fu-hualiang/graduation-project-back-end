package com.example.graduation.apps.user.service.impl;

import com.example.graduation.apps.user.dto.TokenDTO;
import com.example.graduation.apps.user.dto.UserDTO;
import com.example.graduation.apps.user.entity.UserEntity;
import com.example.graduation.apps.user.form.LoginForm;
import com.example.graduation.apps.user.mapper.UserMapper;
import com.example.graduation.apps.user.service.UserService;
import com.example.graduation.exception.MyException;
import com.example.graduation.utils.MyBeanUtils;
import com.example.graduation.utils.TokenUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Value("http://localhost:${server.port}${spring.mvc.static-path-pattern}")
    String staticPath;
    @Resource
    UserMapper userMapper;

    @Override
    public TokenDTO login(LoginForm loginForm) throws MyException {
        UserEntity userEntity;
        try {
            userEntity = userMapper.findByUserName(loginForm.getUsername());
            if (userEntity == null || !userEntity.getPassword().equals(loginForm.getPassword())) {
                throw new MyException(40000, "账号或者密码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(40000, "未知错误");
        }
        TokenDTO token = new TokenDTO();
        token.setToken(TokenUtils.generateToken(userEntity.getId()));
        token.setUserId(userEntity.getId());
        return token;
    }

    @Override
    public UserDTO findByUserId(Long userId) {
        UserEntity userEntity = userMapper.findByUserId(userId);
        String avatarPath = staticPath.replace("*", "") + userEntity.getAvatar();
        userEntity.setAvatar(avatarPath);
        return MyBeanUtils.BeanBuilder(userEntity, UserDTO.class);
    }
}
