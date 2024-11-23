package com.imooc;

import com.imooc.dto.column.ColumnDetailDTO;
import com.imooc.dto.column.ColumnPageDTO;
import com.imooc.param.column.ColumnUpdateParam;

import java.util.List;

/**
 * @author wzy
 * @date 2024/11/21 09:48
 */
public interface ColumnService {

    /**
     * 获取专栏列表
     *
     * @param page 第几页
     * @param size 每页多少条数据
     * @return 专栏列表
     */
    List<ColumnPageDTO> getColumnList(Integer page,
                                      Integer size);

    /**
     * 获取专栏详情
     *
     * @param id 专栏id
     * @return 专栏详情信息
     */
    ColumnDetailDTO getDetail(String id);

    /**
     * 更新专栏信息
     *
     * @param id          专栏id
     * @param updateParam 更新参数
     * @return 专栏信息
     */
    ColumnDetailDTO update(String id, ColumnUpdateParam updateParam);

}