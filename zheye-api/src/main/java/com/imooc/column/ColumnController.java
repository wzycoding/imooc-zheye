package com.imooc.column;

import com.imooc.ColumnService;
import com.imooc.dto.column.ColumnDetailDTO;
import com.imooc.dto.column.ColumnPageDTO;
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
    public List<ColumnPageDTO> getColumnList(@RequestParam("page") Integer page,
                                             @RequestParam("size") Integer size) {
        return columnService.getColumnList(page, size);
    }

    @GetMapping("/{id}")
    public ColumnDetailDTO getDetail(@PathVariable String id) {

        return columnService.getDetail(id);
    }

    @PatchMapping("/{id}")
    public ColumnDetailDTO update(@PathVariable String id,
                                  @RequestBody ColumnUpdateParam updateParam) {

        return columnService.update(id, updateParam);
    }
}
