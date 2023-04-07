package com.example.graduation.apps.weiboToken.controller;

import com.example.graduation.User;
import com.example.graduation.apps.weiboToken.dto.WeiboTokenDTO;
import com.example.graduation.apps.weiboToken.service.WeiboTokenService;
import com.example.graduation.exception.MyException;
import com.example.graduation.utils.resultUtils.Result;
import com.example.graduation.utils.resultUtils.ResultUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("weiboAuth")
public class WeiboTokenController {
    @Resource
    WeiboTokenService weiboTokenService;

    @GetMapping("{userId}/weiboTokens")
    public Result<List<WeiboTokenDTO>> findByUserId(@PathVariable("userId") Integer userId) throws MyException {
        return ResultUtil.success(weiboTokenService.findByUserId(userId));
    }

    @PostMapping("{userId}/weiboToken")
    public Result<String> add(@PathVariable("userId") Integer userId, String code) throws MyException {
        if (code == null) throw new MyException(400,"缺少code");
        return ResultUtil.success(weiboTokenService.add(userId, code));
    }
}
