package com.example.graduation.apps.weiboUser.service.impl;

import com.example.graduation.apps.weiboUser.dto.WeiboUserDTO;
import com.example.graduation.apps.weiboUser.service.WeiboUserService;
import com.example.graduation.exception.MyException;
import com.example.graduation.utils.HttpUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@Service("weiboUserService")
public class WeiboUserServiceImpl implements WeiboUserService {
    @Resource
    ObjectMapper objectMapper;
    /**
     * 获取微博用户信息
     */
    @Override
    public WeiboUserDTO findByWeiboId(Long weiboId,String weiboToken) throws MyException {
        String userUrl = "https://api.weibo.com/2/users/show.json";

        Map<String ,String > parameter = new HashMap<>();
        parameter.put("access_token", weiboToken);
        parameter.put("uid", String.valueOf(weiboId));
        String userInfo = HttpUtils.get(userUrl, parameter, null);
        WeiboUserDTO weiboUser;
        try {
            weiboUser = objectMapper.readValue(userInfo, WeiboUserDTO.class);
            String createdAt = objectMapper.readTree(userInfo).get("created_at").asText();
            weiboUser.setCreatedAt(Date.parse(createdAt));
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(40000,"未知错误");
        }
        return weiboUser;
    }
}
