package com.imooc.handler;

import com.imooc.enums.BizCodeEnum;
import com.imooc.exception.BizException;
import com.imooc.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author wzy
 * @date 2024/11/20 09:35
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BizException.class)
    public CommonResponse handleBizException(BizException e) {
        e.printStackTrace();
        return CommonResponse.error(BizCodeEnum.valueOf(e.getCode()), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public CommonResponse handleException(Exception e) {
        e.printStackTrace();
        return CommonResponse.error(BizCodeEnum.INTERNAL_SERVER_ERROR, e.getMessage());
    }


}
