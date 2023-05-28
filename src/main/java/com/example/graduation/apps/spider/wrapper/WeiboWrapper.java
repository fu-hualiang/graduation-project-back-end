package com.example.graduation.apps.spider.wrapper;

import com.example.graduation.apps.spider.object.Comment;
import com.example.graduation.apps.spider.object.Weibo;
import lombok.Data;

import java.util.List;

@Data
public class WeiboWrapper {
    List<Weibo> weiboList;
    Long total;
}
