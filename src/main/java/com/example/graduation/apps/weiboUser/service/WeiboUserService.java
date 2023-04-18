package com.example.graduation.apps.weiboUser.service;

import com.example.graduation.apps.weiboUser.object.WeiboUser;
import com.example.graduation.exception.MyException;

public interface WeiboUserService {
    WeiboUser findByWeiboId(Long weiboId, String weiboToken) throws MyException;
}
