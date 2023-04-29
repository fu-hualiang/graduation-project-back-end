package com.example.graduation.apps.weiboComment.service;

import com.example.graduation.apps.weiboComment.object.WeiboComment;
import com.example.graduation.exception.MyException;

import java.util.List;

public interface WeiboCommentService {
    List<WeiboComment> findByWeiboContentId(String weiboToken, Long weiboContentId) throws MyException;

    List<WeiboComment> findMention(String weiboToken) throws MyException;

    Void create(String weiboToken, Long weiboId, Long commentId, String comment);
}
