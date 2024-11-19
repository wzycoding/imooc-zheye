package com.imooc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 文章实体类
 *
 * @author wzy
 */
@Data
@TableName("t_post")
public class Post {
    private String id;
    private String title;
    private String excerpt;
    private String content;
    private String image;
    private String author;
    private String column;
    private String createdAt;
}
