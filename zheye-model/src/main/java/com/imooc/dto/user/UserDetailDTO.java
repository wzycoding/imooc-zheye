package com.imooc.dto.user;

import com.imooc.dto.file.ImageFileDTO;
import lombok.Data;

import java.util.Date;

/**
 * @author wzy
 * @date 2024/11/20 16:34
 */
@Data
public class UserDetailDTO {

    private Long id;
    private String email;
    private String nickname;
    private String description;
    private ImageFileDTO avatar;
    private String columnId;
    private Date createTime;
}
