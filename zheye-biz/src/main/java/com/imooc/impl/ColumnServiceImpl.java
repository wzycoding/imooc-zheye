package com.imooc.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.ColumnService;
import com.imooc.base.ImageBaseService;
import com.imooc.base.UserBaseService;
import com.imooc.dto.column.ColumnDetailDTO;
import com.imooc.dto.post.PostDetailDTO;
import com.imooc.entity.Column;
import com.imooc.entity.Post;
import com.imooc.enums.BizCodeEnum;
import com.imooc.exception.BizException;
import com.imooc.mapper.ColumnMapper;
import com.imooc.mapper.PostMapper;
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
    private PostMapper postMapper;

    @Resource
    private UserBaseService userBaseService;

    @Resource
    private ImageBaseService imageBaseService;

    @Override
    public Page<ColumnDetailDTO> getColumnList(Integer page, Integer size) {
        //设置分页参数
        Page<Column> rowPage = new Page<>(page, size);

        LambdaQueryWrapper<Column> queryWrapper = new LambdaQueryWrapper<>();
        Page<Column> columnPage = columnMapper.selectPage(rowPage, queryWrapper);
        List<Column> columnList = columnPage.getRecords();

        Page<ColumnDetailDTO> resultPage = new Page<>();
        BeanUtils.copyProperties(columnPage, resultPage);
        resultPage.setRecords(columnList.stream().map(this::getColumnDetailInfo).collect(Collectors.toList()));

        return resultPage;
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

    @Override
    public List<PostDetailDTO> getPostList(String id, Integer page,
                                           Integer size) {
        //设置分页参数
        Page<Post> rowPage = new Page<>(page, size);

        LambdaQueryWrapper<Post> queryWrapper = new LambdaQueryWrapper<>();
        Page<Post> postPage = postMapper.selectPage(rowPage, queryWrapper);
        queryWrapper.eq(Post::getColumnId, id);
        List<Post> postList = postPage.getRecords();

        return postList.stream().map(this::getPostDetail).collect(Collectors.toList());
    }

    /**
     * 将Post实体类转换成PostDetailDTO
     *
     * @param post 文章信息
     * @return 文章详情信息
     */
    private PostDetailDTO getPostDetail(Post post) {
        PostDetailDTO detail = new PostDetailDTO();
        BeanUtils.copyProperties(post, detail);
        detail.setAuthor(userBaseService.getUserDetail(post.getUserId()));
        if (Objects.nonNull(post.getImage())) {
            detail.setImage(imageBaseService.getImageInfo(post.getImage()));
        }
        return detail;
    }

    private ColumnDetailDTO getColumnDetailInfo(Column column) {
        ColumnDetailDTO detail = new ColumnDetailDTO();
        BeanUtils.copyProperties(column, detail);
        detail.setAuthor(userBaseService.getUserDetail(column.getUserId()));
        detail.setAvatar(imageBaseService.getImageInfo(column.getAvatar()));
        return detail;
    }
}
