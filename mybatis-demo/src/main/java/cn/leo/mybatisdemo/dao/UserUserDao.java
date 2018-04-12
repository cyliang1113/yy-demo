package cn.leo.mybatisdemo.dao;

import cn.leo.mybatisdemo.po.UserUser;

public interface UserUserDao {
	
	UserUser findByUserId(Long userId);
}
