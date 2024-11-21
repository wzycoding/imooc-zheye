package com.imooc.handler;

import com.imooc.enums.HttpStatusEnum;
import com.imooc.response.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author wzy
 * @date 2024/11/20 09:35
 */
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public CommonResponse handleException(Exception e) {
        return CommonResponse.error(HttpStatusEnum.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
