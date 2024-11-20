package com.imooc.response;

import com.imooc.enums.HttpStatusEnum;
import lombok.Data;

/**
 * 接口通用响应对象
 *
 * @param <T>
 */
@Data
public class CommonResponse<T> {
    private Integer code;
    private String msg;
    private T data;

    public CommonResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public CommonResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<T>(HttpStatusEnum.OK.getCode(), "", data);
    }

    public static <T> CommonResponse<T> success() {
        return new CommonResponse<T>(HttpStatusEnum.OK.getCode(), "");
    }

    public static <T> CommonResponse<T> error(HttpStatusEnum httpStatus, String msg) {
        return new CommonResponse<T>(httpStatus.getCode(), msg);
    }
}
