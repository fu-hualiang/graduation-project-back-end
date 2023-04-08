package com.example.graduation.apps.user.controller;

import com.example.graduation.apps.user.dto.UserDTO;
import com.example.graduation.apps.user.form.LoginForm;
import com.example.graduation.apps.user.service.UserService;
import com.example.graduation.exception.MyException;
import com.example.graduation.utils.resultUtils.Result;
import com.example.graduation.utils.resultUtils.ResultUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("users")
public class UserController {
    @Resource
    UserService userService;

    /**
     * 登录
     */
    @PostMapping("login")
    public Result<UserDTO> login(LoginForm loginForm) throws MyException {
        return ResultUtil.success(userService.login(loginForm));
    }
}
