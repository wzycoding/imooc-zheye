package com.imooc.advice;

import com.imooc.annotation.IgnoreResponseAdvice;
import com.imooc.enums.HttpStatusEnum;
import com.imooc.response.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * <h1>描述：对HTTP响应进行处理</h1>
 * Advice对某一方面功能进行增强，ResponseBodyAdvice，相当于对ResponseBody进行增强
 *
 * @author wzy
 * @date 2024-11-20 09:50:52
 **/
@RestControllerAdvice
@SuppressWarnings("all")
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {
    /**
     * 判断是否需要对响应进行处理
     *
     * @param methodParameter controller方法定义
     * @param aClass
     * @return 是否需要处理
     */
    @Override
    public boolean supports(MethodParameter methodParameter,
                            Class<? extends HttpMessageConverter<?>> aClass) {
        // 如果当前方法所在的类标识了@IgnoreResponseAdvice 注解，则不需要处理
        if (methodParameter.getDeclaringClass().isAnnotationPresent(
                IgnoreResponseAdvice.class
        )) {
            return false;
        }
        // 当前方法表示了@IgnoreResponseAdvice 注解，则不需要处理
        if (methodParameter.getMethod().isAnnotationPresent(
                IgnoreResponseAdvice.class
        )) {
            return false;
        }
        // 对响应进行处理， 执行beforeBodyWrite方法
        return true;
    }

    /**
     * <h2>响应返回之前的处理</h2>
     * 这里如果接口返回String会报错，后面排查下
     *
     * @param o                  Controller返回对象
     * @param methodParameter
     * @param mediaType
     * @param aClass
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object o,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        CommonResponse<Object> response = new CommonResponse<>(HttpStatusEnum.OK.getCode(), "");

        // 如果o是null，response不需要设置data
        if (o == null) {
            return response;
        } else if (o instanceof CommonResponse) {
            // 如果已经是CommonResponse，不需要再次处理
            response = (CommonResponse<Object>) o;
        } else {
            // 否则把响应对象作为CommonResponse的data部分
            response.setData(o);
        }
        return response;
    }
}
