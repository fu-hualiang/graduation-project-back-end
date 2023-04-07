package com.example.graduation.apps.weiboToken.dto;

import lombok.Data;

import java.util.Date;

@Data
public class WeiboTokenDTO {
    Integer id;
    Integer userId;
    String weiboToken;
    Date createTime;
    Integer status;
}
