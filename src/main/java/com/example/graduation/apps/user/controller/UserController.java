package com.example.graduation.apps.user.controller;

import com.example.graduation.apps.user.dto.TokenDTO;
import com.example.graduation.apps.user.dto.UserDTO;
import com.example.graduation.apps.user.form.LoginForm;
import com.example.graduation.apps.user.service.UserService;
import com.example.graduation.exception.MyException;
import com.example.graduation.utils.resultUtils.Result;
import com.example.graduation.utils.resultUtils.ResultUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    @Resource
    UserService userService;

    /**
     * 登录,成功则返回token
     */
    @PostMapping("login")
    public Result<TokenDTO> login(@RequestBody LoginForm loginForm) throws MyException {
        return ResultUtil.success(userService.login(loginForm));
    }

    @GetMapping("{userId}")
    public Result<UserDTO> findByUserId(@PathVariable Long userId, HttpServletRequest request) throws MyException {
        return ResultUtil.success(userService.findByUserId(userId));
    }
}
