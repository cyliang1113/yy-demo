package cn.leo.ibatisdemo.dao;

import cn.leo.ibatisdemo.comm.BaseIbatisDao;
import cn.leo.ibatisdemo.po.UserUser;

public class UserUserDao extends BaseIbatisDao {
	
	public UserUser selectByUsername(String username){
		return (UserUser) super.queryForObject("", username);
	}
}
