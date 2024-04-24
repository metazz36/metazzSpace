DROP TABLE IF EXISTS `category`;
CREATE TABLE  `category`(
  `id` int(11)  NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name`  varchar(255)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL  COMMENT '分类名字',
 `status`  varchar(1)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT  '1'  COMMENT '状态(0-无效 1-有效)',
  `sort` int(11)  NULL DEFAULT  0  COMMENT   '排序字段',  
  `clicks` int(11)  NULL DEFAULT 0  COMMENT   '分类点击数',
  `counts` int(11)  NULL DEFAULT 0  COMMENT   '博客数量',
  `created_time`  datetime  NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime  NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_time`  datetime  NULL DEFAULT NULL COMMENT '删除时间',
   PRIMARY KEY (id)  USING BTREE
) ENGINE=InnoDB
  AUTO_INCREMENT = 1 
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  COMMENT='博客分类表';