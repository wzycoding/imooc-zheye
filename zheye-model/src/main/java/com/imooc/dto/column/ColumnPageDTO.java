package com.imooc.dto.column;

import lombok.Data;

import java.util.Date;

/**
 * 专栏分页DTO
 *
 * @author wzy
 * @date 2024/11/20 20:30
 */
@Data
public class ColumnPageDTO {
    private Long id;
    private String title;
    private String description;
    private String avatar;
    private Long author;
    private Date createTime;
}
