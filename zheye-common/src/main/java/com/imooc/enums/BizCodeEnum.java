package com.imooc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * Http状态码
 *
 * @author wzy
 * @date 2024-11-20 09:20:04
 */
@Getter
@AllArgsConstructor
public enum BizCodeEnum {

    /**
     * 通用异常响应
     */
    OK(200, "请求成功"),
    INTERNAL_SERVER_ERROR(500, "服务器开小差了请稍后再试"),
    PARAM_ERROR(400, "请求参数错误"),
    FORBIDDEN(403, "请求资源禁止被访问"),
    UNAUTHORIZED(401, "请求缺少权限"),

    /**
     * 用户业务码10xxx
     * 其他模块：11xxx
     */
    USER_NOT_LOGIN(10001, "未登录");

    /**
     * http状态码
     */
    private final Integer code;
    private final String description;

    public static BizCodeEnum valueOf(Integer code) {
        return Arrays.stream(BizCodeEnum.values()).filter(bizCode -> bizCode.getCode().equals(code)).findAny()
                .orElseThrow(() -> {
                    throw new RuntimeException();
                });
    }
}
