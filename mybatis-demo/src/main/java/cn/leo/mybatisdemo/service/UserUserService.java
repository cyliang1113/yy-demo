package cn.leo.mybatisdemo.service;

import cn.leo.mybatisdemo.po.UserUser;

import java.util.Map;

public interface UserUserService {
	UserUser findByUserId(Long userId);

	int updateUsernameByUserId(Map map);
}
