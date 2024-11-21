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
@TableName("t_post")
@ApiModel(value="Post对象", description="")
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "摘抄")
    private String excerpt;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "图片")
    private String image;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "专栏id")
    private String columnId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}
