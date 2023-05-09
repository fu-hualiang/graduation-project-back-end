package com.example.graduation.apps.spider.object;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

import java.util.List;

@Data
public class Weibo {
    Long userId;
    String weiboId;
    // 微博内容
    String text;
    List<String> pictureList;
    // 微博数据
    Long likeNum;
    Long repostNum;
    Long commentNum;
    // 微博状态
    Long createdAt;
    String source;
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
    @JsonGetter("pictureList")
    public List<String> getPictureList() {
        return pictureList;
    }
    @JsonSetter("picture_list")
    public void setPictureList(List<String> pictureList) {
        this.pictureList = pictureList;
    }
    @JsonGetter("likeNum")
    public Long getLikeNum() {
        return likeNum;
    }
    @JsonSetter("like_num")
    public void setLikeNum(Long likeNum) {
        this.likeNum = likeNum;
    }
    @JsonGetter("repostNum")
    public Long getRepostNum() {
        return repostNum;
    }
    @JsonSetter("repost_num")
    public void setRepostNum(Long repostNum) {
        this.repostNum = repostNum;
    }
    @JsonGetter("commentNum")
    public Long getCommentNum() {
        return commentNum;
    }
    @JsonSetter("comment_num")
    public void setCommentNum(Long commentNum) {
        this.commentNum = commentNum;
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
