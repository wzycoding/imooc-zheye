package com.imooc.impl;

import cn.hutool.core.util.StrUtil;
import com.imooc.PostService;
import com.imooc.dto.post.PostDetailDTO;
import com.imooc.entity.Post;
import com.imooc.mapper.PostMapper;
import com.imooc.param.post.PostCreateParam;
import com.imooc.param.post.PostUpdateParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wzy
 * @date 2024/11/21 10:52
 */
@Slf4j
@Service
public class PostServiceImpl implements PostService {
    @Resource
    private PostMapper postMapper;

    @Override
    public PostDetailDTO createPost(PostCreateParam createParam) {
        Post newPost = new Post();

        newPost.setContent(createParam.getContent());
        newPost.setColumnId(createParam.getColumnId());
        newPost.setExcerpt(StrUtil.sub(createParam.getContent(), 0, 50));
        newPost.setImage(createParam.getImage());

        return new PostDetailDTO();
    }

    @Override
    public PostDetailDTO getDetail(Long id) {
        return null;
    }

    @Override
    public PostDetailDTO updateDetail(Long id, PostUpdateParam updateParam) {
        return null;
    }

    @Override
    public PostDetailDTO delete(Long id) {
        return null;
    }

    @Override
    public PostDetailDTO getPostDetail(Long id, Integer page, Integer size) {
        return null;
    }
}
