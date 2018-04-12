
CREATE TABLE `t_user_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL,
  `cellphone` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------

-- ----------------------------
INSERT INTO `t_user_user` VALUES (null, '小明', '13052510001');
INSERT INTO `t_user_user` VALUES (null, 'tom', '13052511332');