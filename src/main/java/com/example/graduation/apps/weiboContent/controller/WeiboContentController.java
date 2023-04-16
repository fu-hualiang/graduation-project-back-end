package com.example.graduation.apps.weiboContent.controller;

import com.example.graduation.apps.weiboContent.dto.WeiboContentDTO;
import com.example.graduation.apps.weiboContent.service.WeiboContentService;
import com.example.graduation.utils.resultUtils.Result;
import com.example.graduation.utils.resultUtils.ResultUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("weiboContents")
public class WeiboContentController {
    @Resource
    WeiboContentService weiboContentService;

    /**
     * 分页获取用户的微博
     */
    @GetMapping("")
    public Result<List<WeiboContentDTO>> findByUserId(String weiboToken, Long userId, Integer count, Integer page) {
        return ResultUtil.success(weiboContentService.findByUserId(weiboToken, userId, count, page));
    }
}
