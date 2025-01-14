package com.imooc.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author wzy
 * @date 2024/11/20 10:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDTO {
    private Long id;
    private String email;
    private String nickname;
    private String description;
    private String avatar;
    private Long columnId;
    private Date createTime;
}
