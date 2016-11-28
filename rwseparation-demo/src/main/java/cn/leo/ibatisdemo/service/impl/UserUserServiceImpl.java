package cn.leo.ibatisdemo.service.impl;

import java.util.List;
import java.util.Map;

import cn.leo.ibatisdemo.dao.UserUserDao;
import cn.leo.ibatisdemo.po.UserUser;
import cn.leo.ibatisdemo.service.UserUserService;

public class UserUserServiceImpl implements UserUserService {
	private UserUserDao userUserDao;

	public UserUser selectByUserId(Long userId) {
		return userUserDao.selectByUserId(userId);
	}

	public List<UserUser> selectByParam(Map<String, Object> param) {
		return userUserDao.selectByParam(param);
	}

	public void setUserUserDao(UserUserDao userUserDao) {
		this.userUserDao = userUserDao;
	}
}
