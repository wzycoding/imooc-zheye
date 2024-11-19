package com.imooc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 用户实体类
 *
 * @author wzy
 */
@Data
@TableName("t_user")
public class User {
    private String id;
    private String email;
    private String nickName;
    private String description;
    private String avatar;
    private String column;
    private Date createdAt;
}
