package com.example.graduation.apps.weiboToken.dto;

import lombok.Data;

@Data
public class WeiboTokenDTO {
    Long weiboTokenId;
    Long userId;
    Long weiboId;
    String weiboName;
    String weiboToken;
    Long createdTime;
    Integer status;
    Integer deleted;
}
