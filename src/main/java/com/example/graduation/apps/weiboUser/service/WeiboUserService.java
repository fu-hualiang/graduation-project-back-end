package com.example.graduation.apps.weiboUser.service;

import com.example.graduation.apps.weiboUser.dto.WeiboUserDTO;
import com.example.graduation.exception.MyException;

public interface WeiboUserService {
    WeiboUserDTO findByWeiboId(Long weiboId,String weiboToken) throws MyException;
}
