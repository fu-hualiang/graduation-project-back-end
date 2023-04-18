package com.example.graduation.apps.weiboUser.controller;

import com.example.graduation.apps.weiboUser.object.WeiboUser;
import com.example.graduation.apps.weiboUser.service.WeiboUserService;
import com.example.graduation.exception.MyException;
import com.example.graduation.utils.resultUtils.Result;
import com.example.graduation.utils.resultUtils.ResultUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("weiboUsers")
public class WeiboUserController {
    @Resource
    WeiboUserService weiboUserService;

    /**
     * 获取微博用户信息
     */
    @GetMapping("{weiboId}")
    public Result<WeiboUser> findByWeiboId(@PathVariable Long weiboId, String weiboToken) throws MyException {
        return ResultUtil.success(weiboUserService.findByWeiboId(weiboId,weiboToken));
    }
}
