package com.github.mouday.todolist.response;

import java.lang.annotation.*;

/**
 * 忽略返回值包装成统一格式
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface IgnoreJsonResponse {
}
