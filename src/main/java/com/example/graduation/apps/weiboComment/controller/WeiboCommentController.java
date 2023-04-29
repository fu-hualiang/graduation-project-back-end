package com.example.graduation.apps.weiboComment.controller;

import com.example.graduation.apps.weiboComment.form.RequestForm;
import com.example.graduation.apps.weiboComment.object.WeiboComment;
import com.example.graduation.apps.weiboComment.service.WeiboCommentService;
import com.example.graduation.exception.MyException;
import com.example.graduation.utils.resultUtils.Result;
import com.example.graduation.utils.resultUtils.ResultUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

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
     * 获取@用户的微博的评论
     */
    @GetMapping("mentions")
    public Result<List<WeiboComment>> findMention(String weiboToken) throws MyException {
        return ResultUtil.success(weiboCommentService.findMention(weiboToken));
    }

    /**
     * 添加评论
     */
    @PostMapping("")
    public Result<Void> create(@RequestBody RequestForm form) throws MyException {
        String weiboToken = form.getWeiboToken();
        Long weiboId = form.getWeiboId();
        Long commentId = form.getCommentId();
        String comment = form.getComment();
        return ResultUtil.success(weiboCommentService.create(weiboToken, weiboId, commentId, comment));
    }
}
