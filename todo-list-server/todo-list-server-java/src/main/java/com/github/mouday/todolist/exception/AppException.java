package com.github.mouday.todolist.exception;

import com.github.mouday.todolist.response.ResultCodeEnum;

/**
 * 自定义业务异常
 */
public class AppException extends RuntimeException {
    private ResultCodeEnum resultCodeEnum;

    public AppException(ResultCodeEnum resultCodeEnum) {
        super();
        this.resultCodeEnum = resultCodeEnum;
    }

    public ResultCodeEnum getResultCodeEnum() {
        return resultCodeEnum;
    }
}
