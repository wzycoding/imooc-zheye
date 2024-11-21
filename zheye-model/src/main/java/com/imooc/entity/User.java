package com.imooc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author wzy
 * @since 2024-11-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_user")
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "电子邮箱")
    private String email;

    @ApiModelProperty(value = "简介")
    private String description;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "专栏id")
    private String columnId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}
