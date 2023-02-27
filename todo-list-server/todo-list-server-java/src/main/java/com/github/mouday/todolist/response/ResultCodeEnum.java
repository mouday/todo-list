package com.github.mouday.todolist.response;

/**
 * 状态码枚举
 * 看看人家那后端API接口写得，那叫一个优雅！
 * https://mp.weixin.qq.com/s/MUYyBciJvlX7XGgvw5j7Kg
 */
public enum ResultCodeEnum {
    // 成功状态码
    SUCCESS(0, "成功"),

    // 未知错误
    ERROR(1, "未知错误"),

    // 1000～1999 区间表示参数错误
    PARAM_IS_INVALID(1001, "参数无效"),
    PARAM_IS_BLANK(1002, "参数为空"),
    PARAM_TYPE_BIND_ERROR(1003, "参数类型错误"),
    PARAM_NOT_COMPLETE(1004, "参数缺失"),

    // 2000～2999 区间表示用户错误
    USER_NOT_LOGGED_IN(2001, "用户未登录"),
    USER_LOGIN_ERROR(2002, "账号不存在或密码错误"),
    USER_ACCOUNT_FORBIDDEN(2003, "账号已被禁用"),
    USER_NOT_EXIST(2004, "账号不存在"),
    USER_HAS_EXISTED(2005, "账号已存在"),

    // 3000～3999 区间表示接口异常
    INTERFACE_NOT_EXIST(3001, "接口不存在")
    ;

    private Integer code;
    private String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
