package com.imooc.dto.user;

import lombok.Data;

import java.util.Date;

/**
 * @author wzy
 * @date 2024/11/20 16:34
 */
@Data
public class UserCreateDTO {

    private String id;
    private String email;
    private String nickname;
    private String description;
    private String avatar;
    private String column;
    private Date createTime;
}
