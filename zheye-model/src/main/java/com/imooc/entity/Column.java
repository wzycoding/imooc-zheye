package com.imooc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 专栏实体类
 *
 * @author wzy
 */
@Data
@TableName("t_column")
public class Column {
    private String id;
    private String title;
    private String description;
    private String avatar;
    private String author;
    private Date createTime;
}
