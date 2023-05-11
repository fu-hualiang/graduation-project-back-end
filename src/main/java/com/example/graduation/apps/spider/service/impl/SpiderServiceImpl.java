package com.example.graduation.apps.spider.service.impl;

import com.example.graduation.apps.spider.object.Account;
import com.example.graduation.apps.spider.object.Comment;
import com.example.graduation.apps.spider.object.Weibo;
import com.example.graduation.apps.spider.service.SpiderService;
import com.example.graduation.exception.MyException;
import com.example.graduation.utils.PythonScriptUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("spiderService")
public class SpiderServiceImpl implements SpiderService {
    @Value("${python.path}")
    private String pythonPath;

    @Value("${python.script.path}")
    private String pythonScriptPath;

    @Resource
    ObjectMapper objectMapper;

    @Override
    public Account crawlAccount(Long weiboId) throws MyException {
        String[] args = new String[]{pythonPath, pythonScriptPath + "account_starter.py", String.valueOf(weiboId)};
        Account account;
        try {
            String res = PythonScriptUtils.run(args).get(0);
            account = objectMapper.readValue(res, Account.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(40000, "未知错误");
        }
        return account;
    }

    @Override
    public List<Weibo> crawlWeibo(Long userId, Long page) throws MyException {
        String[] args = new String[]{pythonPath, pythonScriptPath + "weibo_starter.py", String.valueOf(userId), String.valueOf(page)};
        List<Weibo> weiboList = new ArrayList<>();
        try {
            List<String> weiboStringList = PythonScriptUtils.run(args);
            for (String weiboString : weiboStringList) {
                Weibo weibo = objectMapper.readValue(weiboString, Weibo.class);
                weiboList.add(weibo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(40000, "未知错误");
        }
        return weiboList;
    }

    @Override
    public List<Comment> crawlComment(String weiboId) throws MyException {
        String[] args = new String[]{pythonPath, pythonScriptPath + "comment_starter.py", weiboId};
        List<Comment> commentList = new ArrayList<>();
        try {
            List<String> commentStringList = PythonScriptUtils.run(args);
            for (String commentString : commentStringList) {
                Comment comment = objectMapper.readValue(commentString, Comment.class);
                commentList.add(comment);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(40000, "未知错误");
        }
        return commentList;
    }
}
