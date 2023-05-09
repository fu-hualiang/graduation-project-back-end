package com.example.graduation.apps.data.entity;

import lombok.Data;

@Data
public class UserEntity {
    Long id;
    Long weiboId;
    // 个人数据
    String gender;
    String location;
    String verified;
    Long birthday;
    Long age;
    String constellation;
    String school;
    String company;
    // 微博数据
    Long weiboNum;
    Long following;
    Long followers;
    // 活动数据
    String devices;
    Long recentWeiboNum;
    Long createdAt;

    Integer deleted;
}
