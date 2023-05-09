package com.example.graduation.apps.spider.object;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

@Data
public class Comment {
    Long userId;
    String weiboId;
    Long commentId;
    String username;
    // 评论内容
    String text;
    String html;
    // 微博数据
    Long likeNum;
    // 微博状态
    Long createdAt;
    String location;
    @JsonGetter("userId")
    public Long getUserId() {
        return userId;
    }
    @JsonSetter("user_id")
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    @JsonGetter("weiboId")
    public String getWeiboId() {
        return weiboId;
    }
    @JsonSetter("weibo_id")
    public void setWeiboId(String weiboId) {
        this.weiboId = weiboId;
    }
    @JsonGetter("commentId")
    public Long getCommentId() {
        return commentId;
    }
    @JsonSetter("comment_id")
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
    @JsonGetter("likeNum")
    public Long getLikeNum() {
        return likeNum;
    }
    @JsonSetter("like_num")
    public void setLikeNum(Long likeNum) {
        this.likeNum = likeNum;
    }
    @JsonGetter("createdAt")
    public Long getCreatedAt() {
        return createdAt;
    }
    @JsonSetter("created_at")
    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }
}
