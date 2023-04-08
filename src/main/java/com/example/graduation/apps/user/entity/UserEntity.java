package com.example.graduation.apps.user.entity;

import lombok.Data;

@Data
public class UserEntity {
    Long id;
    String username;
    String password;
    String name;
    String avatar;
    Long weiboId;
    String weiboToken;
}
