package com.github.mouday.todolist.exception;

import com.github.mouday.todolist.response.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理
 * 参考
 * https://www.jianshu.com/p/3998ea8b53a8
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonResult exceptionHandler(Throwable error) {
        log.error("GlobalExceptionHandler");
        return JsonResult.failure(error);
    }
}
