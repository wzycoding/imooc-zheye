package com.imooc.base;

import com.imooc.dto.file.ImageFileDTO;
import com.imooc.entity.Image;

/**
 * @author wzy
 * @date 2024/11/25 11:39
 */
public interface ImageBaseService {
    Image getImageById(Long id);

    ImageFileDTO getImageInfo(Long id);
}
