package com.imooc.config;

import com.imooc.util.SnowFlake;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wzy
 * @date 2024/11/21 20:17
 */
@Configuration
public class SnowFlakeConfiguration {

    @Bean
    public SnowFlake snowFlake() {
        return new SnowFlake(1, 1);
    }
}
