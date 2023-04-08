package com.example.graduation.apps.user.dto;

import lombok.Data;

@Data
public class UserDTO {
    Long id;
    String name;
    String avatar;
    Long weiboId;
    String weiboToken;
}
