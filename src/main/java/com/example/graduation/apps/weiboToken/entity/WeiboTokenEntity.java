package com.example.graduation.apps.weiboToken.entity;

import lombok.Data;

import java.util.Date;

@Data
public class WeiboTokenEntity {
    Integer id;
    Integer userId;
    String weiboToken;
    Date createTime;
    Integer status;
}
