package com.example.graduation.apps.log.service.impl;

import com.example.graduation.apps.log.form.LoginForm;
import com.example.graduation.apps.log.service.LogService;
import com.example.graduation.apps.log.dto.TokenDTO;
import com.example.graduation.apps.user.entity.UserEntity;
import com.example.graduation.apps.user.mapper.UserMapper;
import com.example.graduation.apps.weiboToken.entity.WeiboTokenEntity;
import com.example.graduation.apps.weiboToken.mapper.WeiboTokenMapper;
import com.example.graduation.exception.MyException;
import com.example.graduation.utils.TokenUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("logService")
public class LogServiceImpl implements LogService {
    @Resource
    UserMapper userMapper;
    @Resource
    WeiboTokenMapper weiboTokenMapper;
    @Override
    public TokenDTO login(LoginForm loginForm) throws MyException {
        UserEntity userEntity;
        // 获取用户记录
        userEntity = userMapper.findByUsername(loginForm.getUsername());
        if (userEntity == null || !userEntity.getPassword().equals(loginForm.getPassword())) {
            throw new MyException(40000, "账号或者密码错误");
        }
        Long userId = userEntity.getUserId();
        // 获取正在使用的微博 token 记录
        List<WeiboTokenEntity> weiboTokenEntities = weiboTokenMapper.findByUserIdAndStatus(userId, 1);
        Long weiboTokenId = null;
        if (weiboTokenEntities.size()==1){
            weiboTokenId = weiboTokenEntities.get(0).getWeiboTokenId();
        }

        TokenDTO token = new TokenDTO();
        token.setUserId(userId);
        token.setWeiboTokenId(weiboTokenId);
        token.setToken(TokenUtils.generateToken(userId));

        return token;
    }
}
