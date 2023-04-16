package com.example.graduation.apps.weiboContent.service.impl;

import com.example.graduation.apps.weiboContent.dto.WeiboContentDTO;
import com.example.graduation.apps.weiboContent.service.WeiboContentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("weiboContentService")
public class WeiboContentServiceImpl implements WeiboContentService {
    /**
     * 分页获取用户的微博
     */
    @Override
    public List<WeiboContentDTO> findByUserId(String weiboToken, Long userId, Integer count, Integer page) {
        return null;
    }
}
