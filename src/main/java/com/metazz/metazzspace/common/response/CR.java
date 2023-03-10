package com.metazz.metazzspace.common.response;

import com.metazz.metazzspace.common.constant.CommonConstant;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @param <T>
 * CR为CommonResponse缩写，用于封装数据(不包含分页字段)
 */
@Data
public class CR<T> extends BaseResponse<T>{

    public CR(T t) {
        setStatus(HttpStatus.OK.value());
        setCode(CommonConstant.NORMAL_CODE);
        super.setData(t);
    }

    public CR() {
        setStatus(HttpStatus.OK.value());
        setCode(CommonConstant.NORMAL_CODE);
        super.setData(null);
    }

    public CR(int code, T t) {
        setStatus(HttpStatus.OK.value());
        setCode(code);
        super.setData(t);
    }

}
