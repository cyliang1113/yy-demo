package cn.leo.demo.service;

import cn.leo.demo.dao.UserDao;

public class UserServiceImpl implements UserService {
	private UserDao userDao;

	public String findUsername() {
		return userDao.findUsername();
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
