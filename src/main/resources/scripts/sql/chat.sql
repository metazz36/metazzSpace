DROP TABLE IF EXISTS `chat`;
CREATE TABLE  `chat`(
  `id` int(11)  NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `content`  text  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL  COMMENT '说说内容',
  `comments` int(11)  NULL DEFAULT 0  COMMENT   '说说评论数',  
  `approvals` int(11)  NULL DEFAULT 0  COMMENT   '说说点赞数',  
  `clicks` int(11)  NULL DEFAULT 0  COMMENT   '说说点击数',  
  `status`  varchar(1)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT '1'  COMMENT '状态(0-无效 1-有效)',
  `open_comment`  varchar(1)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL  COMMENT '是否开启评论(0-否 1-是)',
  `created_time`  datetime  NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime  NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_time`  datetime  NULL DEFAULT NULL COMMENT '删除时间',
   PRIMARY KEY (id)  USING BTREE
) ENGINE=InnoDB
  AUTO_INCREMENT = 1 
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  COMMENT='说说表';