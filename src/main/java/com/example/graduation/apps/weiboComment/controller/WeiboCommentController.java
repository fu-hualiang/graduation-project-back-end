package com.example.graduation.apps.weiboComment.controller;

import com.example.graduation.apps.weiboComment.object.WeiboComment;
import com.example.graduation.apps.weiboComment.service.WeiboCommentService;
import com.example.graduation.exception.MyException;
import com.example.graduation.utils.resultUtils.Result;
import com.example.graduation.utils.resultUtils.ResultUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("weiboComments")
public class WeiboCommentController {
    @Resource
    WeiboCommentService weiboCommentService;

    /**
     * 获取用户的微博的评论
     */
    @GetMapping("")
    public Result<List<WeiboComment>> findByWeiboContentId(String weiboToken, Long weiboContentId) throws MyException {
        return ResultUtil.success(weiboCommentService.findByWeiboContentId(weiboToken, weiboContentId));
    }

    /**
     * 获取用户的微博的评论
     */
    @GetMapping("mentions")
    public Result<List<WeiboComment>> findMention(String weiboToken) throws MyException {
        return ResultUtil.success(weiboCommentService.findMention(weiboToken));
    }
}
