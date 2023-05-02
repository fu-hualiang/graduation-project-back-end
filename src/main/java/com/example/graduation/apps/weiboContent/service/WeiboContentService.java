package com.example.graduation.apps.weiboContent.service;

import com.example.graduation.apps.weiboContent.object.WeiboContent;
import com.example.graduation.exception.MyException;

import java.util.List;

public interface WeiboContentService {
    List<WeiboContent> find(String weiboToken) throws MyException;

    List<WeiboContent> findMention(String weiboToken) throws MyException;

    Void create(String weiboToken, String status, String picture,String path);
}
