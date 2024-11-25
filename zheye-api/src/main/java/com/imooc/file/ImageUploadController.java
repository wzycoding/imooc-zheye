package com.imooc.file;

import com.imooc.ImageService;
import com.imooc.annotation.IgnoreResponseAdvice;
import com.imooc.dto.file.ImageFileDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author wzy
 * @date 2024/11/20 20:23
 */
@Slf4j
@RestController
@RequestMapping("/upload")
public class ImageUploadController {

    @Resource
    private ImageService imageService;

    @PostMapping("/")
    public ImageFileDTO upload(@RequestParam("file") MultipartFile file) {
        return imageService.upload(file);
    }

    @GetMapping(value = "/image/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
    @IgnoreResponseAdvice
    public byte[] image(@PathVariable(name = "fileName") String fileName) {
        return imageService.image(fileName);
    }

}
