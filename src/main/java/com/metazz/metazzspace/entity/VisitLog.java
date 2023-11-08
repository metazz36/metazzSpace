package com.metazz.metazzspace.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 日志表
 */
@Data
public class VisitLog implements Serializable {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 请求IP地址
     */
    private String ip;

    /**
     * 请求URL
     */
    private String url;

    /**
     * 请求方式
     */
    private String httpMethod;

    /**
     * 请求方法
     */
    private String classMethod;

    /**
     * 请求参数
     */
    private String requestParams;

    /**
     * 响应参数
     */
    private String responseParams;

    /**
     * 异常信息
     */
    private String exceptionInfo;

    /**
     * 请求时间
     */
    private Date requestTime;

    /**
     * 响应时间
     */
    private Date responseTime;

}