package com.example.graduation.apps.user.service;

import com.example.graduation.apps.user.dto.UserDTO;
import com.example.graduation.apps.user.form.LoginForm;
import com.example.graduation.exception.MyException;

public interface UserService {
    UserDTO login(LoginForm loginForm) throws MyException;
}
