package com.imooc.param.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wzy
 * @date 2024/11/20 16:34
 */
@Data
public class UserUpdateParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "电子邮箱")
    private String email;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "简介")
    private String description;

    @ApiModelProperty(value = "头像")
    private String avatar;
}
