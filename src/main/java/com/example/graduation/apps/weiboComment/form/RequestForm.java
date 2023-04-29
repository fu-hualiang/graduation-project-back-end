package com.example.graduation.apps.weiboComment.form;

import lombok.Data;

@Data
public class RequestForm {
    String weiboToken;
    Long weiboId;
    Long commentId;
    String comment;
}
