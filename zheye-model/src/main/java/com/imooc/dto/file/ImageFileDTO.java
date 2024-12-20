package com.imooc.dto.file;

import lombok.Data;

import java.util.Date;

/**
 * 图片文件DTO
 *
 * @author wzy
 * @date 2024/11/20 20:38
 */
@Data
public class ImageFileDTO {
    private Long id;
    private String url;
    private Date createTime;
}
