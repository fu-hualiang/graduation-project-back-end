package com.example.graduation.apps.weiboToken.service;

import com.example.graduation.apps.weiboToken.dto.WeiboTokenDTO;
import com.example.graduation.exception.MyException;

import java.util.List;

public interface WeiboTokenService {

    List<WeiboTokenDTO> findByUserId(Long userId);

    WeiboTokenDTO findByWeiboTokenId(Long weiboTokenId);

    List<WeiboTokenDTO> findByUserIdAndStatus(Long userId, Integer status);

    Void create(Long userId, String code) throws MyException;

    Void update(Long userId) throws MyException;

    Void deleteByTokenId(Long weiboTokenId) throws MyException;

    Void updateStatus(Long weiboTokenId);
}
