package com.metazz.metazzspace.common.response;

import com.metazz.metazzspace.common.constant.CommonConstant;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @param <T>
 * CR为CommonResponse缩写，返回正常响应(包含数据)
 */
@Data
public class CR<T> extends BaseResponse<T>{

    public CR(T t) {
        setStatus(String.valueOf(HttpStatus.OK.value()));
        setCode(CommonConstant.SUCCESS_RESPONSE);
        super.setData(t);
    }

    public CR() {
        setStatus(String.valueOf(HttpStatus.OK.value()));
        setCode(CommonConstant.SUCCESS_RESPONSE);
        super.setData(null);
    }

}
