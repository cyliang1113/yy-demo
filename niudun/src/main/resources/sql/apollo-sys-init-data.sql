-- init res
INSERT INTO niudun_console.sys_resource (resource_id, resource_code, resource_name, resource_type, parent_id, state, menu_url, feature_url, description, sort, icon, deleted, create_uid, create_time, update_uid, update_time) VALUES (1, 'sys_prop_folder', '系统设置', 1, 0, 0, '', '', '系统设置根目录', 999, 'http://cheguo-image.cheguo.com/http://cheguo-image.cheguo.com/http://cheguo-image.cheguo.com/http://cheguo-image.cheguo.com/http://cheguo-image.cheguo.com/apollo-sys-server/2018-07-30/4eca89f4e1c8473a90fabbfe380b3469.png', 0, 1, '2018-07-30 09:35:30', 1, '2018-08-09 12:54:45');
INSERT INTO niudun_console.sys_resource (resource_id, resource_code, resource_name, resource_type, parent_id, state, menu_url, feature_url, description, sort, icon, deleted, create_uid, create_time, update_uid, update_time) VALUES (2, 'menu_sys_user', '用户管理', 2, 1, 0, '/sys/userManage/index', '', '', 999, 'http://cheguo-image.cheguo.com/apollo-sys-server/2018-08-14/f19c65ecd699401597010d3d43d4b224.png', 0, 1, '2018-07-30 09:36:16', 1, '2018-08-09 12:55:12');
INSERT INTO niudun_console.sys_resource (resource_id, resource_code, resource_name, resource_type, parent_id, state, menu_url, feature_url, description, sort, icon, deleted, create_uid, create_time, update_uid, update_time) VALUES (3, 'menu_sys_role', '角色管理', 2, 1, 0, '/sys/roleManage/index', '', '', 888, 'http://cheguo-image.cheguo.com/http://cheguo-image.cheguo.com/apollo-sys-server/2018-08-02/8bcc6d121cc049caabf18237f2cd42e8.png', 0, 1, '2018-07-30 09:37:23', 1, '2018-08-09 12:56:16');
INSERT INTO niudun_console.sys_resource (resource_id, resource_code, resource_name, resource_type, parent_id, state, menu_url, feature_url, description, sort, icon, deleted, create_uid, create_time, update_uid, update_time) VALUES (4, 'menu_sys_resource', '权限管理', 2, 1, 0, '/sys/permissionManage/index', '', '', 777, 'http://cheguo-image.cheguo.com/apollo-sys-server/2018-08-14/e8ef0baf4b664f17b8e7a33568386736.png', 0, 1, '2018-07-30 11:01:24', 1, '2018-08-09 12:56:37');
INSERT INTO niudun_console.sys_resource (resource_id, resource_code, resource_name, resource_type, parent_id, state, menu_url, feature_url, description, sort, icon, deleted, create_uid, create_time, update_uid, update_time) VALUES (5, 'menu_sys_file', '文件图片管理', 2, 1, 0, '/sys/fileImgManage/index', '', '', 700, 'http://cheguo-image.cheguo.com/apollo-sys-server/2018-08-02/f18650fc5ea940dbafcb9da52ef7cf5c.png', 0, 1, '2018-07-30 11:03:26', 1, '2018-08-09 12:57:01');
INSERT INTO niudun_console.sys_resource (resource_id, resource_code, resource_name, resource_type, parent_id, state, menu_url, feature_url, description, sort, icon, deleted, create_uid, create_time, update_uid, update_time) VALUES (6, 'menu_sys_cache', '缓存管理', 2, 1, 0, '/sys/cacheManage/index', '', '', 599, 'http://cheguo-image.cheguo.com/apollo-sys-server/2018-08-02/e2ea1c5cdc42482dbd58f921f9aef03a.png', 0, 1, '2018-07-30 11:04:09', 1, '2018-08-09 12:57:45');
INSERT INTO niudun_console.sys_resource (resource_id, resource_code, resource_name, resource_type, parent_id, state, menu_url, feature_url, description, sort, icon, deleted, create_uid, create_time, update_uid, update_time) VALUES (7, 'menu_sys_log', '操作日志', 2, 1, 0, '/sys/operationLog/index', '', '', 588, 'http://cheguo-image.cheguo.com/apollo-sys-server/2018-08-02/f64a525fb3794910b3244efc09a9088f.png', 0, 1, '2018-07-30 11:04:46', 1, '2018-08-09 12:58:05');
INSERT INTO niudun_console.sys_resource (resource_id, resource_code, resource_name, resource_type, parent_id, state, menu_url, feature_url, description, sort, icon, deleted, create_uid, create_time, update_uid, update_time) VALUES (9, 'menu_sys_dict', '数据字典', 2, 1, 0, '/sys/dataDict/index', '', '', 488, 'http://cheguo-image.cheguo.com/apollo-sys-server/2018-08-02/5029996fbaf74dda8064efe139c9ad02.png', 0, 1, '2018-07-30 11:05:31', 1, '2018-08-09 12:58:48');

-- admin role
INSERT INTO niudun_console.sys_role (role_id, role_code, role_name, state, description, deleted, create_uid, create_time, update_uid, update_time) VALUES (1, 'sysadmin', '系统管理员', 0, '', 0, 0, '2018-07-30 09:40:02', 1, '2018-08-09 14:14:27');

INSERT INTO niudun_console.sys_role_resource (resource_id, role_id, deleted, create_uid, create_time, update_uid, update_time) VALUES (1, 1, 0, 1, now(), 1, now());
INSERT INTO niudun_console.sys_role_resource (resource_id, role_id, deleted, create_uid, create_time, update_uid, update_time) VALUES (2, 1, 0, 1, now(), 1, now());
INSERT INTO niudun_console.sys_role_resource (resource_id, role_id, deleted, create_uid, create_time, update_uid, update_time) VALUES (3, 1, 0, 1, now(), 1, now());
INSERT INTO niudun_console.sys_role_resource (resource_id, role_id, deleted, create_uid, create_time, update_uid, update_time) VALUES (4, 1, 0, 1, now(), 1, now());
INSERT INTO niudun_console.sys_role_resource (resource_id, role_id, deleted, create_uid, create_time, update_uid, update_time) VALUES (5, 1, 0, 1, now(), 1, now());
INSERT INTO niudun_console.sys_role_resource (resource_id, role_id, deleted, create_uid, create_time, update_uid, update_time) VALUES (6, 1, 0, 1, now(), 1, now());
INSERT INTO niudun_console.sys_role_resource (resource_id, role_id, deleted, create_uid, create_time, update_uid, update_time) VALUES (7, 1, 0, 1, now(), 1, now());
INSERT INTO niudun_console.sys_role_resource (resource_id, role_id, deleted, create_uid, create_time, update_uid, update_time) VALUES (9, 1, 0, 1, now(), 1, now());

INSERT INTO niudun_console.sys_user (user_id, emp_no, user_code, nick_name, password, real_name, phone, state, sex, age, email, id_number, register_time, ip, last_login_time, province_code, province_name, city_code, city_name, area_code, area_name, address, description, salt, deleted, create_uid, create_time, update_uid, update_time)
VALUES (1,'1', 'sysadmin', 'super', 'fa0b80af5114b817dd0478cf87a04d20', '超级管理员', '00000000000', 0, 1, 0, '', '', now(), '', now(), '', '', '', '', '', '', '', '', '3119be2b468c7a00711be980d78503c5', 0, 0, now(), 0, now());

INSERT INTO niudun_console.sys_user_role (user_id, role_id, deleted, create_uid, create_time, update_uid, update_time) VALUES (1, 1, 0, 0, now(), 0, now());

---- dict 数据字典

-- 文件管理-业务单元类型
INSERT INTO niudun_console.sys_dict (dict_type, dict_type_name, dict_key, dict_value, sort_no, remark, deleted, create_time, create_uid, update_time, update_uid, state)
VALUES ('sysfile_biz_type', '文件管理-业务单元类型', '1', '权限管理', 1, '', 0, now(), 1, now(), 1, 0);
INSERT INTO niudun_console.sys_dict (dict_type, dict_type_name, dict_key, dict_value, sort_no, remark, deleted, create_time, create_uid, update_time, update_uid, state)
VALUES ('sysfile_biz_type', '文件管理-业务单元类型', '2', '车商管理', 2, '', 0, now(), 1, now(), 1, 0);

-- 系统日志-操作类型
INSERT INTO niudun_console.sys_dict (dict_type, dict_type_name, dict_key, dict_value, sort_no, remark, deleted, create_time, create_uid, update_time, update_uid, state)
VALUES ('syslog_module_name', '系统日志-操作类型', 'SYSUSER', '用户管理', 1, '', 0, now(), 1, now(), 1, 0);
INSERT INTO niudun_console.sys_dict (dict_type, dict_type_name, dict_key, dict_value, sort_no, remark, deleted, create_time, create_uid, update_time, update_uid, state)
VALUES ('syslog_module_name', '系统日志-操作类型', 'SYSROLE', '角色管理', 2, '', 0, now(), 1, now(), 1, 0);
INSERT INTO niudun_console.sys_dict (dict_type, dict_type_name, dict_key, dict_value, sort_no, remark, deleted, create_time, create_uid, update_time, update_uid, state)
VALUES ('syslog_module_name', '系统日志-操作类型', 'SYSRESOURCE', '权限管理', 3, '', 0, now(), 1, now(), 1, 0);
INSERT INTO niudun_console.sys_dict (dict_type, dict_type_name, dict_key, dict_value, sort_no, remark, deleted, create_time, create_uid, update_time, update_uid, state)
VALUES ('syslog_module_name', '系统日志-操作类型', 'SYSFILE', '文件图片管理', 4, '', 0, now(), 1, now(), 1, 0);
INSERT INTO niudun_console.sys_dict (dict_type, dict_type_name, dict_key, dict_value, sort_no, remark, deleted, create_time, create_uid, update_time, update_uid, state)
VALUES ('syslog_module_name', '系统日志-操作类型', 'SYSCACHE', '缓存管理', 5, '', 0, now(), 1, now(), 1, 0);
INSERT INTO niudun_console.sys_dict (dict_type, dict_type_name, dict_key, dict_value, sort_no, remark, deleted, create_time, create_uid, update_time, update_uid, state)
VALUES ('syslog_module_name', '系统日志-操作类型', 'SYSDICT', '数据字典', 7, '', 0, now(), 1, now(), 1, 0);
INSERT INTO niudun_console.sys_dict (dict_type, dict_type_name, dict_key, dict_value, sort_no, remark, deleted, create_time, create_uid, update_time, update_uid, state)
VALUES ('syslog_module_name', '系统日志-操作类型', 'SYSLOGIN', '登录和注销', 8, '', 0, now(), 1, now(), 1, 0);

-- 系统文件上传管理配置
INSERT INTO niudun_console.sys_dict (dict_type, dict_type_name, dict_key, dict_value, sort_no, remark, deleted, create_time, create_uid, update_time, update_uid, state)
VALUES ('cheguo_apollo_sysfile', '系统文件上传管理配置', 'thumbnailname', 'http://cheguo-image.cheguo.com/', 1, '', 0, now(), 1, now(), 1, 0);
INSERT INTO niudun_console.sys_dict (dict_type, dict_type_name, dict_key, dict_value, sort_no, remark, deleted, create_time, create_uid, update_time, update_uid, state)
VALUES ('cheguo_apollo_sysfile', '系统文件上传管理配置', 'zacdn', 'http://zacdn-image.cgw360.com/', 2, '', 0, now(), 1, now(), 1, 0);
INSERT INTO niudun_console.sys_dict (dict_type, dict_type_name, dict_key, dict_value, sort_no, remark, deleted, create_time, create_uid, update_time, update_uid, state)
VALUES ('cheguo_apollo_sysfile', '系统文件上传管理配置', 'zacdn_check', 'https://zacdn-image.cgw360.com/', 3, '', 0, now(), 1, now(), 1, 0);
INSERT INTO niudun_console.sys_dict (dict_type, dict_type_name, dict_key, dict_value, sort_no, remark, deleted, create_time, create_uid, update_time, update_uid, state)
VALUES ('cheguo_apollo_sysfile', '系统文件上传管理配置', 'cdnname_check', 'https://cdn-file.cheguo.com/', 4, '', 0, now(), 1, now(), 1, 0);
INSERT INTO niudun_console.sys_dict (dict_type, dict_type_name, dict_key, dict_value, sort_no, remark, deleted, create_time, create_uid, update_time, update_uid, state)
VALUES ('cheguo_apollo_sysfile', '系统文件上传管理配置', 'accesskeysecret', 'bWmdaac1hm943ATKl451U1K2UtgtAQ', 5, '', 0, now(), 1, now(), 1, 0);
INSERT INTO niudun_console.sys_dict (dict_type, dict_type_name, dict_key, dict_value, sort_no, remark, deleted, create_time, create_uid, update_time, update_uid, state)
VALUES ('cheguo_apollo_sysfile', '系统文件上传管理配置', 'aliyunserver', 'http://oss.aliyuncs.com/', 6, '', 0, now(), 1, now(), 1, 0);
INSERT INTO niudun_console.sys_dict (dict_type, dict_type_name, dict_key, dict_value, sort_no, remark, deleted, create_time, create_uid, update_time, update_uid, state)
VALUES ('cheguo_apollo_sysfile', '系统文件上传管理配置', 'filesource', '10f4fe1edeae11e5b7be086266812821', 7, '', 0, now(), 1, now(), 1, 0);
INSERT INTO niudun_console.sys_dict (dict_type, dict_type_name, dict_key, dict_value, sort_no, remark, deleted, create_time, create_uid, update_time, update_uid, state)
VALUES ('cheguo_apollo_sysfile', '系统文件上传管理配置', 'cdnname2', 'http://cheguo-image.cheguo.com/', 8, '', 0, now(), 1, now(), 1, 0);
INSERT INTO niudun_console.sys_dict (dict_type, dict_type_name, dict_key, dict_value, sort_no, remark, deleted, create_time, create_uid, update_time, update_uid, state)
VALUES ('cheguo_apollo_sysfile', '系统文件上传管理配置', 'accesskeyid', 'LTAI6AYWB4LRdK1Z', 9, '', 0, now(), 1, now(), 1, 0);
INSERT INTO niudun_console.sys_dict (dict_type, dict_type_name, dict_key, dict_value, sort_no, remark, deleted, create_time, create_uid, update_time, update_uid, state)
VALUES ('cheguo_apollo_sysfile', '系统文件上传管理配置', 'cdnname2_check', 'https://cheguo-image.cheguo.com/', 10, '', 0, now(), 1, now(), 1, 0);
INSERT INTO niudun_console.sys_dict (dict_type, dict_type_name, dict_key, dict_value, sort_no, remark, deleted, create_time, create_uid, update_time, update_uid, state)
VALUES ('cheguo_apollo_sysfile', '系统文件上传管理配置', 'cdnname', 'http://cdn-file.cheguo.com/', 11, '', 0, now(), 1, now(), 1, 0);
INSERT INTO niudun_console.sys_dict (dict_type, dict_type_name, dict_key, dict_value, sort_no, remark, deleted, create_time, create_uid, update_time, update_uid, state)
VALUES ('cheguo_apollo_sysfile', '系统文件上传管理配置', 'bucketname', 'cheguo', 12, '', 0, now(), 1, now(), 1, 0);
INSERT INTO niudun_console.sys_dict (dict_type, dict_type_name, dict_key, dict_value, sort_no, remark, deleted, create_time, create_uid, update_time, update_uid, state)
VALUES ('cheguo_apollo_sysfile', '系统文件上传管理配置', 'style', 'image/resize,m_mfit,w_640,h_640', 13, '', 0, now(), 1, now(), 1, 0);
INSERT INTO niudun_console.sys_dict (dict_type, dict_type_name, dict_key, dict_value, sort_no, remark, deleted, create_time, create_uid, update_time, update_uid, state)
VALUES ('cheguo_apollo_sysfile', '系统文件上传管理配置', 'thumbnailserver', 'http://img-cn-hangzhou.aliyuncs.com/', 14, '', 0, now(), 1, now(), 1, 0);

-- 权限类型
INSERT INTO niudun_console.sys_dict (dict_type, dict_type_name, dict_key, dict_value, sort_no, remark, state, deleted, create_time, create_uid, update_time, update_uid) VALUES ('sys_resource_type', '权限管理_权限类型', '1', '目录', 3, '', 0, 0, '2018-08-14 18:53:36', 1, '2018-08-14 18:53:36', 1);
INSERT INTO niudun_console.sys_dict (dict_type, dict_type_name, dict_key, dict_value, sort_no, remark, state, deleted, create_time, create_uid, update_time, update_uid) VALUES ('sys_resource_type', '权限管理_权限类型', '2', '菜单', 2, '', 0, 0, '2018-08-14 18:53:51', 1, '2018-08-14 18:53:51', 1);
INSERT INTO niudun_console.sys_dict (dict_type, dict_type_name, dict_key, dict_value, sort_no, remark, state, deleted, create_time, create_uid, update_time, update_uid) VALUES ('sys_resource_type', '权限管理_权限类型', '3', '功能', 1, '', 0, 0, '2018-08-14 18:54:17', 1, '2018-08-14 18:54:17', 1);