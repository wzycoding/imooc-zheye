package com.imooc.file;

import com.imooc.dto.file.ImageFileDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author wzy
 * @date 2024/11/20 20:23
 */
@Slf4j
@RestController
@RequestMapping("/upload")
public class ImageUploadController {
    @Value("${file.upload.path}")
    private String uploadFilePath;
    
    @PostMapping("/")
    public ImageFileDTO upload(@RequestParam("file") MultipartFile file) throws Exception {
        // 设置上传至项目文件夹下的uploadFile文件夹中，没有文件夹则创建
        File dir = new File(uploadFilePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        file.transferTo(new File(dir.getAbsolutePath() + File.separator + System.currentTimeMillis() + ".png"));
        return new ImageFileDTO();
    }
}
