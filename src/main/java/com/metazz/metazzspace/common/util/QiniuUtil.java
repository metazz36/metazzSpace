package com.metazz.metazzspace.common.util;

import com.metazz.metazzspace.common.constant.CommonConstant;
import com.qiniu.util.Auth;

public class QiniuUtil {

    public static String getToken(String key){
        Auth auth = Auth.create(CommonConstant.ACCESS_KEY, CommonConstant.SECRET_KEY);
        return auth.uploadToken(CommonConstant.BUCKET, key);
    }

}
