package com.metazz.metazzspace.common.constant;

public class CommonConstant {

    /**
     * 响应状态码
     */
    public static final String SUCCESS_RESPONSE = "0000"; // 成功响应
    public static final String SERVER_ERROR = "1000"; // 服务器异常
    public static final String BUSINESS_ERROR = "2000"; // 业务异常
    public static final String VALID_ERROR = "3000"; // 参数校验异常

    /**
     * 七牛云
     */
    public static final String ACCESS_KEY = "vQcreCt6zqA5BApQ2ojHvglB-9x63JGK2UfN98m6";
    public static final String SECRET_KEY = "IOS4zzMSr2OZ-T-sZzbaAeASHhSFGTIEVOZh2Quz";
    public static final String BUCKET = "metazzbase";
    public static final String DOWNLOAD_URL = "http://s3w87as9r.hn-bkt.clouddn.com/";

    /**
     * Redis缓存前缀
     */
    public static final String USER_CODE = "user_code_"; // 验证码前缀
    public static final String USER_TOKEN = "user_token_"; // Token前缀

    /**
     * 请求头Token键
     */
    public static final String TOKEN_HEADER = "Authorization";

    /**
     * Redis缓存过期时间
     */
    public static final long USER_CODE_EXPIRE = 2; // 验证码过期时间，单位分钟
    public static final long USER_TOKEN_EXPIRE = 5; // Token过期时间，单位天

    /**
     * 加密密钥
     */
    public static final String PASSWORD_KEY = "metazz36metazz36";


}
