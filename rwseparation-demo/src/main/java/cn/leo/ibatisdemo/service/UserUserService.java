package cn.leo.ibatisdemo.service;

import java.util.List;
import java.util.Map;

import cn.leo.ibatisdemo.comm.ReadOnlyDataSource;
import cn.leo.ibatisdemo.po.UserUser;

public interface UserUserService {
	@ReadOnlyDataSource
	public UserUser selectByUserId(Long userId);

	@ReadOnlyDataSource
	public List<UserUser> selectByParam(Map<String, Object> param);

	public void saveUser(UserUser user);
}
