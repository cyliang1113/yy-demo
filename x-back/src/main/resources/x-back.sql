/*
Navicat MySQL Data Transfer

Source Server         : 192.168.17.212
Source Server Version : 50629
Source Host           : 192.168.17.212:3306
Source Database       : x-back

Target Server Type    : MYSQL
Target Server Version : 50629
File Encoding         : 65001

Date: 2017-02-13 18:47:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_menu`;
CREATE TABLE `t_sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(64) DEFAULT NULL,
  `menu_level` int(11) DEFAULT NULL,
  `menu_type` int(11) DEFAULT NULL,
  `menu_url` varchar(256) DEFAULT NULL,
  `menu_weight` int(11) DEFAULT NULL,
  `parent_menu_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_menu
-- ----------------------------
INSERT INTO `t_sys_menu` VALUES ('1', '系统管理', '1', '1', null, '100', null);
INSERT INTO `t_sys_menu` VALUES ('2', '菜单管理', '2', '2', 'sysmanage/menumanage', '1', '1');
INSERT INTO `t_sys_menu` VALUES ('3', '权限管理', '2', '1', null, '2', '1');
INSERT INTO `t_sys_menu` VALUES ('4', '角色管理', '3', '2', 'sys/menu', '1', '3');
INSERT INTO `t_sys_menu` VALUES ('5', '财务管理', '1', '1', null, '50', null);
INSERT INTO `t_sys_menu` VALUES ('6', '支付监控', '2', '2', 'sys/menu', '50', '5');
INSERT INTO `t_sys_menu` VALUES ('7', '退款监控', '2', '2', 'sys/menu', '51', '5');
