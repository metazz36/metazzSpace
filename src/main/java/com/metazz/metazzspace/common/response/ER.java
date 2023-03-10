package com.metazz.metazzspace.common.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * ER为ErrorResponse缩写，返回异常响应
 */
@Data
public class ER extends BaseResponse{

    private String errorMsg;

    public ER(Integer errorCode,String errorMsg) {
        super.setStatus(HttpStatus.OK.value());
        super.setCode(errorCode);
        this.errorMsg = errorMsg;
    }

    public ER(Integer status,Integer errorCode,String errorMsg) {
        super.setStatus(status);
        super.setCode(errorCode);
        this.errorMsg = errorMsg;
    }

}
