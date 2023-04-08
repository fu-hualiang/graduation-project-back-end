package com.example.graduation.apps.weiboAuth.service;

import com.example.graduation.apps.weiboAuth.dto.WeiboTokenDTO;
import com.example.graduation.exception.MyException;

import java.util.List;

public interface WeiboTokenService {

    List<WeiboTokenDTO> findByUserId(Long userId);

    WeiboTokenDTO add(Long userId, String code) throws MyException;
}
