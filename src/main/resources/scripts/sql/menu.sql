DROP TABLE IF EXISTS `menu`;
CREATE TABLE  `menu`(
  `id` int(11)  NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name`  varchar(64)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL  COMMENT '菜单名',
  `path`  varchar(200)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL  COMMENT '路由地址',
  `component`  varchar(255)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL  COMMENT '组件路径',
  `visiable`  varchar(1)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT  '1'  COMMENT '显示状态(0-隐藏 1-显示)',
  `status`  varchar(1)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT  '1'  COMMENT '状态(0-无效 1-有效)',
  `perms`  varchar(100)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL  COMMENT '权限标识',
  `parent_id`  varchar(100)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT  NULL  COMMENT '父菜单',
  `level` int(1)  NULL DEFAULT 1  COMMENT  '菜单级别',
  `icon`  varchar(100)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL  COMMENT '菜单图标',
  `remark`  varchar(500)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL  COMMENT '备注',
  `created_time`  datetime  NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime  NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_time`  datetime  NULL DEFAULT NULL COMMENT '删除时间',
   PRIMARY KEY (id)  USING BTREE
) ENGINE=InnoDB
  AUTO_INCREMENT = 1 
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  COMMENT='菜单表';