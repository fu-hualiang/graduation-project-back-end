package com.example.graduation.apps.weiboComment.object;

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

    WeiboContent weiboContent;
    WeiboComment replyComment;
}
