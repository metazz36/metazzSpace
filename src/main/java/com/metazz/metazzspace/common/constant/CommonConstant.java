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
     * Redis缓存前缀
     */
    public static final String USER_REGISTER_CODE = "user_register_code_"; // 注册验证码前缀
    public static final String PASSWORD_MODIFY_CODE = "password_modify_code_"; // 密码修改验证码前缀
    public static final String USER_TOKEN = "user_token_"; // Token前缀
    public static final String USERID_TOKEN="userid_token";

    /**
     * 请求头Token键
     */
    public static final String TOKEN_HEADER = "Authorization";

    /**
     * Redis缓存过期时间
     */
    public static final long CODE_EXPIRE = 2; // 验证码过期时间，单位分钟
    public static final long USER_TOKEN_EXPIRE = 5; // Token过期时间，单位天

    /**
     * 加密密钥
     */
    public static final String PASSWORD_KEY = "metazz36metazz36";


}
