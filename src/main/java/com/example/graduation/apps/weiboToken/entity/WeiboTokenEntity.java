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
    String weiboToken;
    Long createdTime;
    Integer status;
    Integer deleted;
}
