package com.example.graduation.apps.material.form;

import lombok.Data;

@Data
public class RequestForm {
    Long userId;
    Integer type;
    Long categoryId;
    String text;
}
