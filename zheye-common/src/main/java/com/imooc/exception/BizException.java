package com.imooc.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * TODO：errorCode和errorMsg报个null的错误，后续排查下
 *
 * @author wzy
 * @date 2024/11/22 17:45
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BizException extends RuntimeException {
    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误提示
     */
    private String message;

    public BizException(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

}
