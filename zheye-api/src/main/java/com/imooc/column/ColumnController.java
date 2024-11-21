package com.imooc.column;

import com.imooc.dto.column.ColumnDetailDTO;
import com.imooc.dto.column.ColumnPageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
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

    @GetMapping("/")
    public List<ColumnPageDTO> getColumnList(@RequestParam("page") Integer page,
                                             @RequestParam("size") Integer size) {
        return Collections.emptyList();
    }

    @GetMapping("/{id}")
    public ColumnDetailDTO getDetail(@PathVariable String id) {

        return new ColumnDetailDTO();
    }

    @PatchMapping("/{id}")
    public ColumnDetailDTO update(@PathVariable String id) {

        return new ColumnDetailDTO();
    }
}
