package com.imooc.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wzy
 * @date 2024/11/24 08:49
 */
@Data
@Component
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {
    /**
     * token有效时间
     */
    private Long tokenExpired = 24L * 60L * 60L;

    /**
     * 允许客户端和服务端时间误差范围
     */
    private Long allowedClockSkewSeconds = 30L;

}
