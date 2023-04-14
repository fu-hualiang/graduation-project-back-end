package com.example.graduation.apps.user.service;

import com.example.graduation.apps.user.dto.UserDTO;

public interface UserService {

    UserDTO findByUserId(Long userId);
}
