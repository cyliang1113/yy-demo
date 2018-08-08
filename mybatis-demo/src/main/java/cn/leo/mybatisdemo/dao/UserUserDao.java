package cn.leo.mybatisdemo.dao;

import cn.leo.mybatisdemo.po.UserUser;

import java.util.Map;

public interface UserUserDao {
	
	UserUser findByUserId(Long userId);

	int updateUsernameByUserId(Map map);
}
