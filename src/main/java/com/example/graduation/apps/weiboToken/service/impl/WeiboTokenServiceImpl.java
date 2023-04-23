package com.example.graduation.apps.weiboToken.service.impl;

import com.example.graduation.apps.weiboToken.dto.WeiboTokenDTO;
import com.example.graduation.apps.weiboToken.entity.WeiboTokenEntity;
import com.example.graduation.apps.weiboToken.mapper.WeiboTokenMapper;
import com.example.graduation.apps.weiboToken.service.WeiboTokenService;
import com.example.graduation.config.WeiboPropertyConfig;
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
    ObjectMapper objectMapper;
    @Resource
    WeiboTokenMapper weiboTokenMapper;
    @Resource
    WeiboPropertyConfig.WeiboProperty weiboProperty;


    /**
     * 获取一个用户的所有 token
     */
    @Override
    public List<WeiboTokenDTO> findByUserId(Long userId) {
        List<WeiboTokenEntity> weiboTokenEntities = weiboTokenMapper.findByUserId(userId);
        return MyBeanUtils.BeanBuilder(weiboTokenEntities, WeiboTokenDTO.class);
    }

    /**
     * 根据 weiboTokenId 获取 token
     */
    @Override
    public WeiboTokenDTO findByWeiboTokenId(Long weiboTokenId) {
        return MyBeanUtils.BeanBuilder(weiboTokenMapper.findByWeiboTokenId(weiboTokenId), WeiboTokenDTO.class);
    }

    /**
     * 获取一个用户正在使用/未使用的 token
     */
    @Override
    public List<WeiboTokenDTO> findByUserIdAndStatus(Long userId, Integer status) {
        List<WeiboTokenEntity> weiboTokenEntities = weiboTokenMapper.findByUserIdAndStatus(userId, status);
        return MyBeanUtils.BeanBuilder(weiboTokenEntities, WeiboTokenDTO.class);
    }

    /**
     * 通过 code 为一个用户添加 token 信息
     */
    public Void create(Long userId, String code) throws MyException {

        String accessTokenUrl = "https://api.weibo.com/oauth2/access_token";
        String userUrl = "https://api.weibo.com/2/users/show.json";

        Map<String, String> parameter = new HashMap<>();
        parameter.put("client_id", weiboProperty.getClientId());
        parameter.put("client_secret", weiboProperty.getClientSecret());
        parameter.put("grant_type", weiboProperty.getGrantType());
        parameter.put("redirect_uri", weiboProperty.getRedirectUri());
        parameter.put("code", code);

        String tokenInfo = HttpUtils.post(accessTokenUrl, parameter, null);
        try {
            JsonNode jsonNode = objectMapper.readTree(tokenInfo);
            // 检验
            if (!jsonNode.has("access_token")) {
                throw new MyException(40000, "code 无效！");
            }
            String weiboToken = jsonNode.get("access_token").asText();
            if (weiboTokenMapper.findByWeiboToken(weiboToken) != null) {
                throw new MyException(40000, "token 已存在！");
            }
            // 请求用户信息
            Long weiboId = jsonNode.get("uid").asLong();
            parameter.clear();
            parameter.put("access_token", weiboToken);
            parameter.put("uid", String.valueOf(weiboId));
            String userInfo = HttpUtils.get(userUrl, parameter, null);
            String weiboName = objectMapper.readTree(userInfo).get("screen_name").asText();
            String weiboAvatar = objectMapper.readTree(userInfo).get("avatar_hd").asText();
            // 存入数据库
            WeiboTokenEntity weiboTokenEntity = new WeiboTokenEntity(
                    null, userId, weiboId, weiboName, weiboAvatar, weiboToken, new Date().getTime(), 0, 0);
            if (weiboTokenMapper.add(weiboTokenEntity) != 1) {
                throw new MyException(40000, "添加失败");
            }
        } catch (MyException e) {
            e.printStackTrace();
            throw new MyException(e.getCode(), e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(40000, "未知错误！");
        }
        return null;
    }

    /**
     * 更新账号下所有微博用户信息
     */
    @Override
    public Void update(Long userId) throws MyException {
        String userUrl = "https://api.weibo.com/2/users/show.json";
        // 根据 userId 获取账号所有 weiboToken 记录
        List<WeiboTokenEntity> weiboTokenEntities = weiboTokenMapper.findByUserId(userId);
        // 根据 WeiboToken 和 WeiboId 向微博请求用户信息
        for (WeiboTokenEntity weiboTokenEntity : weiboTokenEntities) {
            Map<String, String> parameter = new HashMap<>();
            parameter.put("appkey", weiboProperty.getAppKey());
            parameter.put("access_token", weiboTokenEntity.getWeiboToken());
            parameter.put("uid", String.valueOf(weiboTokenEntity.getWeiboId()));
            String userInfo = HttpUtils.get(userUrl, parameter, null);
            try {
                String weiboName = objectMapper.readTree(userInfo).get("screen_name").asText();
                String weiboAvatar = objectMapper.readTree(userInfo).get("avatar_hd").asText();

                if (!weiboTokenEntity.getWeiboName().equals(weiboName) || !weiboTokenEntity.getWeiboAvatar().equals(weiboAvatar)) {
                    // 处理请求，保存用户信息
                    weiboTokenEntity.setWeiboName(weiboName);
                    weiboTokenEntity.setWeiboAvatar(weiboAvatar);
                    if (weiboTokenMapper.update(weiboTokenEntity) != 1) throw new MyException(40000, "更新失败");
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new MyException(40000, "未知错误！");
            }
        }
        return null;
    }

    /**
     * 根据 weiboTokenId 删除 token
     */
    @Override
    public Void deleteByTokenId(Long weiboTokenId) throws MyException {
        String deleteUrl = "https://api.weibo.com/oauth2/revokeoauth2";

        WeiboTokenEntity weiboTokenEntity = weiboTokenMapper.findByTokenId(weiboTokenId);

        Map<String, String> parameter = new HashMap<>();
        parameter.put("access_token", weiboTokenEntity.getWeiboToken());

        String deleteInfo = HttpUtils.get(deleteUrl, parameter, null);
        try {
            JsonNode jsonNode = objectMapper.readTree(deleteInfo);
            if (!jsonNode.has("result") || !jsonNode.get("result").asText().equals("true")) {
                throw new MyException(40000, "删除失败");
            }
            weiboTokenMapper.deleteByTokenId(weiboTokenId);
        } catch (MyException e) {
            e.printStackTrace();
            throw new MyException(e.getCode(), e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(40000, "未知错误！");
        }
        return null;
    }

    /**
     * 更新 token 的 status（启用/停用）
     */
    @Override
    public Void updateStatus(Long weiboTokenId) {
        // 取出 weiboToken 记录
        WeiboTokenEntity weiboTokenEntity = weiboTokenMapper.findByTokenId(weiboTokenId);
        if (weiboTokenEntity.getStatus() == 1) {
            // 停用
            weiboTokenEntity.setStatus(0);
            weiboTokenMapper.update(weiboTokenEntity);
        } else {
            // 启用
            List<WeiboTokenEntity> weiboTokenEntities = weiboTokenMapper.findByUserIdAndStatus(weiboTokenEntity.getUserId(), 1);
            for (WeiboTokenEntity weiboToken : weiboTokenEntities) {
                weiboToken.setStatus(0);
                weiboTokenMapper.update(weiboToken);
            }
            weiboTokenEntity.setStatus(1);
            weiboTokenMapper.update(weiboTokenEntity);
        }
        return null;
    }

}