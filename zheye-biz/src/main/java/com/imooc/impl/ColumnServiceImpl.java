package com.imooc.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.ColumnService;
import com.imooc.base.ImageBaseService;
import com.imooc.base.UserBaseService;
import com.imooc.dto.column.ColumnDetailDTO;
import com.imooc.entity.Column;
import com.imooc.enums.BizCodeEnum;
import com.imooc.exception.BizException;
import com.imooc.mapper.ColumnMapper;
import com.imooc.param.column.ColumnUpdateParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author wzy
 * @date 2024/11/21 10:51
 */
@Slf4j
@Service
public class ColumnServiceImpl implements ColumnService {
    @Resource
    private ColumnMapper columnMapper;

    @Resource
    private UserBaseService userBaseService;

    @Resource
    private ImageBaseService imageBaseService;

    @Override
    public List<ColumnDetailDTO> getColumnList(Integer page, Integer size) {
        //设置分页参数
        Page<Column> rowPage = new Page<>(page, size);

        LambdaQueryWrapper<Column> queryWrapper = new LambdaQueryWrapper<>();
        Page<Column> columnPage = columnMapper.selectPage(rowPage, queryWrapper);
        List<Column> columnList = columnPage.getRecords();

        return columnList.stream().map(this::getColumnDetailInfo).collect(Collectors.toList());
    }

    @Override
    public ColumnDetailDTO getDetail(String id) {

        Column column = columnMapper.selectById(id);

        if (Objects.isNull(column)) {
            throw new BizException(BizCodeEnum.PARAM_ERROR, "专栏信息不存在");
        }
        return getColumnDetailInfo(column);
    }


    @Override
    public ColumnDetailDTO update(String id, ColumnUpdateParam updateParam) {

        Column column = columnMapper.selectById(id);
        if (Objects.isNull(column)) {
            throw new BizException(BizCodeEnum.PARAM_ERROR, "专栏信息不存在");
        }

        BeanUtils.copyProperties(updateParam, column);
        columnMapper.updateById(column);

        return getColumnDetailInfo(column);
    }

    private ColumnDetailDTO getColumnDetailInfo(Column column) {
        ColumnDetailDTO detail = new ColumnDetailDTO();
        BeanUtils.copyProperties(column, detail);
        detail.setAuthor(userBaseService.getUserDetail(column.getUserId()));
        detail.setAvatar(imageBaseService.getImageInfo(column.getAvatar()));
        return detail;
    }
}
