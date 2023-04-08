package com.example.graduation.apps.weiboAuth.dto;

import lombok.Data;

@Data
public class WeiboTokenDTO {
    Long id;
    Long userId;
    Long weiboId;
    String weiboName;
    String weiboToken;
    Long createdTime;
    Integer status;
}
