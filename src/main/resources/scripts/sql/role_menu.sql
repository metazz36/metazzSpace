DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE  `role_menu`(
  `id` int(11)  NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` int(11)  NULL DEFAULT NULL  COMMENT   '角色ID',  
  `menu_id` int(11)  NULL DEFAULT NULL  COMMENT   '菜单ID',  
   PRIMARY KEY (id)  USING BTREE
) ENGINE=InnoDB
  AUTO_INCREMENT = 1 
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  COMMENT='角色菜单关联表';