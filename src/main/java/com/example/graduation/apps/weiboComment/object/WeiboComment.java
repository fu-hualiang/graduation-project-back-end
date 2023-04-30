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
    Long rootId;
    String text;
    String source;
    Long likeCount;
    Long replyCount;

    WeiboUser weiboUser;
    WeiboContent weiboContent;
    WeiboComment replyComment;
}
