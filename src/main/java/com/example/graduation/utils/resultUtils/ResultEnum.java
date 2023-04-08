package com.example.graduation.utils.resultUtils;

public enum ResultEnum {
    /**
     * 成功
     */
    SUCCESS(20000),
    /**
     * 失败
     */
    FAIL(40000),
    /**
     * 接口不存在
     */
    NOT_FOUND(404),
    /**
     * 服务器内部错误
     */
    INTERNAL_SERVER_ERROR(500);
    public final int code;

    ResultEnum(int code) {
        this.code = code;
    }
}
