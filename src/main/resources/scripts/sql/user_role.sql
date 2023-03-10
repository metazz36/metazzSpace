DROP TABLE IF EXISTS `user_role`;
CREATE TABLE  `user_role`(
  `id` int(11)  NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` int(11)  NULL DEFAULT NULL  COMMENT  '角色ID',  
  `user_id` int(11)  NULL DEFAULT NULL  COMMENT   '用户ID',  
   PRIMARY KEY (id)  USING BTREE
) ENGINE=InnoDB
  AUTO_INCREMENT = 1 
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  COMMENT='用户角色关联表';