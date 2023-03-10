DROP TABLE IF EXISTS `blog`;
CREATE TABLE  `blog`(
  `id` int(11)  NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title`  varchar(200)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL  COMMENT '博客标题',
  `summary`  varchar(200)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL  COMMENT '博客简介',
  `content`  longtext  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL  COMMENT '博客内容',
  `image`  varchar(200)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL  COMMENT '博客图片',
  `words` int(24)  NULL DEFAULT 0 COMMENT '博客字数',
  `category_id` int(24)  NOT NULL  COMMENT '博客分类ID',
  `label_id` int(24)  NOT NULL  COMMENT  '博客标签ID',
  `approvals` int(11)  NULL DEFAULT 0   COMMENT '博客点赞数',  
  `collections` int(11)  NULL DEFAULT 0  COMMENT '博客收藏数',  
  `comments` int(11)  NULL DEFAULT 0  COMMENT  '博客评论数',  
  `clicks` int(11)  NULL DEFAULT 0  COMMENT  '博客点击数',  
  `user_id` int(11)  NOT NULL  COMMENT  '用户ID',
  `author`  varchar(255)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL  COMMENT '作者',
  `sort` int(11)  NULL DEFAULT NULL   COMMENT '排序字段',  
  `source`  varchar(255)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL  COMMENT '文章出处',
  `status`  varchar(1)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT '1'  COMMENT '状态(0-无效 1-有效)',
  `is_original`  varchar(1)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL  COMMENT '是否原创(0-否 1-是)',
  `is_publish`  varchar(1)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL  COMMENT '是否发布(0-否 1-是)',
  `open_comment`  varchar(1)  CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL  COMMENT '是否开启评论(0-否 1-是)',
  `created_time`  datetime  NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime  NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_time`  datetime  NULL DEFAULT  NULL COMMENT '删除时间',
   PRIMARY KEY (id)  USING BTREE
) ENGINE=InnoDB
  AUTO_INCREMENT = 1 
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  COMMENT='博客表';