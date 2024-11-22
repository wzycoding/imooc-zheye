package com.imooc.handler;

import com.imooc.enums.HttpStatusEnum;
import com.imooc.exception.BizException;
import com.imooc.response.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author wzy
 * @date 2024/11/20 09:35
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BizException.class)
    public CommonResponse handleBizException(BizException e) {
        return CommonResponse.error(HttpStatusEnum.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public CommonResponse handleException(Exception e) {
        return CommonResponse.error(HttpStatusEnum.INTERNAL_SERVER_ERROR, e.getMessage());
    }


}
