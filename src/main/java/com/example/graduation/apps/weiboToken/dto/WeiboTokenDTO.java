package com.example.graduation.apps.weiboToken.dto;

import lombok.Data;

@Data
public class WeiboTokenDTO {
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
