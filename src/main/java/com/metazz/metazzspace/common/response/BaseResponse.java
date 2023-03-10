package com.metazz.metazzspace.common.response;

import lombok.Data;

/**
 * 父Response,封装响应信息
 */
@Data
public class BaseResponse<T> {

    /**
     * 通讯状态码(200表示正常)
     */
    private Integer status;

    /**
     * 数据信息
     */
    private T data;

    /**
     * 响应状态码(1000是正常，其他为异常)
     */
    private Integer code;

}
