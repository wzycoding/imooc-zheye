package com.imooc.impl;

import com.imooc.PostService;
import com.imooc.dto.post.PostDetailDTO;
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
        return null;
    }

    @Override
    public PostDetailDTO getDetail(String id) {
        return null;
    }

    @Override
    public PostDetailDTO updateDetail(String id, PostUpdateParam updateParam) {
        return null;
    }

    @Override
    public PostDetailDTO delete(String id) {
        return null;
    }

    @Override
    public PostDetailDTO getPostDetail(Integer id, Integer page, Integer size) {
        return null;
    }
}
