package cn.leo.mybatisdemo.dao;

import cn.leo.mybatisdemo.po.User;
import cn.leo.mybatisdemo.po.UserUser;

import java.util.List;
import java.util.Map;

public interface UserDao {

	public void batchInsert(List<User> users);
}
