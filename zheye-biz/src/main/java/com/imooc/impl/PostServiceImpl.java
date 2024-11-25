package com.imooc.impl;

import cn.hutool.core.util.StrUtil;
import com.imooc.PostService;
import com.imooc.base.ImageBaseService;
import com.imooc.base.UserBaseService;
import com.imooc.dto.post.PostDetailDTO;
import com.imooc.entity.Post;
import com.imooc.mapper.PostMapper;
import com.imooc.param.post.PostCreateParam;
import com.imooc.param.post.PostUpdateParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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

    @Resource
    private UserBaseService userBaseService;

    @Resource
    private ImageBaseService imageBaseService;

    @Override
    public PostDetailDTO createPost(PostCreateParam createParam) {
        Post newPost = new Post();
        BeanUtils.copyProperties(createParam, newPost);
        newPost.setExcerpt(StrUtil.sub(createParam.getContent(), 0, 50));

        postMapper.insert(newPost);
        PostDetailDTO result = new PostDetailDTO();
        BeanUtils.copyProperties(newPost, result);

        return result;
    }

    @Override
    public PostDetailDTO getDetail(Long id) {
        Post post = postMapper.selectById(id);
        return getPostDetailInfo(post);
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

    /**
     * 获取帖子详情方法
     *
     * @param post 帖子信息
     * @return 帖子详情信息
     */
    private PostDetailDTO getPostDetailInfo(Post post) {
        PostDetailDTO result = new PostDetailDTO();
        BeanUtils.copyProperties(post, result);
        result.setAuthor(userBaseService.getUserDetail(post.getUserId()));
        result.setImage(imageBaseService.getImageInfo(post.getImage()));

        return result;
    }
}
