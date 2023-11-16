DROP TABLE IF EXISTS `user`;
CREATE TABLE  `user`(
  `id` int(11)  NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `nick_name`  varchar(255)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL  COMMENT '昵称',
  `user_name`  varchar(255)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL  COMMENT '用户名',
  `password`  varchar(32)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL  COMMENT '密码',
  `gender`  varchar(1)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL  COMMENT '性别(0-女 1-男)',
  `avatar`  varchar(100)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL  COMMENT '头像',
  `email`  varchar(60)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL  COMMENT '邮箱',
  `birthday`  date  NULL DEFAULT NULL COMMENT '生日',
  `phone`  varchar(50)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL  COMMENT '手机',
  `introduction`  varchar(2048)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL  COMMENT '简介',
  `login_count` int(11)  NULL DEFAULT 0  COMMENT   '登录次数',
  `last_login_time`  datetime  NULL DEFAULT NULL COMMENT '最后登录时间',
  `last_login_ip`  varchar(50)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL  COMMENT '最后登录IP',
  `status`  varchar(1)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT 1  COMMENT '状态(0-无效 1-有效)',
  `comment_status`  varchar(1)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT 1  COMMENT '评论状态(0-禁言 1-正常)',
  `user_tag`  varchar(1)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT 0  COMMENT '用户标签(0-普通用户  1-管理员)',
  `created_time`  datetime  NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime  NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_time`  datetime  NULL DEFAULT CURRENT_TIMESTAMP COMMENT '删除时间',
   PRIMARY KEY (id)  USING BTREE
) ENGINE=InnoDB
  AUTO_INCREMENT = 1 
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  COMMENT='用户表';