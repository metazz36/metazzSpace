package com.metazz.metazzspace.common.response;

import com.metazz.metazzspace.common.response.CR;

/**
 * 父controller，封装正常返回响应数据
 */
public interface BaseController {

    default <T> CR<T> success(T response){
        return new CR<>(response);
    }

    default CR success() {
        return new CR<>();
    }

}
