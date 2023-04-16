package com.example.graduation.apps.weiboContent.service;

import com.example.graduation.apps.weiboContent.dto.WeiboContentDTO;

import java.util.List;

public interface WeiboContentService {
    List<WeiboContentDTO> findByUserId(String weiboToken, Long userId, Integer count, Integer page);
}
