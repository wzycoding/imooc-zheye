package com.imooc.configuration;

import com.imooc.security.SecurityInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author wzy
 * @date 2024/11/24 09:01
 */
@Slf4j
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Resource
    private SecurityInterceptor securityInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(securityInterceptor);
        registration.addPathPatterns("/**"); //所有路径都被拦截
        // 添加不拦截路径
        registration.excludePathPatterns(
                "/login",
                "/**/*.html",
                "/**/*.js",
                "/**/*.css"
        );
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 添加跨域配置，所有接口都支持跨域
        registry.addMapping("/**")
                // 是否发送 Cookie
                .allowCredentials(true)
                // 支持域
                .allowedOriginPatterns("*")
                // 支持方法
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
                .allowedHeaders("*")
                .exposedHeaders("*");
    }
}
