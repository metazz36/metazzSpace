package com.metazz.metazzspace.common.response;

import com.metazz.metazzspace.common.constant.GeneralConstant;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @param <T>
 * CR为CommonResponse缩写，返回正常响应(包含数据,不包含分页字段)
 */
@Data
public class CR<T> extends BaseResponse<T>{

    public CR(T t) {
        setStatus(HttpStatus.OK.value());
        setCode(GeneralConstant.NORMAL_CODE);
        super.setData(t);
    }

    public CR() {
        setStatus(HttpStatus.OK.value());
        setCode(GeneralConstant.NORMAL_CODE);
        super.setData(null);
    }

}
