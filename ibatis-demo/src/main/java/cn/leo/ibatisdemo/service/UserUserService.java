package cn.leo.ibatisdemo.service;

import java.util.List;
import java.util.Map;

import cn.leo.ibatisdemo.po.UserUser;

public interface UserUserService {

	public UserUser selectByUserId(Long userId);

	public List<UserUser> selectByParam(Map<String, Object> param);
}
