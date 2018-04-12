package cn.leo.mybatisdemo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.leo.mybatisdemo.dao.UserUserDao;
import cn.leo.mybatisdemo.po.UserUser;
import cn.leo.mybatisdemo.service.UserUserService;

@Service("userUserService")
public class UserUserServiceImpl implements UserUserService {
	
	@Autowired
	private UserUserDao userUserDao;

	@Override
	public UserUser findByUserId(Long userId) {
		
		return userUserDao.findByUserId(userId);
	}

}
