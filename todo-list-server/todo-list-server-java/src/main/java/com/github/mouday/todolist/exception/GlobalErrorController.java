package com.github.mouday.todolist.exception;

import com.github.mouday.todolist.response.JsonResult;
import com.github.mouday.todolist.response.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理404异常
 */
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
@Slf4j
public class GlobalErrorController implements ErrorController {

    /**
     * html 错误
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public void errorHtml(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.error((String) request.getAttribute(WebUtils.ERROR_REQUEST_URI_ATTRIBUTE));

        log.info(request.getRequestURI());
        response.getWriter().write("<h2>404 not found<h2>");
    }

    /**
     * json 错误
     *
     * @param request
     * @return
     */
    @RequestMapping
    @ResponseBody
    public JsonResult errorJson(HttpServletRequest request, HttpServletResponse response) {
        log.error("GlobalErrorController");
        log.error((String) request.getAttribute(WebUtils.ERROR_REQUEST_URI_ATTRIBUTE));

        HttpStatus httpStatus = HttpStatus.valueOf(response.getStatus());

        if (httpStatus == HttpStatus.NOT_FOUND) {
            return JsonResult.failure(ResultCodeEnum.INTERFACE_NOT_EXIST);
        } else {
            Throwable error = (Throwable) request.getAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE);
            return JsonResult.failure(error);
        }
    }
}
