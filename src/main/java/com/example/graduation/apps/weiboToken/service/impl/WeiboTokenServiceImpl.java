package com.example.graduation.apps.weiboToken.service.impl;

import com.example.graduation.apps.weiboToken.dto.WeiboTokenDTO;
import com.example.graduation.apps.weiboToken.entity.WeiboTokenEntity;
import com.example.graduation.apps.weiboToken.mapper.WeiboTokenMapper;
import com.example.graduation.apps.weiboToken.service.WeiboTokenService;
import com.example.graduation.exception.MyException;
import com.example.graduation.utils.HttpUtils;
import com.example.graduation.utils.MyBeanUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service("weiboAuthService")
public class WeiboTokenServiceImpl implements WeiboTokenService {
    @Resource
    WeiboTokenMapper weiboTokenMapper;

    @Override
    public List<WeiboTokenDTO> findByUserId(Integer userId) {
        List<WeiboTokenEntity> entityList = weiboTokenMapper.findByUserId(userId);
        return MyBeanUtils.BeanBuilder(entityList, WeiboTokenDTO.class);
    }

    public String add(Integer userId, String code) throws MyException {

        String url = "https://api.weibo.com/oauth2/access_token";

        Map<String, String> parameter = new HashMap<>();
        parameter.put("client_id", "2843458463");
        parameter.put("client_secret", "5bafa7257b6623c59d18648e2b1f146a");
        parameter.put("grant_type", "authorization_code");
        parameter.put("redirect_uri", "http://124.222.8.252:5173/");
        parameter.put("code", code);
        String str = HttpUtils.post(url, parameter, null);
        // 处理请求结果
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(str);
            if (!jsonNode.has("access_token")) {
                throw new MyException(400, "code无效");
            }

            WeiboTokenEntity weiboToken = new WeiboTokenEntity();
            weiboToken.setUserId(userId);
            weiboToken.setWeiboToken(jsonNode.get("access_token").asText());
            weiboToken.setCreateTime(new Date());
            weiboToken.setStatus(0);

            if (weiboTokenMapper.add(weiboToken) != 1) {
                throw new MyException(400, "添加失败");
            }
            return objectMapper.readValue(jsonNode.get("access_token").toString(), String.class);
        } catch (MyException e) {
            e.printStackTrace();
            throw new MyException(e.getCode(), e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(400, "未知错误！");
        }
    }

}