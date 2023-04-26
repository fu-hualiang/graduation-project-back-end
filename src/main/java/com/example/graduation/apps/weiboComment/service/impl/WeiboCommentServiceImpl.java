package com.example.graduation.apps.weiboComment.service.impl;

import com.example.graduation.apps.weiboComment.object.WeiboComment;
import com.example.graduation.apps.weiboComment.object.WeiboContent;
import com.example.graduation.apps.weiboComment.object.WeiboUser;
import com.example.graduation.apps.weiboComment.service.WeiboCommentService;
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

@Service("WeiboCommentService")
public class WeiboCommentServiceImpl implements WeiboCommentService {
    @Resource
    ObjectMapper objectMapper;

    /**
     * 获取用户的微博的评论
     */
    @Override
    public List<WeiboComment> findByWeiboContentId(String weiboToken, Long weiboContentId) throws MyException {
        String commentUrl = "https://api.weibo.com/2/comments/show.json";

        Map<String, String> parameter = new HashMap<>();
        parameter.put("access_token", weiboToken);
        parameter.put("id", String.valueOf(weiboContentId));
        parameter.put("count", "200");
        parameter.put("page", "1");
        String res = HttpUtils.get(commentUrl, parameter, null);
        List<WeiboComment> weiboCommentList = new ArrayList<>();
        try {
            int count = objectMapper.readTree(res).get("comments").size();
            for (int i = 0; i < count; i++) {
                JsonNode comments = objectMapper.readTree(res).get("comments").get(i);
                WeiboComment weiboComment = new WeiboComment();

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

    @Override
    public List<WeiboComment> findMention(String weiboToken) throws MyException {
        String userUrl = "https://api.weibo.com/2/comments/mentions.json";

        Map<String, String> parameter = new HashMap<>();
        parameter.put("access_token", weiboToken);
        String res = HttpUtils.get(userUrl, parameter, null);
        List<WeiboComment> weiboCommentList = new ArrayList<>();
        try {
            int count = objectMapper.readTree(res).get("total_number").asInt();
            for (int i = 0; i < count; i++) {
                JsonNode comment = objectMapper.readTree(res).get("comments").get(i);
                WeiboComment weiboComment = new WeiboComment();
                weiboComment.setCreatedAt(Date.parse(comment.get("created_at").asText()));
                weiboComment.setWeiboCommentId(comment.get("id").asLong());
                weiboComment.setText(comment.get("text").asText());
                weiboComment.setSource(comment.get("source").asText());
                weiboComment.setRootId(comment.get("rootid").asLong());

                JsonNode user = comment.get("user");
                WeiboUser weiboUser = new WeiboUser();
                weiboUser.setWeiboId(user.get("id").asLong());
                weiboUser.setWeiboName(user.get("screen_name").asText());
                weiboUser.setWeiboAvatar(user.get("avatar_hd").asText());
                weiboComment.setWeiboUser(weiboUser);
                // 来自哪条微博
                JsonNode status = comment.get("status");
                WeiboContent weiboContent = new WeiboContent();
                weiboContent.setCreatedAt(Date.parse(status.get("created_at").asText()));
                weiboContent.setWeiboContentId(status.get("id").asLong());
                weiboContent.setText(status.get("text").asText());
                weiboContent.setSource(status.get("source").asText());
                weiboContent.setRepostsCount(status.get("reposts_count").asLong());
                weiboContent.setCommentsCount(status.get("comments_count").asLong());
                weiboContent.setAttitudesCount(status.get("attitudes_count").asLong());
                weiboContent.setPicUrls(new ArrayList<>());
                weiboComment.setWeiboContent(weiboContent);

                user = status.get("user");
                weiboUser = new WeiboUser();
                weiboUser.setWeiboId(user.get("id").asLong());
                weiboUser.setWeiboName(user.get("screen_name").asText());
                weiboUser.setWeiboAvatar(user.get("avatar_hd").asText());
                weiboContent.setWeiboUser(weiboUser);
                // 加入微博
                weiboComment.setWeiboContent(weiboContent);

                // 回复哪条评论
                if (comment.has("reply_comment")) {
                    comment = comment.get("reply_comment");
                    WeiboComment replyComment = new WeiboComment();
                    replyComment.setCreatedAt(Date.parse(comment.get("created_at").asText()));
                    replyComment.setWeiboCommentId(comment.get("id").asLong());
                    replyComment.setText(comment.get("text").asText());
                    replyComment.setRootId(comment.get("rootid").asLong());

                    user = comment.get("user");
                    weiboUser = new WeiboUser();
                    weiboUser.setWeiboId(user.get("id").asLong());
                    weiboUser.setWeiboName(user.get("screen_name").asText());
                    weiboUser.setWeiboAvatar(user.get("avatar_hd").asText());
                    replyComment.setWeiboUser(weiboUser);
                    // 加入评论
                    weiboComment.setReplyComment(replyComment);
                }
                weiboCommentList.add(weiboComment);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(40000, "未知错误");
        }
        return weiboCommentList;
    }
}
