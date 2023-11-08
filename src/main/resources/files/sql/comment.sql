DROP TABLE IF EXISTS `comment`;
CREATE TABLE  `comment`(
  `id` int(11)  NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `content`  varchar(1024)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL  COMMENT '评论内容',
  `object_id` int(11)  NULL DEFAULT NULL  COMMENT   '对象ID(对应博客或说说或全站留言的ID)',  
  `type`  varchar(1)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL  COMMENT '评论类型(0-博客 1-说说 2-全站留言)',
  `status`  varchar(1)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT  '1'  COMMENT '状态(0-无效 1-有效)',
  `from_uid` int(11)  NULL DEFAULT NULL  COMMENT   '评论用户ID',  
  `to_uid` int(11)  NULL DEFAULT NULL  COMMENT   '回复用户ID',  
  `comment_id` int(11)  NULL DEFAULT NULL   COMMENT   '评论ID',  
  `approvals` int(11)  NULL DEFAULT  0  COMMENT    '评论点赞数',  
  `created_time`  datetime  NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `delete_time`  datetime  NULL DEFAULT NULL COMMENT '删除时间',
   PRIMARY KEY (id)  USING BTREE
) ENGINE=InnoDB
  AUTO_INCREMENT = 1 
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  COMMENT='评论表';