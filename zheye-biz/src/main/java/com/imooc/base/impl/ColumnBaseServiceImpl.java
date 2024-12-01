package com.imooc.base.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.imooc.base.ColumnBaseService;
import com.imooc.entity.Column;
import com.imooc.mapper.ColumnMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wzy
 * @date 2024/12/1 22:09
 */
@Slf4j
@Service
public class ColumnBaseServiceImpl implements ColumnBaseService {

    @Resource
    private ColumnMapper columnMapper;

    @Override
    public Column getColumnByUserId(Long userId) {
        LambdaQueryWrapper<Column> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Column::getUserId, userId);

        return columnMapper.selectOne(queryWrapper);
    }

    @Override
    public Integer insert(Column column) {
        return columnMapper.insert(column);
    }
}
