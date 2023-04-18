package com.example.graduation.apps.weiboContent.controller;

import com.example.graduation.apps.weiboContent.dto.WeiboComment;
import com.example.graduation.apps.weiboContent.dto.WeiboContent;
import com.example.graduation.apps.weiboContent.service.WeiboContentService;
import com.example.graduation.exception.MyException;
import com.example.graduation.utils.resultUtils.Result;
import com.example.graduation.utils.resultUtils.ResultUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("weiboContents")
public class WeiboContentController {
    @Resource
    WeiboContentService weiboContentService;

    /**
     * 获取用户的微博（最多 5 条）
     */
    @GetMapping("")
    public Result<List<WeiboContent>> find(String weiboToken) throws MyException {
        return ResultUtil.success(weiboContentService.find(weiboToken));
    }
    /**
     * 获取用户的微博的评论
     */
    @GetMapping("{weiboContentId}/weiboComments")
    public Result<List<WeiboComment>> findCommitByWeiboContentId(String weiboToken, @PathVariable Long weiboContentId) throws MyException {
        return ResultUtil.success(weiboContentService.findCommitByWeiboContentId(weiboToken,weiboContentId));
    }
}
