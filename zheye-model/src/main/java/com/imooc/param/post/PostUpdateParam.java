package com.imooc.param.post;

import lombok.Data;

/**
 * @author wzy
 * @date 2024/11/20 22:38
 */
@Data
public class PostUpdateParam {
    private String title;
    private String content;
    private Long image;
}
