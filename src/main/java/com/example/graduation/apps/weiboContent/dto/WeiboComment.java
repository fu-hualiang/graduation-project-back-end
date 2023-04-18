package com.example.graduation.apps.weiboContent.dto;

import com.example.graduation.apps.weiboUser.object.WeiboUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeiboComment {
    Long createdAt;
    Long weiboCommentId;
    String text;
    String source;
    WeiboUser weiboUser;
    Long rootId;
}
