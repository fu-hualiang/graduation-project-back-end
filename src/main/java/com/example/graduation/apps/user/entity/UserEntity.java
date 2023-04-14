package com.example.graduation.apps.user.entity;

import lombok.Data;

@Data
public class UserEntity {
    Long userId;
    String username;
    String password;
    String name;
    String avatar;
}
