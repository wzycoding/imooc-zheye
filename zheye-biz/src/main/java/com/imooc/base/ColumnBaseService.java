package com.imooc.base;

import com.imooc.entity.Column;

/**
 * @author wzy
 * @date 2024/12/1 22:08
 */
public interface ColumnBaseService {

    Column getColumnByUserId(Long userId);

    Integer insert(Column column);
}
