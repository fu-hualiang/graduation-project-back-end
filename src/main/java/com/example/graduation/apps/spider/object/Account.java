package com.example.graduation.apps.spider.object;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

@Data
public class Account {
    Long id;
    Long weiboId;

    Long weiboNum;
    Long following;
    Long followers;

    Long influence;
    Long coverageDegree;
    Long activityDegree;
    Long propagationPower;

    Long totalLikeNum;
    Long totalRepostNum;
    Long totalCommentNum;

    Long createdAt;

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

    @JsonGetter("coverageDegree")
    public Long getCoverageDegree() {
        return coverageDegree;
    }

    @JsonSetter("coverage_degree")
    public void setCoverageDegree(Long coverageDegree) {
        this.coverageDegree = coverageDegree;
    }

    @JsonGetter("activityDegree")
    public Long getActivityDegree() {
        return activityDegree;
    }

    @JsonSetter("activity_degree")
    public void setActivityDegree(Long activityDegree) {
        this.activityDegree = activityDegree;
    }

    @JsonGetter("propagationPower")
    public Long getPropagationPower() {
        return propagationPower;
    }

    @JsonSetter("propagation_power")
    public void setPropagationPower(Long propagationPower) {
        this.propagationPower = propagationPower;
    }
    @JsonGetter("totalLikeNum")
    public Long getTotalLikeNum() {
        return totalLikeNum;
    }
    @JsonSetter("total_like_num")
    public void setTotalLikeNum(Long totalLikeNum) {
        this.totalLikeNum = totalLikeNum;
    }
    @JsonGetter("totalRepostNum")
    public Long getTotalRepostNum() {
        return totalRepostNum;
    }
    @JsonSetter("total_repost_num")
    public void setTotalRepostNum(Long totalRepostNum) {
        this.totalRepostNum = totalRepostNum;
    }
    @JsonGetter("totalCommentNum")
    public Long getTotalCommentNum() {
        return totalCommentNum;
    }
    @JsonSetter("total_comment_num")
    public void setTotalCommentNum(Long totalCommentNum) {
        this.totalCommentNum = totalCommentNum;
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
