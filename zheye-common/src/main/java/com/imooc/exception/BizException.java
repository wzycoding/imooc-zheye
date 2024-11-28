package com.imooc.exception;

import com.imooc.enums.BizCodeEnum;
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

    public BizException(BizCodeEnum bizCode) {
        this.message = bizCode.getDescription();
        this.code = bizCode.getCode();
    }

    public BizException(BizCodeEnum bizCode, String optionMessage) {
        this.message = optionMessage;
        this.code = bizCode.getCode();
    }
}
