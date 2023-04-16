package com.example.graduation.apps.weiboContent.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeiboContentDTO {
    Long createdAt;
    Long weiboContentId;
    String text;
    String source;
    Long repostsCount;
    Long commentsCount;
    Long attitudesCount;
    List<String> picUrls;
}
