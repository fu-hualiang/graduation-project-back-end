package com.example.graduation.apps.user.service.impl;

import com.example.graduation.apps.user.dto.UserDTO;
import com.example.graduation.apps.user.entity.UserEntity;
import com.example.graduation.apps.user.form.LoginForm;
import com.example.graduation.apps.user.mapper.UserMapper;
import com.example.graduation.apps.user.service.UserService;
import com.example.graduation.exception.MyException;
import com.example.graduation.utils.MyBeanUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public UserDTO login(LoginForm loginForm) throws MyException {
        UserEntity userEntity;
        try {
            userEntity = userMapper.findByUserName(loginForm.getUsername());
            if (userEntity == null) {
                throw new MyException(40000, "无此用户！");
            } else if (!userEntity.getPassword().equals(loginForm.getPassword())) {
                throw new MyException(40000, "密码错误！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(40000, "未知错误");
        }
        return MyBeanUtils.BeanBuilder(userEntity, UserDTO.class);
    }
}
