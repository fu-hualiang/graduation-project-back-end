package com.example.graduation.apps.spider.service.impl;

import com.example.graduation.apps.spider.object.Account;
import com.example.graduation.apps.spider.object.Comment;
import com.example.graduation.apps.spider.object.Weibo;
import com.example.graduation.apps.spider.service.SpiderService;
import com.example.graduation.apps.spider.wrapper.WeiboWrapper;
import com.example.graduation.exception.MyException;
import com.example.graduation.utils.PythonScriptUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
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
    public WeiboWrapper crawlWeibo(Long userId, Long page) throws MyException {
        String[] args = new String[]{pythonPath, pythonScriptPath + "weibo_starter.py", String.valueOf(userId), String.valueOf(page)};
        WeiboWrapper weiboWrapper = new WeiboWrapper();
        List<Weibo> weiboList = new ArrayList<>();
        try {
            List<String> weiboStringList = PythonScriptUtils.run(args);
            Long total = Long.valueOf(weiboStringList.get(0));
            weiboWrapper.setTotal(total);
            weiboStringList.remove(0);
            for (String weiboString : weiboStringList) {
                Weibo weibo = objectMapper.readValue(weiboString, Weibo.class);
                weiboList.add(weibo);
            }
            weiboWrapper.setWeiboList(weiboList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(40000, "未知错误");
        }
        return weiboWrapper;
    }

    @Override
    public List<Comment> crawlComment(String weiboId, String wordCloudPath) throws MyException {
        // 检查目录
        File dir = new File(wordCloudPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String[] args = new String[]{pythonPath, pythonScriptPath + "comment_starter.py", weiboId, wordCloudPath + weiboId + ".png"};
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
