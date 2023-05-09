package com.example.graduation.apps.spider.controller;

import com.example.graduation.apps.spider.object.Account;
import com.example.graduation.apps.spider.object.Comment;
import com.example.graduation.apps.spider.object.Weibo;
import com.example.graduation.apps.spider.service.SpiderService;
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
@RequestMapping("spider")
public class SpiderController {
    @Resource
    SpiderService spiderService;

    /**
     * 爬取某个账户的信息
     */
    @GetMapping("accounts/{weiboId}")
    public Result<Account> crawlAccount(@PathVariable Long weiboId) throws MyException {
        return ResultUtil.success(spiderService.crawlAccount(weiboId));
    }

    @GetMapping("weibo")
    public Result<List<Weibo>> crawlWeibo(Long userId, Long page) throws MyException {
        return ResultUtil.success(spiderService.crawlWeibo(userId, page));
    }

    @GetMapping("weibo/{weiboId}/comments")
    public Result<List<Comment>> crawlComment(@PathVariable String weiboId) throws MyException {
        return ResultUtil.success(spiderService.crawlComment(weiboId));
    }
}
