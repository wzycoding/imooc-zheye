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
@ConfigurationProperties(prefix = "file")
public class FileUploadProperties {
    /**
     * 上传图片base url
     */
    private String baseUrl = "http://localhost:7001/api/upload/image/";

}
