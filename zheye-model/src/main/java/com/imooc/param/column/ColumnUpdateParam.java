package com.imooc.param.column;

import lombok.Data;

/**
 * @author wzy
 * @date 2024/11/20 20:48
 */
@Data
public class ColumnUpdateParam {
    private String title;
    private String description;
    private Long avatar;
}
