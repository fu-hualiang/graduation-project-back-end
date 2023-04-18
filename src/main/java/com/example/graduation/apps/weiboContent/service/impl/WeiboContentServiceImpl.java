package com.example.graduation.apps.weiboContent.service.impl;

import com.example.graduation.apps.weiboContent.dto.WeiboComment;
import com.example.graduation.apps.weiboContent.dto.WeiboContent;
import com.example.graduation.apps.weiboContent.service.WeiboContentService;
import com.example.graduation.apps.weiboUser.object.WeiboUser;
import com.example.graduation.exception.MyException;
import com.example.graduation.utils.HttpUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                List<String > PicUrls = new ArrayList<>();
                for (int j=0;j<statuses.get("pic_urls").size();j++){
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

    /**
     * 获取用户的微博的评论
     */
    @Override
    public List<WeiboComment> findCommitByWeiboContentId(String weiboToken, Long weiboContentId) throws MyException {
        String commentUrl = "https://api.weibo.com/2/comments/show.json";

        Map<String, String> parameter = new HashMap<>();
        parameter.put("access_token", weiboToken);
        parameter.put("id", String.valueOf(weiboContentId));
        parameter.put("count","200");
        parameter.put("page","1");
        String res = HttpUtils.get(commentUrl, parameter, null);
        List<WeiboComment> weiboCommentList = new ArrayList<>();
        try {
            int count = objectMapper.readTree(res).get("comments").size();
            for (int i = 0; i < count; i++) {
                JsonNode comments = objectMapper.readTree(res).get("comments").get(i);
                WeiboComment weiboComment=new WeiboComment();

                weiboComment.setCreatedAt(Date.parse(comments.get("created_at").asText()));
                weiboComment.setWeiboCommentId(comments.get("id").asLong());
                weiboComment.setText(comments.get("text").asText());
                weiboComment.setSource(comments.get("source").asText());
                WeiboUser weiboUser = objectMapper.readValue(comments.get("user").toString(), WeiboUser.class);
                weiboComment.setWeiboUser(weiboUser);
                weiboComment.setRootId(comments.get("rootid").asLong());
                weiboCommentList.add(weiboComment);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(40000, "未知错误");
        }
        return weiboCommentList;
    }
}
