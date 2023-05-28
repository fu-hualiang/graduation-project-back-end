package com.example.graduation.apps.weiboContent.service.impl;

import com.example.graduation.apps.weiboContent.object.WeiboContent;
import com.example.graduation.apps.weiboContent.object.WeiboUser;
import com.example.graduation.apps.weiboContent.service.WeiboContentService;
import com.example.graduation.exception.MyException;
import com.example.graduation.utils.HttpUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("weiboContentService")
public class WeiboContentServiceImpl implements WeiboContentService {
    @Resource
    ObjectMapper objectMapper;

    /**
     * 获取用户的微博
     */
    @Override
    public List<WeiboContent> find(String weiboToken) throws MyException {
        String userUrl = "https://api.weibo.com/2/statuses/user_timeline.json";

        Map<String, String> parameter = new HashMap<>();
        parameter.put("access_token", weiboToken);
        String res = HttpUtils.get(userUrl, parameter, null);
        List<WeiboContent> weiboContentList = new ArrayList<>();
        try {
            int count = objectMapper.readTree(res).get("statuses").size();
            for (int i = 0; i < count; i++) {
                WeiboContent weiboContent = new WeiboContent();
                JsonNode statuses = objectMapper.readTree(res).get("statuses").get(i);
                weiboContent.setWeiboContentId(statuses.get("id").asLong());
                weiboContent.setSource(statuses.get("source").asText());
                weiboContent.setText(statuses.get("text").asText());
                weiboContent.setCreatedAt(Date.parse(statuses.get("created_at").asText()));
                weiboContent.setRepostsCount(statuses.get("reposts_count").asLong());
                weiboContent.setCommentsCount(statuses.get("comments_count").asLong());
                weiboContent.setAttitudesCount(statuses.get("attitudes_count").asLong());
                List<String> PicUrls = new ArrayList<>();
                for (int j = 0; j < statuses.get("pic_urls").size(); j++) {
                    PicUrls.add(statuses.get("pic_urls").get(j).get("thumbnail_pic").asText());
                }
                weiboContent.setPicUrls(PicUrls);
                weiboContentList.add(weiboContent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(40000, "未知错误");
        }
        return weiboContentList;
    }

    @Override
    public List<WeiboContent> findMention(String weiboToken) throws MyException {
        String userUrl = "https://api.weibo.com/2/statuses/mentions.json";

        Map<String, String> parameter = new HashMap<>();
        parameter.put("access_token", weiboToken);
        String res = HttpUtils.get(userUrl, parameter, null);
        List<WeiboContent> weiboContentList = new ArrayList<>();
        try {
            int count = objectMapper.readTree(res).get("statuses").size();
            for (int i = 0; i < count; i++) {
                WeiboContent weiboContent = new WeiboContent();
                JsonNode statuses = objectMapper.readTree(res).get("statuses").get(i);
                weiboContent.setWeiboContentId(statuses.get("id").asLong());
                weiboContent.setSource(statuses.get("source").asText());
                weiboContent.setText(statuses.get("text").asText());
                weiboContent.setCreatedAt(Date.parse(statuses.get("created_at").asText()));
                weiboContent.setRepostsCount(statuses.get("reposts_count").asLong());
                weiboContent.setCommentsCount(statuses.get("comments_count").asLong());
                weiboContent.setAttitudesCount(statuses.get("attitudes_count").asLong());
                weiboContent.setPicUrls(new ArrayList<>());

                WeiboUser weiboUser = new WeiboUser();
                JsonNode user = statuses.get("user");
                weiboUser.setWeiboId(user.get("id").asLong());
                weiboUser.setWeiboName(user.get("screen_name").asText());
                weiboUser.setLocation(user.get("location").asText());
                weiboUser.setWeiboAvatar(user.get("avatar_hd").asText());
                weiboContent.setWeiboUser(weiboUser);

                weiboContentList.add(weiboContent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(40000, "未知错误");
        }
        return weiboContentList;
    }

    @Override
    public Void create(String weiboToken, String status, String picture, String path) throws UnsupportedEncodingException {
//        status = URLEncoder.encode(status, StandardCharsets.UTF_8);
        String shareUrl = "https://api.weibo.com/2/statuses/share.json";

        Map<String, String> body = new HashMap<>();
        body.put("status", status + "http://lighthouse.kingstarfly.cn");

        String res;
        if (picture == null) {
            Map<String, String> parameter = new HashMap<>();
            parameter.put("access_token", weiboToken);
            parameter.put("rip", "124.222.8.252");
            res = HttpUtils.post(shareUrl, parameter, body);
        } else {
            body.put("rip", "124.222.8.252");
            body.put("access_token", weiboToken);

            String pictureName = picture.split("/")[picture.split("/").length - 1];
            File file = new File(path + pictureName);
            res = HttpUtils.post(shareUrl, null, body, file);
        }
        return null;
    }
}
