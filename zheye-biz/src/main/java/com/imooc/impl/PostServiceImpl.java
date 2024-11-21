package com.imooc.impl;

import com.imooc.PostService;
import com.imooc.mapper.PostMapper;
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
}
