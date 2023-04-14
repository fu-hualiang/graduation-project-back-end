package com.example.graduation.apps.user.service.impl;

import com.example.graduation.apps.user.dto.UserDTO;
import com.example.graduation.apps.user.entity.UserEntity;
import com.example.graduation.apps.user.mapper.UserMapper;
import com.example.graduation.apps.user.service.UserService;
import com.example.graduation.utils.MyBeanUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Value("${static-path}")
    String staticPath;
    @Resource
    UserMapper userMapper;

    @Override
    public UserDTO findByUserId(Long userId) {
        UserEntity userEntity = userMapper.findByUserId(userId);
        String avatarPath = staticPath.replace("*", "") + userEntity.getAvatar();
        userEntity.setAvatar(avatarPath);
        return MyBeanUtils.BeanBuilder(userEntity, UserDTO.class);
    }
}
