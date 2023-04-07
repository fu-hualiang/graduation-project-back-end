package com.example.graduation.apps.weiboAuth.service;

import com.example.graduation.exception.MyException;

public interface WeiboAuthService {
    public String getWeiboToken(String code) throws MyException;
}
