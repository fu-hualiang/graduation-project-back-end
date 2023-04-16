package com.example.graduation.apps.weiboToken.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeiboTokenEntity {
    Long weiboTokenId;
    Long userId;
    Long weiboId;
    String weiboName;
    String weiboAvatar;
    String weiboToken;
    Long createdAt;
    Integer status;
    Integer deleted;
}
