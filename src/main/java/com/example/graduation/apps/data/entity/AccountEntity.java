package com.example.graduation.apps.data.entity;

import lombok.Data;

@Data
public class AccountEntity {
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
}
