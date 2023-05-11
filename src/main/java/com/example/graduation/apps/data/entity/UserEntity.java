package com.example.graduation.apps.data.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

@Data
public class UserEntity {
    Long id;
    Long weiboId;
    // 个人数据
    String gender;
    String location;
    String verified;
    String birthday;
    Long age;
    String constellation;
    String school;
    String company;
    // 微博数据
    Long weiboNum;
    Long following;
    Long followers;
    // 活动数据
    String devices;
    Long recentWeiboNum;
    Long createdAt;

    Integer deleted;

    @JsonGetter("weiboId")
    public Long getWeiboId() {
        return weiboId;
    }

    @JsonSetter("weibo_id")
    public void setWeiboId(Long weiboId) {
        this.weiboId = weiboId;
    }

    @JsonGetter("weiboNum")
    public Long getWeiboNum() {
        return weiboNum;
    }

    @JsonSetter("weibo_num")
    public void setWeiboNum(Long weiboNum) {
        this.weiboNum = weiboNum;
    }

    @JsonGetter("recentWeiboNum")
    public Long getRecentWeiboNum() {
        return recentWeiboNum;
    }

    @JsonSetter("recent_weibo_num")
    public void setRecentWeiboNum(Long recentWeiboNum) {
        this.recentWeiboNum = recentWeiboNum;
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
