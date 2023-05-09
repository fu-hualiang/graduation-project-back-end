package com.example.graduation.apps.spider.service;

import com.example.graduation.apps.spider.object.Account;
import com.example.graduation.apps.spider.object.Comment;
import com.example.graduation.apps.spider.object.Weibo;
import com.example.graduation.exception.MyException;

import java.util.List;

public interface SpiderService {
    Account crawlAccount(Long weiboId) throws MyException;

    List<Weibo> crawlWeibo(Long userId, Long page) throws MyException;

    List<Comment> crawlComment(String weiboId) throws MyException;
}
