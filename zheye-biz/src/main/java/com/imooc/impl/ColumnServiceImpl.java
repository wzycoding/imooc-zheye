package com.imooc.impl;

import com.imooc.ColumnService;
import com.imooc.mapper.ColumnMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wzy
 * @date 2024/11/21 10:51
 */
@Slf4j
@Service
public class ColumnServiceImpl implements ColumnService {
    @Resource
    private ColumnMapper columnMapper;
}
