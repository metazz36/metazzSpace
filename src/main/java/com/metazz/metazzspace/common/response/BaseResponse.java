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
    private String status;

    /**
     * 响应状态(0000表示正常)
     */
    private String code;

    /**
     * 数据信息
     */
    private T data;

}
