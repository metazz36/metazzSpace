DROP TABLE IF EXISTS `visit_log`;
CREATE TABLE  `visit_log`(
  `id` int(11)  NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(11)  NOT NULL   COMMENT  '用户ID',
   `ip`  varchar(255)  NULL DEFAULT NULL COMMENT '请求IP地址',
   `url`  varchar(255)  NULL DEFAULT NULL COMMENT '请求URL',
   `http_method`  varchar(32)  NULL DEFAULT NULL COMMENT '请求方式',
   `class_method`  varchar(255)  NULL DEFAULT NULL COMMENT '请求方法',
   `request_params`  longtext NULL DEFAULT NULL COMMENT '请求参数',
   `response_params`  longtext NULL DEFAULT NULL COMMENT '响应参数',
   `exception_info`  longtext NULL DEFAULT NULL COMMENT '异常信息',
  `request_time`  datetime  NULL DEFAULT NULL COMMENT '请求时间',
  `response_time`  datetime  NULL DEFAULT NULL COMMENT '响应时间',
   PRIMARY KEY (id)  USING BTREE
) ENGINE=InnoDB
  AUTO_INCREMENT = 1 
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  COMMENT='日志表';