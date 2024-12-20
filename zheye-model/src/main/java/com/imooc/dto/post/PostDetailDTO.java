package com.imooc.dto.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.imooc.dto.file.ImageFileDTO;
import com.imooc.dto.user.UserDetailDTO;
import lombok.Data;

import java.util.Date;

/**
 * @author wzy
 * @date 2024/11/20 22:29
 */
@Data
public class PostDetailDTO {
    private Long id;
    private String title;
    private String excerpt;
    private String content;
    private ImageFileDTO image;
    private UserDetailDTO author;
    private Long columnId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
