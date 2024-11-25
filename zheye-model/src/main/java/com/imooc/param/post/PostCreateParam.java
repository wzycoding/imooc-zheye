package com.imooc.param.post;

import lombok.Data;

/**
 * @author wzy
 * @date 2024/11/20 22:27
 */
@Data
public class PostCreateParam {
    private String title;
    private String content;
    private Long image;
    private Long columnId;
    private String author;
}
