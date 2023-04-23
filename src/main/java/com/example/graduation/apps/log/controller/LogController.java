package com.example.graduation.apps.log.controller;

import com.example.graduation.apps.log.service.LogService;
import com.example.graduation.apps.log.dto.TokenDTO;
import com.example.graduation.apps.log.form.LoginForm;
import com.example.graduation.exception.MyException;
import com.example.graduation.utils.resultUtils.Result;
import com.example.graduation.utils.resultUtils.ResultUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {
    @Resource
    LogService logService;

    /**
     * 登录,成功则返回token
     */
    @PostMapping("login")
    public Result<TokenDTO> login(@RequestBody LoginForm loginForm) throws MyException {
        return ResultUtil.success(logService.login(loginForm));
    }
}
