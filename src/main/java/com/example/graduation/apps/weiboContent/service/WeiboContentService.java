package com.example.graduation.apps.weiboContent.service;

import com.example.graduation.apps.weiboContent.dto.WeiboComment;
import com.example.graduation.apps.weiboContent.dto.WeiboContent;
import com.example.graduation.exception.MyException;

import java.util.List;

public interface WeiboContentService {
    List<WeiboContent> find(String weiboToken) throws MyException;

    List<WeiboComment> findCommitByWeiboContentId(String weiboToken, Long weiboContentId) throws MyException;
}
