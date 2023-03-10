DROP TABLE IF EXISTS `role`;
CREATE TABLE  `role`(
  `id` int(11)  NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name`  varchar(64)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL  COMMENT '角色名',
  `key`  varchar(100)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL  COMMENT '角色权限字符串',
  `status`  varchar(1)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL  COMMENT '状态(0-无效 1-有效)',
  `created_time`  datetime  NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime  NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
   PRIMARY KEY (id)  USING BTREE
) ENGINE=InnoDB
  AUTO_INCREMENT = 1 
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  COMMENT='角色表';