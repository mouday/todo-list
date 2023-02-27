package com.github.mouday.todolist.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 返回值拦截处理
 * <p>
 * 参考：
 * springboot 后端统一返回数据格式，异常统一处理（全局异常处理器）
 * https://www.cnblogs.com/ds521/p/16262706.html
 */
@RestControllerAdvice
@Slf4j
public class JsonResponseHandler implements ResponseBodyAdvice<Object> {
    /**
     * 判断是否存在忽略包装注解
     *
     * @param returnType
     * @param converterType
     * @return
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return !returnType.hasMethodAnnotation(IgnoreJsonResponse.class);
    }

    /**
     * 包装返回值
     *
     * @param body
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // log.info(body.toString());
        // System.out.println(body.toString());
        // System.out.println(body.getClass());

        JsonResult jsonResult;

        if (body instanceof JsonResult) {
            jsonResult = (JsonResult) body;
        } else if (body instanceof Throwable) {
            jsonResult = JsonResult.failure((Throwable) body);
        } else {
            jsonResult = JsonResult.success(body);
        }

        return jsonResult;
    }
}
