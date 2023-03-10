DROP TABLE IF EXISTS `chat_user_applaud`;
CREATE TABLE  `chat_user_applaud`(
  `id` int(11)  NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(11)  NOT NULL  COMMENT   '用户ID',  
  `blog_id` int(11)  NOT NULL  COMMENT   '说说ID',  
  `created_time`  datetime  NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   PRIMARY KEY (id)  USING BTREE
) ENGINE=InnoDB
  AUTO_INCREMENT = 1 
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  COMMENT='用户点赞说说记录表';