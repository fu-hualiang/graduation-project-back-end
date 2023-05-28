package com.example.graduation.apps.data.service.impl;

import com.example.graduation.apps.data.dto.AccountDTO;
import com.example.graduation.apps.data.dto.UserDTO;
import com.example.graduation.apps.data.entity.AccountEntity;
import com.example.graduation.apps.data.entity.UserEntity;
import com.example.graduation.apps.data.mapper.DataMapper;
import com.example.graduation.apps.data.service.DataService;
import com.example.graduation.utils.MyBeanUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service("dataService")
public class DataServiceImpl implements DataService {
    @Resource
    DataMapper dataMapper;
    @Override
    public List<AccountDTO> findAccountData(Long weiboId) {
        List<AccountEntity> accountEntityList = dataMapper.findAccountData(weiboId);
        Collections.reverse(accountEntityList);
        return MyBeanUtils.BeanBuilder(accountEntityList,AccountDTO.class);
    }

    @Override
    public List<UserDTO> findUserData(Long weiboId) {
        List<UserEntity> userEntityList = dataMapper.findUserData(weiboId);
        return MyBeanUtils.BeanBuilder(userEntityList,UserDTO.class);
    }
}
