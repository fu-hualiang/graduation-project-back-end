package com.example.graduation.apps.user.service;

import com.example.graduation.apps.user.dto.TokenDTO;
import com.example.graduation.apps.user.dto.UserDTO;
import com.example.graduation.apps.user.form.LoginForm;
import com.example.graduation.exception.MyException;

public interface UserService {
    TokenDTO login(LoginForm loginForm) throws MyException;

    UserDTO findByUserId(Long userId);
}
