package com.example.graduation.apps.weiboAuth.service.impl;

import com.example.graduation.apps.weiboAuth.mapper.WeiboAuthMapper;
import com.example.graduation.apps.weiboAuth.service.WeiboAuthService;
import com.example.graduation.exception.MyException;
import com.example.graduation.utils.HttpUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Transactional
@Service("weiboAuthService")
public class WeiboAuthServiceImpl implements WeiboAuthService {
    @Resource
    WeiboAuthMapper weiboAuthMapper;

    public String getWeiboToken(String code) throws MyException {

        String url = "https://api.weibo.com/oauth2/access_token";

        Map<String, String> parameter = new HashMap<>();
        parameter.put("client_id", "2843458463");
        parameter.put("client_secret", "5bafa7257b6623c59d18648e2b1f146a");
        parameter.put("grant_type", "authorization_code");
        parameter.put("redirect_uri", "http://124.222.8.252:5173/");
        parameter.put("code", code);
        String str = HttpUtils.post(url, parameter, null);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(str);
            if (!jsonNode.has("access_token")) {
                throw new MyException(400, "code无效");
            }
            return objectMapper.readValue(jsonNode.get("access_token").toString(), String.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(400, "未知错误！");
        }
    }
}