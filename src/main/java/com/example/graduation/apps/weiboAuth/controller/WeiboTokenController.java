package com.example.graduation.apps.weiboAuth.controller;

import com.example.graduation.apps.weiboAuth.dto.WeiboTokenDTO;
import com.example.graduation.apps.weiboAuth.service.WeiboTokenService;
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

    /**
     * 获取一个用户的所有 token
     */
    @GetMapping("{userId}/weiboTokens")
    public Result<List<WeiboTokenDTO>> findByUserId(@PathVariable("userId") Long userId){
        return ResultUtil.success(weiboTokenService.findByUserId(userId));
    }

    /**
     * 通过 code 为一个用户添加 token
     */
    @PostMapping("{userId}/weiboToken")
    public Result<WeiboTokenDTO> add(@PathVariable("userId") Long userId, String code) throws MyException {
        return ResultUtil.success(weiboTokenService.add(userId, code));
    }
}
