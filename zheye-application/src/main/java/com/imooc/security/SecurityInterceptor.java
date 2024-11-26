package com.imooc.security;

import cn.hutool.core.util.StrUtil;
import com.imooc.annotation.UserAuth;
import com.imooc.enums.BizCodeEnum;
import com.imooc.exception.BizException;
import com.imooc.properties.SecurityProperties;
import com.imooc.util.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录相关拦截器
 *
 * @author wzy
 * @date 2024/11/24 08:37
 */
@Slf4j
@Component
public class SecurityInterceptor implements HandlerInterceptor {

    @Resource
    private SecurityProperties securityProperties;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            // 放行预检请求
            return true;
        }
        if (handler instanceof HandlerMethod &&
                !hasUserAuthAnnotation((HandlerMethod) handler)) {
            return true;
        }
        String token = request.getHeader("token");
        if (StrUtil.isEmpty(token)) {
            throw new BizException(BizCodeEnum.USER_NOT_LOGIN);
        }
        Claims claims = JwtUtils.parseToken(token, securityProperties.getTokenExpired());
        if (claims == null) {
            throw new BizException(BizCodeEnum.USER_NOT_LOGIN);
        }
        return true;
    }

    private boolean hasUserAuthAnnotation(HandlerMethod handler) {
        if (handler.getMethod().getDeclaringClass().isAnnotationPresent(
                UserAuth.class
        )) {
            return true;
        }
        // 当前方法表示了@IgnoreResponseAdvice 注解，则不需要处理
        if (handler.getMethod().isAnnotationPresent(
                UserAuth.class
        )) {
            return true;
        }

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
