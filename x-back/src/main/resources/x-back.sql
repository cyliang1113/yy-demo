/*
Navicat MySQL Data Transfer

Source Server         : 192.168.19.221
Source Server Version : 50629
Source Host           : 192.168.19.221:3306
Source Database       : sys-back

Target Server Type    : MYSQL
Target Server Version : 50629
File Encoding         : 65001

Date: 2017-02-12 22:29:55
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_menu
-- ----------------------------
INSERT INTO `t_sys_menu` VALUES ('1', '系统管理', '1', '1', null, '100', null);
INSERT INTO `t_sys_menu` VALUES ('2', '菜单管理', '2', '2', '/sys/menu', '1', '1');
