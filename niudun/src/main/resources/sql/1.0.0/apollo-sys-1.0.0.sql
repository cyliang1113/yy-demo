CREATE DATABASE `niudun_console` default character set utf8 collate utf8_general_ci;

CREATE TABLE `sys_user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `emp_no` varchar(255) NOT NULL DEFAULT '' COMMENT '工号',
  `user_code` varchar(50) NOT NULL DEFAULT '' COMMENT '用户code',
  `nick_name` varchar(80) NOT NULL DEFAULT '' COMMENT '昵称',
  `password` varchar(32) NOT NULL DEFAULT '' COMMENT '密码',
  `real_name` varchar(50) NOT NULL DEFAULT '' COMMENT '真实姓名',
  `phone` varchar(50) NOT NULL DEFAULT '' COMMENT '联系方式',
  `state` int(1) NOT NULL DEFAULT 0 COMMENT '0:in_use,1:invalid',
  `sex` int(1) NOT NULL DEFAULT 0 COMMENT '0:男,1:女',
  `age` int(3) NOT NULL DEFAULT 0 COMMENT '年龄',
  `email` varchar(80) NOT NULL DEFAULT '' COMMENT '邮箱',
  `id_number`  varchar(255)  NOT NULL DEFAULT '' COMMENT '身份证号码' ,
  `register_time` datetime NOT NULL DEFAULT now() COMMENT '注册时间',
  `ip` varchar(255) NOT NULL DEFAULT '' COMMENT '登录ip',
  `last_login_time` datetime COMMENT '最近一次登录时间',
  `province_code` varchar(11) NOT NULL DEFAULT '' COMMENT '省编码',
  `province_name` varchar(255) NOT NULL DEFAULT '' COMMENT '省名',
  `city_code` varchar(11) NOT NULL DEFAULT '' COMMENT '市编码',
  `city_name` varchar(255) NOT NULL DEFAULT '' COMMENT '市名',
  `area_code` varchar(11) NOT NULL DEFAULT '' COMMENT '区编码',
  `area_name` varchar(255) NOT NULL DEFAULT '' COMMENT '区名',
  `address` varchar(500) NOT NULL DEFAULT '' COMMENT '详细地址',
  `description` varchar(2000) NOT NULL DEFAULT '' COMMENT '描述/备注',
  `salt` varchar(255) NOT NULL DEFAULT '' COMMENT '密码加密盐值',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除：0在使用、1已删除',
  `create_uid` int NOT NULL DEFAULT 0 COMMENT '创建人id',
  `create_time` datetime NOT NULL DEFAULT now() COMMENT '创建时间',
  `update_uid` int NOT NULL DEFAULT 0 COMMENT '更新人id',
  `update_time` datetime NOT NULL DEFAULT now() COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  INDEX `idx_sys_user_empno` (`emp_no`) USING BTREE ,
  UNIQUE INDEX `idx_sys_user_user_code` (`user_code`) USING BTREE ,
  INDEX `idx_sys_user_phone` (`phone`) USING BTREE ,
  INDEX `idx_sys_user_real_name` (`real_name`) USING BTREE
)
  ENGINE=InnoDB
  DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci
  COMMENT='用户表';


CREATE TABLE `sys_role` (
  `role_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_code` varchar(50) NOT NULL DEFAULT '' COMMENT '角色编码',
  `role_name` varchar(50) NOT NULL DEFAULT '' COMMENT '角色名称',
  `state` int(1) NOT NULL DEFAULT 0 COMMENT '0:in_use,1:invalid',
  `description` varchar(2000) NOT NULL DEFAULT '' COMMENT '描述/备注',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除：0在使用、1已删除',
  `create_uid` int NOT NULL DEFAULT 0 COMMENT '创建人id',
  `create_time` datetime NOT NULL DEFAULT now() COMMENT '创建时间',
  `update_uid` int NOT NULL DEFAULT 0 COMMENT '更新人id',
  `update_time` datetime NOT NULL DEFAULT now() COMMENT '更新时间',
  PRIMARY KEY (`role_id`),
  INDEX `idx_sys_role_role_code` (`role_code`) USING BTREE ,
  INDEX `idx_sys_role_role_name` (`role_name`) USING BTREE
)
  ENGINE=InnoDB
  DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci
  COMMENT='角色表';


CREATE TABLE `sys_resource` (
  `resource_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `resource_code` varchar(50) NOT NULL DEFAULT '' COMMENT '权限编码',
  `resource_name` varchar(50) NOT NULL DEFAULT '' COMMENT '权限名称',
  `resource_type` int NOT NULL DEFAULT 0 COMMENT '资源类型：1目录、2菜单、3功能',
  `parent_id` int(10) NOT NULL DEFAULT 0 COMMENT '父节点ID',
  `state` int(1) NOT NULL DEFAULT 0 COMMENT '0:in_use,1:invalid',
  `menu_url` varchar(255) NOT NULL DEFAULT '' COMMENT '菜单链接',
  `feature_url` varchar(255) NOT NULL DEFAULT '' COMMENT '功能链接',
  `description` varchar(2000) NOT NULL DEFAULT '' COMMENT '描述/备注',
  `sort` int NOT NULL DEFAULT 0 COMMENT '排序',
  `icon` varchar(255) NOT NULL DEFAULT '' COMMENT '小图标',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除：0在使用、1已删除',
  `create_uid` int NOT NULL DEFAULT 0 COMMENT '创建人id',
  `create_time` datetime NOT NULL DEFAULT now() COMMENT '创建时间',
  `update_uid` int NOT NULL DEFAULT 0 COMMENT '更新人id',
  `update_time` datetime NOT NULL DEFAULT now() COMMENT '更新时间',
  PRIMARY KEY (`resource_id`),
  UNIQUE INDEX `idx_sys_resource_resource_code` (`resource_code`) USING BTREE ,
  INDEX `idx_sys_resource_parent_id` (`parent_id`) USING BTREE ,
  INDEX `idx_sys_resource_sort` (`sort`) USING BTREE
)
  ENGINE=InnoDB
  DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci
  COMMENT='权限表';




CREATE TABLE `sys_user_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int NOT NULL DEFAULT 0 COMMENT '用户ID',
  `role_id` int NOT NULL DEFAULT 0 COMMENT '角色ID',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除：0、1',
  `create_uid` int NOT NULL DEFAULT 0 COMMENT '创建人id',
  `create_time` datetime NOT NULL DEFAULT now() COMMENT '创建时间',
  `update_uid` int NOT NULL DEFAULT 0 COMMENT '更新人id',
  `update_time` datetime NOT NULL DEFAULT now() COMMENT '更新时间',
  PRIMARY KEY (`id`),
  INDEX `idx_sys_user_role_user_id` (`user_id`) USING BTREE ,
  INDEX `idx_sys_user_role_role_id` (`role_id`) USING BTREE
)
  ENGINE=InnoDB
  DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci
  COMMENT='用户-角色关联表';




CREATE TABLE `sys_role_resource` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `resource_id` int NOT NULL DEFAULT 0 COMMENT '权限ID',
  `role_id` int NOT NULL DEFAULT 0 COMMENT '角色ID',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除：0在使用、1已删除',
  `create_uid` int NOT NULL DEFAULT 0 COMMENT '创建人id',
  `create_time` datetime NOT NULL DEFAULT now() COMMENT '创建时间',
  `update_uid` int NOT NULL DEFAULT 0 COMMENT '更新人id',
  `update_time` datetime NOT NULL DEFAULT now() COMMENT '更新时间',
  PRIMARY KEY (`id`),
  INDEX `idx_sys_role_resource_resource_id` (`resource_id`) USING BTREE ,
  INDEX `idx_sys_role_resource_role_id` (`role_id`) USING BTREE
)
  ENGINE=InnoDB
  DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci
  COMMENT='角色-权限关联表';



CREATE TABLE `sys_log` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int NOT NULL DEFAULT 0 COMMENT '用户ID',
  `user_code` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名称',
  `real_name` varchar(50) NOT NULL DEFAULT '' COMMENT '真实姓名',
  `phone` varchar(50) NOT NULL DEFAULT '' COMMENT '联系方式',
  `url` varchar(255) NOT NULL DEFAULT '' COMMENT '请求url',
  `ip` varchar(255) NOT NULL DEFAULT '' COMMENT '请求ip',
  `module_name` varchar(30) NOT NULL DEFAULT '' COMMENT '功能模块：枚举module_name',
  `operation_type` varchar(50) NOT NULL DEFAULT '' COMMENT '操作类型（新增等）',
  `operation` text  COMMENT '操作内容',
  `create_time` datetime DEFAULT now() COMMENT '操作时间',
  PRIMARY KEY (`id`),
  INDEX `idx_sys_log_user_id` (`user_id`) USING BTREE ,
  INDEX `idx_sys_log_sys_real_name` (`real_name`) USING BTREE,
  INDEX `idx_sys_log_sys_module_name` (`module_name`) USING BTREE
)
  ENGINE=InnoDB
  DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci
  COMMENT='用户操作记录表';


CREATE TABLE `sys_file` (
  `id` int(15) NOT NULL AUTO_INCREMENT COMMENT '文件ID',
  `file_no` varchar(100) DEFAULT '' COMMENT '文件唯一编码，全局唯一',
  `object_type` varchar(100) DEFAULT '' COMMENT '文件所属的业务对象类型',
  `object_id` int(15) DEFAULT 0 COMMENT '业务对象ID',
  `biz_type` varchar(200) DEFAULT '' COMMENT '业务类型',
  `biz_id` int(15) DEFAULT 0 COMMENT '业务对象的业务类型ID',
  `file_key` varchar(20) DEFAULT '' COMMENT '文件关键字位置（可定位文件排序位置，而不是使用file_order）',
  `file_type` int(2) DEFAULT 1 COMMENT '文档类型（1：图片，2：视频，3：普通文件）',
  `file_name` varchar(200) DEFAULT '' COMMENT '文件名称',
  `file_size` decimal(18,2) DEFAULT NULL COMMENT '文件大小（k）',
  `file_format` varchar(20) DEFAULT '' COMMENT '文件格式',
  `file_host` varchar(50) DEFAULT '' COMMENT '文件域名',
  `file_path` varchar(300) DEFAULT NULL COMMENT '上传路径',
  `file_order` int(2) DEFAULT 10 COMMENT '文件排序类型，默认10，0-99之间',
  `is_public` tinyint(1) DEFAULT '1' COMMENT '是否公开访问（1公开，0私有）',
  `deleted` int(1) DEFAULT '0' COMMENT '是否已删除（0使用中，1已删除）',
  `description` varchar(2000) NOT NULL DEFAULT '' COMMENT '描述/备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_uid` int(20) DEFAULT 0 COMMENT '创建人ID',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `update_uid` int(15) DEFAULT 0 COMMENT '修改人ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_file_no` (`file_no`),
  KEY `index_object` (`object_type`,`object_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='文件表';


CREATE TABLE sys_dict
(
  id          INT AUTO_INCREMENT
  COMMENT '主键',
  dict_type   VARCHAR(60)           NOT NULL default ''
  COMMENT '字典类型',
  dict_type_name        VARCHAR(150)           NOT NULL default ''
  COMMENT '字典类型名称',
  dict_key VARCHAR(60)    NOT NULL default ''
  COMMENT '字典-key',
  dict_value   VARCHAR(150)           NOT NULL default ''
  COMMENT '字典-value',
  sort_no     INT(8) DEFAULT 0     NOT NULL default 0
  COMMENT '排序号',
  remark      VARCHAR(600)           NOT NULL default ''
  COMMENT '备注',
  `state` int(1) NOT NULL DEFAULT 0 COMMENT '0:in_use,1:invalid',
  deleted     TINYINT(1) DEFAULT '0' NOT NULL default 0
  COMMENT '0 在使用 1 已删除',
  create_time DATETIME               NOT NULL default now()
  COMMENT '创建时间',
  create_uid  BIGINT                 NULL
  COMMENT '创建人ID',
  update_time DATETIME               NOT NULL default now()
  COMMENT '修改时间',
  update_uid  BIGINT                 NULL
  COMMENT '修改人ID',
  PRIMARY KEY(`id`),
  INDEX `idx_sys_dict_dict_type` (`dict_type`) USING BTREE ,
  INDEX `idx_sys_dict_dict_type_name` (`dict_type_name`) USING BTREE,
  INDEX `idx_sys_dict_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB
  DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '字典表';



CREATE TABLE `sys_version_sql` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `version` varchar(50) NOT NULL DEFAULT '' COMMENT '版本',
  `sql`  text  COMMENT 'sql',
  `creator` varchar(50) NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime DEFAULT now() COMMENT '创建时间',
  PRIMARY KEY (`id`)
)
  ENGINE=InnoDB
  DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci
  COMMENT='版本sql更新记录';