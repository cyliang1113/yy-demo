package cn.leo.ibatisdemo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.leo.ibatisdemo.comm.BaseIbatisDao;
import cn.leo.ibatisdemo.po.UserUser;

public class UserUserDao extends BaseIbatisDao {

	public UserUser selectByUserId(Long userId) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("userId", userId);
		return (UserUser) super.queryForObject("USER_USER.selectByParamMap", param);
	}

	public List<UserUser> selectByParam(Map<String, Object> param) {
		return (List<UserUser>) super.queryForList("USER_USER.selectByParamMap", param);
	}
}
