package com.imooc;

import com.imooc.dto.file.ImageFileDTO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wzy
 * @date 2024/11/21 09:48
 */
public interface ImageService {

    ImageFileDTO upload(MultipartFile file);

    byte[] image(String fileName);
}
