package com.example.graduation.task.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

@Data
public class Follower {
    Long id;
    Long weiboId;
    Long followerId;
    Integer deleted;

    @JsonGetter("weiboId")
    public Long getWeiboId() {
        return weiboId;
    }
    @JsonSetter("weibo_id")
    public void setWeiboId(Long weiboId) {
        this.weiboId = weiboId;
    }
    @JsonGetter("followerId")
    public Long getFollowerId() {
        return followerId;
    }
    @JsonSetter("follower_id")
    public void setFollowerId(Long followerId) {
        this.followerId = followerId;
    }
}
