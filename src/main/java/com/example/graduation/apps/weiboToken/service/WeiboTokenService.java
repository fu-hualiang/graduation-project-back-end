package com.example.graduation.apps.weiboToken.service;

import com.example.graduation.apps.weiboToken.dto.WeiboTokenDTO;
import com.example.graduation.exception.MyException;

import java.util.List;

public interface WeiboTokenService {

    List<WeiboTokenDTO> findByUserId(Integer userId);

    String add(Integer userId, String code) throws MyException;
}
