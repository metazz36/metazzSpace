package com.metazz.metazzspace.common.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * ER为ErrorResponse缩写，返回异常响应
 */
@Data
public class ER extends BaseResponse{

    private String errorMsg;

    public ER(String errorCode,String errorMsg) {
        super.setStatus(String.valueOf(HttpStatus.OK.value()));
        super.setCode(errorCode);
        this.errorMsg = errorMsg;
    }

    public ER(String status,String errorCode,String errorMsg) {
        super.setStatus(status);
        super.setCode(errorCode);
        this.errorMsg = errorMsg;
    }

}
