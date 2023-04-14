package com.example.graduation.apps.log.service;

import com.example.graduation.apps.log.dto.TokenDTO;
import com.example.graduation.apps.log.form.LoginForm;
import com.example.graduation.exception.MyException;

public interface LogService {
    TokenDTO login(LoginForm loginForm) throws MyException;
}
