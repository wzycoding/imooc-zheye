package com.imooc.base.impl;

import com.imooc.base.ImageBaseService;
import com.imooc.dto.file.ImageFileDTO;
import com.imooc.entity.Image;
import com.imooc.mapper.ImageMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wzy
 * @date 2024/11/25 11:40
 */
@Slf4j
@Service
public class ImageBaseServiceImage implements ImageBaseService {

    @Resource
    private ImageMapper imageMapper;

    @Override
    public Image getImageById(Long id) {
        return imageMapper.selectById(id);
    }

    @Override
    public ImageFileDTO getImageInfo(Long id) {
        Image image = imageMapper.selectById(id);
        ImageFileDTO result = new ImageFileDTO();
        BeanUtils.copyProperties(image, result);

        return result;
    }
}
