package com.metazz.metazzspace.model.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 菜单表
 */
@Data
public class Menu implements Serializable {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 菜单名
     */
    private String name;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 显示状态(0-隐藏 1-显示)
     */
    private String visiable;

    /**
     * 状态(0-无效 1-有效)
     */
    private String status;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 父菜单
     */
    private String parentId;

    /**
     * 菜单级别
     */
    private Integer level;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 删除时间
     */
    private Date deleteTime;

}