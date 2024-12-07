package com.imooc.column;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.ColumnService;
import com.imooc.annotation.UserAuth;
import com.imooc.dto.column.ColumnDetailDTO;
import com.imooc.dto.post.PostDetailDTO;
import com.imooc.param.column.ColumnUpdateParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 专栏列表
 *
 * @author wzy
 * @date 2024/11/20 20:23
 */
@Slf4j
@RestController
@RequestMapping("/columns")
public class ColumnController {

    @Resource
    private ColumnService columnService;

    @GetMapping("/")
    public Page<ColumnDetailDTO> getColumnList(@RequestParam(name = "currentPage", defaultValue = "1") Integer currentPage,
                                               @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        return columnService.getColumnList(currentPage, pageSize);
    }

    @GetMapping("/{id}")
    public ColumnDetailDTO getDetail(@PathVariable String id) {

        return columnService.getDetail(id);
    }

    @PatchMapping("/{id}")
    @UserAuth
    public ColumnDetailDTO update(@PathVariable String id,
                                  @RequestBody ColumnUpdateParam updateParam) {

        return columnService.update(id, updateParam);
    }

    @GetMapping("/{id}/posts")
    public List<PostDetailDTO> getPostList(@PathVariable String id,
                                           @RequestParam(name = "page", defaultValue = "1") Integer page,
                                           @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return columnService.getPostList(id, page, size);
    }
}
