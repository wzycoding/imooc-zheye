package com.imooc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Http状态码
 *
 * @author wzy
 * @date 2024-11-20 09:20:04
 */
@Getter
@AllArgsConstructor
public enum HttpStatusEnum {
    OK(200, "请求成功"),
    NOT_FOUND(404, "请求的资源不存在"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    FORBIDDEN(403, "请求资源禁止被访问"),
    UNAUTHORIZED(401, "请求缺少权限");
    /**
     * http状态码
     */
    private final Integer code;
    private final String description;
}
