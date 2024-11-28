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
@ConfigurationProperties(prefix = "user")
public class UserProperties {
    /**
     * 用户默认头像图片id
     */
    private Long userDefaultHeadImageId = 3L;

    /**
     * 专栏默认描述
     */
    private String columnDefaultDescription = "这个人很懒什么都没有留下...";

}
