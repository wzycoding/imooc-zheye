package com.imooc.dto.column;

import com.imooc.dto.file.ImageFileDTO;
import lombok.Data;

import java.util.Date;

/**
 * 专栏详情DTO
 *
 * @author wzy
 * @date 2024/11/20 20:35
 */
@Data
public class ColumnDetailDTO {
    private Long id;
    private String title;
    private String description;
    private ImageFileDTO avatar;
    private String author;
    private Date createTime;
}
