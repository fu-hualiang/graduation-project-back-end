package com.example.graduation.apps.weiboAuth.entity;

import lombok.Data;

@Data
public class WeiboTokenEntity {
    Long id;
    Long userId;
    Long weiboId;
    String weiboName;
    String weiboToken;
    Long createdTime;
    Integer status;
}
