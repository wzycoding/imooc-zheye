package com.imooc.impl;

import com.imooc.ImageService;
import com.imooc.mapper.ImageMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wzy
 * @date 2024/11/21 10:52
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    @Resource
    private ImageMapper imageMapper;
}
