package com.example.graduation.apps.weiboAuth.controller;

import com.example.graduation.apps.weiboAuth.service.WeiboAuthService;
import com.example.graduation.exception.MyException;
import com.example.graduation.utils.resultUtils.Result;
import com.example.graduation.utils.resultUtils.ResultUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("weiboAuth")
public class WeiboAuthController {
    @Resource
    WeiboAuthService weiboAuthService;

    @GetMapping("weiboToken")
    public Result<String> getWeiboToken(String code) throws MyException {
        if (code == null) throw new MyException(400,"缺少code");
        return ResultUtil.success(weiboAuthService.getWeiboToken(code));
    }
}
