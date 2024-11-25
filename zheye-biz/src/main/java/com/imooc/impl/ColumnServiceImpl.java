package com.imooc.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.ColumnService;
import com.imooc.dto.column.ColumnDetailDTO;
import com.imooc.dto.column.ColumnPageDTO;
import com.imooc.dto.user.UserDetailDTO;
import com.imooc.entity.Column;
import com.imooc.entity.User;
import com.imooc.enums.BizCodeEnum;
import com.imooc.exception.BizException;
import com.imooc.mapper.ColumnMapper;
import com.imooc.mapper.UserMapper;
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
    private UserMapper userMapper;

    @Override
    public List<ColumnPageDTO> getColumnList(Integer page, Integer size) {
        //设置分页参数
        Page<Column> rowPage = new Page<>(page, size);

        LambdaQueryWrapper<Column> queryWrapper = new LambdaQueryWrapper<>();
        Page<Column> columnPage = columnMapper.selectPage(rowPage, queryWrapper);
        List<Column> columnList = columnPage.getRecords();

        return columnList.stream().map(column -> {
            ColumnPageDTO pageDTO = new ColumnPageDTO();
            BeanUtils.copyProperties(column, pageDTO);
            User user = userMapper.selectById(column.getUserId());
            UserDetailDTO author = new UserDetailDTO();
            BeanUtils.copyProperties(user, author);
            pageDTO.setAuthor(author);
            return pageDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public ColumnDetailDTO getDetail(String id) {
        ColumnDetailDTO detail = new ColumnDetailDTO();
        Column column = columnMapper.selectById(id);

        if (Objects.isNull(column)) {
            throw new BizException(BizCodeEnum.PARAM_ERROR, "专栏信息不存在");
        }
        BeanUtils.copyProperties(column, detail);
        return detail;
    }

    @Override
    public ColumnDetailDTO update(String id, ColumnUpdateParam updateParam) {

        Column column = columnMapper.selectById(id);
        if (Objects.isNull(column)) {
            throw new BizException(BizCodeEnum.PARAM_ERROR, "专栏信息不存在");
        }

        BeanUtils.copyProperties(updateParam, column);
        columnMapper.updateById(column);

        ColumnDetailDTO result = new ColumnDetailDTO();
        BeanUtils.copyProperties(column, result);
        return result;
    }
}
