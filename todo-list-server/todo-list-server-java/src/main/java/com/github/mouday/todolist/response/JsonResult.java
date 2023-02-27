package com.github.mouday.todolist.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.mouday.todolist.exception.AppException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * 统一数据返回
 *
 * @param <T>
 */
@Data
@Slf4j
public class JsonResult<T> implements Serializable {
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 信息描述
     */
    @JsonProperty("msg")
    private String message;

    /**
     * 数据
     */
    private T data;

    public JsonResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> JsonResult<T> success(T data) {
        return new JsonResult<>(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(), data);
    }

    public static <T> JsonResult<T> failure(Integer code, String message) {
        return new JsonResult<>(code, message, null);
    }

    public static <T> JsonResult<T> failure(ResultCodeEnum resultCodeEnum) {
        return JsonResult.failure(resultCodeEnum.getCode(), resultCodeEnum.getMessage());
    }

    public static <T> JsonResult<T> failure(Throwable error) {

        System.out.println(error);

        Integer code = ResultCodeEnum.ERROR.getCode();
        String message = ResultCodeEnum.ERROR.getMessage();

        if (error instanceof AppException) {
            code = ((AppException) error).getResultCodeEnum().getCode();
            message = ((AppException) error).getResultCodeEnum().getMessage();
        } else if (error != null) {
            message = error.getMessage();
            message = message.substring(message.lastIndexOf(":") + 1).trim();
        }

        return JsonResult.failure(code, message);
    }
}
