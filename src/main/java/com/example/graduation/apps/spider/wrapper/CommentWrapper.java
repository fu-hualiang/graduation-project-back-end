package com.example.graduation.apps.spider.wrapper;

import com.example.graduation.apps.spider.object.Comment;
import lombok.Data;

import java.util.List;

@Data
public class CommentWrapper {
    List<Comment> commentList;
    String wordCloud;
}
