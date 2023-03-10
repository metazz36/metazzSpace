DROP TABLE IF EXISTS `friendly_link`;
CREATE TABLE  `friendly_link`(
  `id` int(11)  NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL  COMMENT '友情链接标题',
  `summary`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL  COMMENT '友情链接介绍',
  `url`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL  COMMENT '友情链接URL',
  `clicks` int(11)  NULL DEFAULT 0 COMMENT  '点击数',  
  `status`  varchar(1)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT  '1'  COMMENT '状态(0-无效 1-有效)',
  `sort` int(11)  NULL DEFAULT  0  COMMENT  '排序字段',  
  `created_time`  datetime  NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime  NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_time`  datetime  NULL DEFAULT NULL COMMENT '删除时间',
   PRIMARY KEY (id)  USING BTREE
) ENGINE=InnoDB
  AUTO_INCREMENT = 1 
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  COMMENT='友情链接表';