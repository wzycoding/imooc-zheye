package com.imooc.impl;

import com.imooc.ImageService;
import com.imooc.dto.file.ImageFileDTO;
import com.imooc.entity.Image;
import com.imooc.enums.BizCodeEnum;
import com.imooc.exception.BizException;
import com.imooc.mapper.ImageMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author wzy
 * @date 2024/11/21 10:52
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    @Resource
    private ImageMapper imageMapper;

    @Value("${file.upload.path}")
    private String uploadFilePath;

    @Override
    public ImageFileDTO upload(MultipartFile file) {
        // 设置上传至项目文件夹下的uploadFile文件夹中，没有文件夹则创建
        File dir = new File(uploadFilePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String fileName = System.currentTimeMillis() + "." + getFileExtension(file);
        try {
            file.transferTo(new File(dir.getAbsolutePath() + File.separator + fileName));
        } catch (IOException e) {
            throw new BizException(BizCodeEnum.INTERNAL_SERVER_ERROR, "文件上传失败");
        }
        String url = String.format("http://localhost:7001/upload/image/%s", fileName);

        Image newImage = new Image();
        newImage.setUrl(url);

        imageMapper.insert(newImage);

        ImageFileDTO result = new ImageFileDTO();
        BeanUtils.copyProperties(newImage, result);
        return result;
    }

    @Override
    public byte[] image(String fileName) {
        File file = new File(String.format("%s" + File.separator + "%s", uploadFilePath, fileName));
        FileInputStream inputStream = null;
        byte[] bytes;
        try {
            inputStream = new FileInputStream(file);
            bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());
        } catch (IOException e) {
            e.printStackTrace();
            throw new BizException(BizCodeEnum.PARAM_ERROR, "图片加载失败");
        }

        return bytes;
    }

    private String getFileExtension(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null && originalFilename.contains(".")) {
            return originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        } else {
            throw new BizException(BizCodeEnum.PARAM_ERROR, "无法获取文件扩展名");
        }
    }
}
